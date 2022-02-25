package com.liumd.data.utils.excel;

import com.liumd.data.utils.excel.annotation.ExcelField;
import com.liumd.data.utils.excel.annotation.ExcelSheet;
import com.liumd.data.utils.excel.entity.ClassListEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tk.mybatis.mapper.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;


/**
 * @author liumd
 * @date 2022/2/7 9:16
 */

@Slf4j
public class ExcelExportUtil {


    public static final String XLS = "xls";

    public static final String XLSX = "xlsx";


    /**
     * 导出Excel对象
     *
     * @param classListEntity  Excel数据
     * @return Workbook
     */
    public static Workbook exportWorkbook(ClassListEntity classListEntity){
        List<ClassListEntity> classListEntityList = Collections.singletonList(classListEntity);
        return exportWorkbook(classListEntityList);
    }

    /**
     * 导出Excel对象
     *
     * @param classListEntityList  Excel数据
     * @return Workbook
     */
    public static Workbook exportWorkbook(List<ClassListEntity> classListEntityList){
        return exportWorkbook(XLSX, classListEntityList);
    }

    /**
     * 导出Excel对象
     *
     * @param classListEntityList  Excel数据
     * @return Workbook
     */
    public static Workbook exportWorkbook(String type, List<ClassListEntity> classListEntityList){

        // data array valid
        if (classListEntityList  == null || classListEntityList.size() == 0) {
            throw new RuntimeException(">>>>>>>>>>> xxl-excel error, data array can not be empty.");
        }

        Workbook workbook;
        // book （HSSFWorkbook=2003/xls、XSSFWorkbook=2007/xlsx）
        switch (type) {
            case XLS :
                workbook = new HSSFWorkbook();
                break;
            case XLSX :
                workbook = new XSSFWorkbook();
                break;
            default:
                throw new RuntimeException(">>>>>>>>>>> xxl-excel error, type only xls,xlsx.");
        }


        // sheet
        for (ClassListEntity classListEntity : classListEntityList) {
            makeSheet(workbook, classListEntity);
        }
        return workbook;
    }

    private static void makeSheet(Workbook workbook, ClassListEntity classListEntity){
        // data
        if (classListEntity == null) {
            throw new RuntimeException(">>>>>>>>>>> xxl-excel error, data can not be empty.");
        }

        Class<?> clazz = classListEntity.getClazz();
        if (clazz == null) {
            throw new RuntimeException(">>>>>>>>>>> xxl-excel error, class can not be empty.");
        }

        // sheet
        ExcelSheet excelSheet = clazz.getAnnotation(ExcelSheet.class);

        String sheetName = clazz.getSimpleName();
        if (excelSheet != null) {
            if (StringUtil.isNotEmpty(excelSheet.name().trim())) {
                sheetName = excelSheet.name().trim();
            }
        }

        Sheet existSheet = workbook.getSheet(sheetName);
        if (existSheet != null) {
            for (int i = 2; i <= 10; i++) {
                // avoid sheetName repetition
                String newSheetName = sheetName.concat(String.valueOf(i));
                existSheet = workbook.getSheet(newSheetName);
                if (existSheet == null) {
                    sheetName = newSheetName;
                    break;
                }
            }
        }

        Sheet sheet = workbook.createSheet(sheetName);

        // sheet field
        List<Field> fields = ReflectUtil.getFieldsNotStaticByIndex(clazz);

        if (fields==null || fields.size()==0) {
            throw new RuntimeException(">>>>>>>>>>> xxl-excel error, data field can not be empty.");
        }

        // sheet header row
        CellStyle[] fieldDataStyleArr = new CellStyle[fields.size()];
        int[] fieldWidthArr = new int[fields.size()];
        Row headRow = sheet.createRow(0);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            String fieldName = field.getName();
            int fieldWidth = 0;
            HorizontalAlignment align = null;
            if (excelField != null) {
                if (StringUtil.isNotEmpty(excelField.name().trim())) {
                    fieldName = excelField.name().trim();
                }
                fieldWidth = excelField.width();
                align = excelField.align();
            }

            // field width
            fieldWidthArr[i] = fieldWidth;

            // head-style、field-data-style
            CellStyle fieldDataStyle = workbook.createCellStyle();
            if (align != null) {
                fieldDataStyle.setAlignment(align);
            }
            fieldDataStyleArr[i] = fieldDataStyle;

            /*CellStyle headStyle = workbook.createCellStyle();
            headStyle.cloneStyleFrom(fieldDataStyle);
            if (headColorIndex > -1) {
                headStyle.setFillForegroundColor((short) headColorIndex);
                headStyle.setFillBackgroundColor((short) headColorIndex);
                headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }*/

            // head-field data
            Cell cellX = headRow.createCell(i, CellType.STRING);
            cellX.setCellValue(String.valueOf(fieldName));
        }

        if(classListEntity.getList() == null || classListEntity.getList().size() == 0) {
            return;
        }

        // sheet data rows
        for (int dataIndex = 0; dataIndex < classListEntity.getList().size(); dataIndex++) {
            int rowIndex = dataIndex + 1;
            Object rowData = classListEntity.getList().get(dataIndex);

            Row rowX = sheet.createRow(rowIndex);
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                try {
                    field.setAccessible(true);
                    Object fieldValue = field.get(rowData);
                    String fieldValueString = FieldReflectionUtil.formatValue(field, fieldValue);

                    Cell cellX = rowX.createCell(i, CellType.STRING);
                    cellX.setCellValue(fieldValueString);
                    cellX.setCellStyle(fieldDataStyleArr[i]);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            }
        }

        // sheet finally
        for (int i = 0; i < fields.size(); i++) {
            int fieldWidth = fieldWidthArr[i];
            if (fieldWidth > 0) {
                sheet.setColumnWidth(i, fieldWidth);
            } else {
                sheet.autoSizeColumn((short)i);
            }
        }
    }

    /**
     * 导出Excel文件到磁盘
     *
     * @param filePath
     * @param classListEntity  数据，可变参数，如多个参数则代表导出多张Sheet
     */
    public static void exportToFile(String filePath, ClassListEntity classListEntity){
        List<ClassListEntity> classListEntityList = Collections.singletonList(classListEntity);
        exportToFile(filePath, classListEntityList);
    }

    /**
     * 导出Excel文件到磁盘
     *
     * @param filePath
     * @param classListEntityList  数据，可变参数，如多个参数则代表导出多张Sheet
     */
    public static void exportToFile(String filePath, List<ClassListEntity> classListEntityList){
        exportToFile(XLSX, filePath, classListEntityList);
    }

    /**
     * 导出Excel文件到磁盘
     *
     * @param filePath
     * @param classListEntityList  数据，可变参数，如多个参数则代表导出多张Sheet
     */
    public static void exportToFile(String type, String filePath, List<ClassListEntity> classListEntityList){
        // workbook
        Workbook workbook = exportWorkbook(type, classListEntityList);

        FileOutputStream fileOutputStream = null;
        try {
            // workbook 2 FileOutputStream
            fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);

            // flush
            fileOutputStream.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileOutputStream!=null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 导出Excel字节数据
     *
     * @param classListEntityList
     * @return byte[]
     */
    public static byte[] exportToBytes(List<ClassListEntity> classListEntityList){
        return exportToBytes(XLSX, classListEntityList);
    }

    /**
     * 导出Excel字节数据
     *
     * @param classListEntity
     * @return byte[]
     */
    public static byte[] exportToBytes(ClassListEntity classListEntity){
        List<ClassListEntity> classListEntityList = Collections.singletonList(classListEntity);
        return exportToBytes(XLSX, classListEntityList);
    }

    /**
     * 导出Excel字节数据
     *
     * @param classListEntityList
     * @return byte[]
     */
    public static byte[] exportToBytes(String type, List<ClassListEntity> classListEntityList){
        // workbook
        Workbook workbook = exportWorkbook(type, classListEntityList);

        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] result = null;
        try {
            // workbook 2 ByteArrayOutputStream
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);

            // flush
            byteArrayOutputStream.flush();
            result = byteArrayOutputStream.toByteArray();
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

}

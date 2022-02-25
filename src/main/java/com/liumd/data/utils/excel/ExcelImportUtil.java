package com.liumd.data.utils.excel;

import com.liumd.data.utils.excel.annotation.ExcelSheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author liumd
 * @date 2022/2/7 9:17
 */

@Slf4j
public class ExcelImportUtil {


    /**
     * 从Workbook导入Excel文件，并封装成对象
     *
     * @param workbook
     * @param sheetClass
     * @return List<Object>
     */
    public static <T> List<T> importExcel(Workbook workbook, Class<T> sheetClass) {
        List<T> sheetDataList = importSheet(workbook, sheetClass);
        return sheetDataList;
    }

    private static <T> List<T> importSheet(Workbook workbook, Class<T> sheetClass) {
        try {
            // sheet
            ExcelSheet excelSheet = sheetClass.getAnnotation(ExcelSheet.class);
            if(excelSheet == null) {
                return null;
            }
            String sheetName = excelSheet.name().trim();

            // sheet field
            List<Field> fields = ReflectUtil.getFieldsNotStatic(sheetClass);

            if (fields==null || fields.size()==0) {
                throw new RuntimeException(">>>>>>>>>>> xxl-excel error, data field can not be empty.");
            }

            // sheet data
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return null;
            }

            Iterator<Row> sheetIterator = sheet.rowIterator();
            int rowIndex = 0;
            List<T> dataList = new ArrayList<>();
            while (sheetIterator.hasNext()) {
                Row rowX = sheetIterator.next();
                if (rowIndex > 0) {
                    T rowObj = sheetClass.newInstance();
                    for (int i = 0; i < fields.size(); i++) {

                        // cell
                        Cell cell = rowX.getCell(i);
                        if (cell == null) {
                            continue;
                        }

                        // call val str
                        cell.setCellType(CellType.STRING);
                        // cell.getCellTypeEnum()
                        String fieldValueStr = cell.getStringCellValue();

                        // java val
                        Field field = fields.get(i);
                        Object fieldValue = FieldReflectionUtil.parseValue(field, fieldValueStr);
                        if (fieldValue == null) {
                            continue;
                        }

                        // fill val
                        field.setAccessible(true);
                        field.set(rowObj, fieldValue);
                    }
                    dataList.add(rowObj);
                }
                rowIndex++;
            }
            return dataList;
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 导入Excel文件，并封装成对象
     *
     * @param excelFile
     * @param sheetClass
     * @return List<Object>
     */
    public static <T> List<T> importExcel(File excelFile, Class<T> sheetClass) {
        try {
            Workbook workbook = WorkbookFactory.create(excelFile);
            return importExcel(workbook, sheetClass);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件路径导入Excel文件，并封装成对象
     *
     * @param filePath
     * @param sheetClass
     * @return List<Object>
     */
    public static <T> List<T> importExcel(String filePath, Class<T> sheetClass) {
        File excelFile = new File(filePath);
        return importExcel(excelFile, sheetClass);
    }

    /**
     * 导入Excel数据流，并封装成对象
     *
     * @param inputStream
     * @param sheetClass
     * @return List<Object>
     */
    public static <T> List<T> importExcel(InputStream inputStream, Class<T> sheetClass) {
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            return importExcel(workbook, sheetClass);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}


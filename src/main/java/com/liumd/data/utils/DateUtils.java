package com.liumd.data.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liumuda
 * @date 2022/2/18 11:08
 */
public class DateUtils {
    /** 日期格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Date getStartOfDay(String date) {
        DateTime nowTime = new DateTime(date);
        DateTime startOfDay = nowTime.withTimeAtStartOfDay();
        return startOfDay.toDate();
    }

    public static Date getEndOfDay(String date) {
        DateTime nowTime = new DateTime(date);
        DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
        return endOfDay.toDate();
    }

    public static Date getStartOfDay(Date date) {
        DateTime nowTime = new DateTime(date);
        DateTime startOfDay = nowTime.withTimeAtStartOfDay();
        return startOfDay.toDate();
    }

    public static Date getEndOfDay(Date date) {
        DateTime nowTime = new DateTime(date);
        DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
        return endOfDay.toDate();
    }

    public static Date plusDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    public static String getNowDay() {
        return new DateTime().toString("yyyy-MM-dd");
    }

    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String formatDate(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    public static Date toDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        return new DateTime(date).toDate();
    }

    public static boolean isBeforeNow(String date) {
        DateTime d1 = new DateTime(date);
        return d1.isBeforeNow();
    }

    @Deprecated
    public static boolean isBeforeOfDay(String now, String date) {
        DateTime d1 = new DateTime(date);
        DateTime nowTime = new DateTime(now);
        return d1.isBefore(nowTime.millisOfDay().withMaximumValue());
    }

    public static int betweenDays(Date startDate, Date endDate) {
        return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays();
    }

    /***
     * 日期月份减一个月
     * @param datetime
     */

    public static String subMonth(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, -1);
        date = cl.getTime();
        return sdf.format(date);
    }
    /**
     * 字符串转换为日期
     * @param date 日期字符串
     * @param patten 格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parse(String date, String patten){
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    public static void main(String[] args) {
/*        System.out.println(new BigDecimal(2000).multiply(new BigDecimal(0.02))
                .multiply(new BigDecimal(46)).divide(new BigDecimal(30), 2, BigDecimal.ROUND_UP));

        Date date = new Date();
        System.out.println(DateUtils.betweenDays(date, DateUtils.plusDays(date, 2)));*/
        System.out.println(getFirstDayOfMonth(new Date()));
        System.out.println(DateUtils.formatDate(DateUtils.parse(DateUtils.subMonth("2022-09-28"),"yyyy-MM"),"yyyy-MM") + "-21");
    }

    /**
     * 字符串转换成日期(默认格式)
     *
     * @param strDate
     *            日期字符串
     */
    public static Date stringToDate(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    public static Date addDateMonth(Date date, int dd) {
        if (null == date) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date); // 设置当前日期
        c.add(Calendar.MONTH, dd); // 日期加1月
        date = c.getTime();
        return date;
    }

    public static String getKey() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DT_YYYYMMDDHHMMSS);
        return sdf.format(date);
    }

    /**
     * 获取给定时间是在月中的第几日
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.DAY_OF_MONTH);
    }

    //判断选择的日期是否是本月
    public static boolean isCurMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if(param.equals(now)){
            return true;
        }
        return false;
    }

    /**
     * 日期相减
     * date1-date2
     * 返回相差多少天
     * @param date1
     * @param date2
     * @return
     */
    public static int dateSubOfDays(Date date1,Date date2) {
        date1 = parse( formatDate(date1, "yyyyMMdd"),"yyyyMMdd");
        date2 = parse( formatDate(date2, "yyyyMMdd"),"yyyyMMdd");
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long time = time1-time2;
        long day = time/1000/60/60/24;
        return (int) day;
    }

    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = parse(formatDate(date), DATE_PATTERN);
        Date otherDateTmp = parse(formatDate(otherDate), DATE_PATTERN);
        if (dateTmp != null && otherDateTmp != null) {
            long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
            num = (int)(time / 86400000L);
        }

        return num;
    }

    public static String getDatetime() {
        return getDatetime(new Date());
    }
    public static String getDatetime(Date date) {
        return new SimpleDateFormat(DATETIME_PATTERN).format(date);
    }


    //实现日期加一天的方法
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);//增加一天
            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取指定日期当月的第一天
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // 获取当天凌晨零点的时间
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            // 获取当月第一天时间
            Date firstDay = calendar.getTime();
            return firstDay;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.biz.lesson.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.google.common.collect.Lists;

/**
 * 酌情使用,可以使用java 8 的 LocalDate 替换一些方法
 */
public class DateUtil {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HALFHOUR = MINUTE * 30;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;

    private DateUtil() {
    }

    public static int getAge(Date date) {
        Calendar c_now = Calendar.getInstance();
        Calendar c_birth = Calendar.getInstance();
        c_birth.setTimeInMillis(date.getTime());
        int age = c_now.get(Calendar.YEAR) - c_birth.get(Calendar.YEAR);

        c_birth.add(Calendar.YEAR, age);
        if (c_birth.getTimeInMillis() <= c_now.getTimeInMillis())
            age++;
        return age;
    }

    public static Date getUnknown() {
        Calendar c = Calendar.getInstance();
        c.set(2000, 0, 1);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Date afterDay(Date theDay) {
        return afterDay(theDay, 1);
    }

    public static Date afterDay(Date theDay, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(theDay);
        c.add(Calendar.DATE, days);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Date afterDay(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, days);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Date beforeDay(Date theDay) {
        return beforeDay(theDay, 1);
    }

    public static Date beforeDay(Date theDay, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(theDay);
        c.add(Calendar.DATE, -days);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Date beforeDay(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -days);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static boolean sameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        if (c1.get(Calendar.YEAR) == c1.get(Calendar.YEAR) && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }
        return false;
    }

    public static boolean isHoliday(Date day) {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        if (c.get(Calendar.DAY_OF_WEEK) == 7 || c.get(Calendar.DAY_OF_WEEK) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int betweenDays(Date d1, Date d2) {
        return betweenDays(d1, d2, true);
    }

    public static int betweenDays(Date d1, Date d2, boolean include) {
        Date date1 = Date.valueOf(d1.toString());
        Date date2 = Date.valueOf(d2.toString());
        long days = Math.abs(date1.getTime() - date2.getTime()) / DAY;
        if (include) {
            days++;
        }
        return (int) days;
    }


    public static int betweenDaysWithSymbols(Date d1, Date d2) {
        Date date1 = Date.valueOf(d1.toString());
        Date date2 = Date.valueOf(d2.toString());
        long days = (date1.getTime() - date2.getTime()) / DAY;
        return (int) days;
    }

    public static Date buildDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return new java.sql.Date(c.getTimeInMillis());
    }


    public static Date getDate() {
        Calendar c = Calendar.getInstance();
        return new java.sql.Date(c.getTimeInMillis());
    }


    public static Date getFirstDayOfCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, 1);
        return new java.sql.Date(c.getTimeInMillis());
    }


    public static Timestamp getDateTime() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    public static Date getNow() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    public static String fmtDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_PATTERN);
        return format.format(date);
    }

    public static String ftmDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date beforeMonth(Date theDay) {
        return beforeMonth(theDay, 1);
    }

    public static Date beforeMonth(Date theDay, int monthCount) {
        Calendar c = Calendar.getInstance();
        c.setTime(theDay);
        c.add(Calendar.MONTH, -monthCount);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Date firstDateOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), 1);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date lastDateOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, 1);
        calendar.add(Calendar.DATE, -1);
        return new Date(calendar.getTimeInMillis());
    }

    public static boolean isBetween(Date begin, Date end, Date date) {
        return date.after(begin) && date.before(end);
    }

    public static boolean isBetweenAndEqual(Date begin, Date end, Date date) {
        return (date.after(begin) || date.equals(begin)) && (date.before(end) || date.equals(end)) ;
    }

    
    public static Date reoveTime(Date date) {
        return Date.valueOf(date.toString());
    }

    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        return (week == 1 ? 7 : week - 1);
    }

    public static Date getFirstDay(int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, 1);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Date getLastDay(int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static java.sql.Date yearAgo() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Timestamp parseTimestamp(String str) {
        if (str == null)
            return null;
        str = str.trim();
        String[] arr = str.split(" ");
        try {
            String temp = "";
            if (arr != null && arr.length > 0)
                ;
            temp = java.sql.Date.valueOf(arr[0]).toString();
            if (arr.length == 2) {
                String[] time = arr[1].split(":");
                if (time != null) {
                    for (int i = 0; i < time.length; i++) {
                        if (i == 0)
                            temp += " ";
                        else
                            temp += ":";
                        temp += time[i];
                    }
                    for (int i = time.length; i < 3; i++) {
                        temp += ":00";
                    }
                }
            } else {
                temp += " 00:00:00";
            }
            //System.out.println(temp);
            return java.sql.Timestamp.valueOf(temp);
        } catch (Exception e) {
        }
        return null;
    }

    public static int getSeason(int month) {
        int result = 1;
        switch (month) {
            case 4:
            case 5:
            case 9:
            case 10:
            case 11:
            case 12:
                result = 1;
                break;
            case 6:
            case 7:
            case 8:
                result = 2;
                break;
            case 1:
            case 2:
            case 3:
                result = 3;
                break;
            default:
                result = 1;
        }
        return result;
    }


    /**
     * GTM时间转本地时间
     *
     * @param timeZone String such as GMT+08:00
     * @param date Date
     * @return Date
     */
    public static Date GMTToLocal(String timeZone, Date date) {
        long d = date.getTime();
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        d = d + tz.getOffset(d);
        return new Date(d);
    }

    /**
     * 本地时间转为GTM时间
     *
     * @param timeZone String such as GMT+08:00
     * @param date Date
     * @return Date
     */
    public static Date LocalToGMT(String timeZone, Date date) {
        long d = date.getTime();
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        d = d - tz.getOffset(d);
        return new Date(d);
    }

    /**
     * GTM时间转本地时间
     *
     * @param timeZone String such as GMT+08:00
     * @param date Timestamp
     * @return Timestamp
     */
    public static Timestamp GMTToLocal(String timeZone, Timestamp date) {
        long d = date.getTime();
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        d = d + tz.getOffset(d);
        return new Timestamp(d);
    }

    /**
     * 本地时间转为GTM时间
     *
     * @param timeZone String such as GMT+08:00
     * @param date Timestamp
     * @return Timestamp
     */
    public static Timestamp LocalToGMT(String timeZone, Timestamp date) {
        long d = date.getTime();
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        d = d - tz.getOffset(d);
        return new Timestamp(d);
    }

    public static Timestamp GMTFromTime(Timestamp time, Integer timeZone) {
        return new Timestamp(time.getTime() - timeZone.longValue() * HOUR);
    }

    public static Timestamp timeFromGMT(Timestamp GMT, Integer timeZone) {
        return new Timestamp(GMT.getTime() + timeZone.longValue() * HOUR);
    }

    public static long[] timeDifference(java.util.Date from, java.util.Date to) {
        long[] result = {0, 0, 0};
        if (from != null) {
            if (to == null)
                to = new java.util.Date();
            long margin = to.getTime() - from.getTime();

            result[0] = margin / DAY;
            margin = margin - (DAY * result[0]);

            result[1] = margin / HOUR;
            margin = margin - (HOUR * result[1]);

            result[2] = margin / DateUtil.MINUTE;
        }
        return result;
    }

    public static Date getWorkDate(Date date, int after) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = 0;
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week > 1 && week < 7)
            i = 1;
        while (i < after) {
            c.add(Calendar.DATE, 1);
            week = c.get(Calendar.DAY_OF_WEEK);
            if (week > 1 && week < 7)
                i++;
        }
        return new Date(c.getTimeInMillis());
    }

    public static Date getWorkDate(int after) {
        return getWorkDate(DateUtil.getDate(), after);
    }

    public static String toString(java.util.Date date) {
        return df.format(date);
    }


    public static String formatMinutes(Integer timeElapsed) {
        if (timeElapsed != null) {
            if (timeElapsed < 60) {
                return "" + timeElapsed + " Minutes";
            } else {
                int h = timeElapsed / 60;
                int i = timeElapsed % 60;
                if (i <= 20) {
                    return "" + h + " Hours";
                } else if (i > 20 && i < 40) {
                    return "" + h + ".5 Hours";
                } else {
                    return "" + (h + 1) + " Hours";
                }
            }
        }
        return "";
    }


    public static java.sql.Date addMonth(Date fromDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.MONTH, month);
        return new java.sql.Date(c.getTimeInMillis());
    }

    public static Date addDay(Date fromDate, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.DATE, day);
        return new Date(c.getTimeInMillis());
    }

    public static LocalDate dateToLoalDate(Date d){
    	if(d!=null){
    		return d.toLocalDate();
    	}
    	return null;
    }

    public static long dateDiff(Date from,Date to) {
    	LocalDate toLocalDate = (to == null ? LocalDate.now() : to.toLocalDate());
    	return (from==null) ? 0l : from.toLocalDate().toEpochDay() - toLocalDate.toEpochDay(); 
    }
    
   

	public static List<Date> datesBetween(Date fromDate, Date toDate) {
		if(toDate.before(fromDate)){
			return Lists.newArrayList(fromDate);
		}
		List<Date> result = Lists.newArrayList();
		int d = betweenDays(fromDate, toDate, false);
		for(int i=0;i<d;i++){
			result.add(DateUtil.addDay(fromDate, i));
		}
		return result;
	}

    public static List<Date> datesBetween(Date fromDate, Date toDate, boolean include) {
        if(toDate.before(fromDate)){
            return Lists.newArrayList(fromDate);
        }
        List<Date> result = Lists.newArrayList();
        int d = betweenDays(fromDate, toDate, include);
        for(int i=0;i<d;i++){
            result.add(DateUtil.addDay(fromDate, i));
        }
        return result;
    }
}


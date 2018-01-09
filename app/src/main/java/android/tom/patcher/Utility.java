package android.tom.patcher;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tom.saju on 1/4/2018.
 */

public class Utility {

    public static String DEFAULT_DATE_FORMAT = "dd-MM-yyyy hh:mm";

    public static long getCurrentTimeInMillis(){
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis();
    }

    public static String getFormattedDate(Long timeInMillis,String requiredDateFormat){
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(requiredDateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getMonthName(int month) {
        String monthName = "";

        switch (month){
            case 1:
                monthName = "JANUARY";
                break;
            case 2:
                monthName = "FEBRUARY";
                break;
            case 3:
                monthName = "MARCH";
                break;
            case 4:
                monthName = "APRIL";
                break;
            case 5:
                monthName = "MAY";
                break;
            case 6:
                monthName = "JUNE";
                break;
            case 7:
                monthName = "JULY";
                break;
            case 8:
                monthName = "AUGUST";
                break;
            case 9:
                monthName = "SEPTEMBER";
                break;
            case 10:
                monthName = "OCTOBER";
                break;
            case 11:
                monthName = "NOVEMBER";
                break;
            case 12:
                monthName = "DECEMBER";
                break;
                default:
                    monthName = "" ;


        }
        return monthName;
    }

    public static String getDayOfWeek(int dayOfWeek) {
        String monthName = "";

        switch (dayOfWeek) {
            case 1:
                monthName = "SUNDAY";
                break;
            case 2:
                monthName = "MONDAY";
                break;
            case 3:
                monthName = "TUESDAY";
                break;
            case 4:
                monthName = "WEDNESDAY";
                break;
            case 5:
                monthName = "THURSDAY";
                break;
            case 6:
                monthName = "FRIDAY";
                break;
            case 7:
                monthName = "SATURDAY";
                break;
            default:
                monthName = "";
                break;
        }

        return monthName;
    }
}

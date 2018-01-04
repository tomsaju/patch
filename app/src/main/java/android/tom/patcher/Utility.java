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
}

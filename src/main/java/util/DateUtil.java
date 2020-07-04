package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final SimpleDateFormat SDF=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SDF1=new SimpleDateFormat("YYYY-MM-dd");

  public static String convertDateToString(Date date){
        return SDF.format(date);
  }
  public static Date convertStringToDate(String date){
      try {
          return SDF.parse(date);
      } catch (ParseException e) {
          e.printStackTrace();
      }
      return null;
  } public static String convertDeadlineToString(Date date){
        return SDF1.format(date);
  }
  public static Date convertStringToDeadline(String date){
      try {
          return SDF1.parse(date);
      } catch (ParseException e) {
          e.printStackTrace();
      }
      return null;
  }
}

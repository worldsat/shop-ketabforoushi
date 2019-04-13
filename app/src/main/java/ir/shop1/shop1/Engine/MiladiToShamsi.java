package ir.shop1.shop1.Engine;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ir.shop1.shop1.otherClass.roozh;

public class MiladiToShamsi {

    public String convert(String RawDate) {

        SimpleDateFormat dateFormatBuild = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat dateYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        SimpleDateFormat dateMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        SimpleDateFormat dateDay = new SimpleDateFormat("dd", Locale.ENGLISH);
        String jCalTxt = "null";
        try {
            String now = changeNumber(RawDate);


            String MonthName = dateMonth.format(dateFormatBuild.parse(now.substring(0, 10)));
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            Calendar cal = Calendar.getInstance();
            cal.setTime(inputFormat.parse(MonthName));
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM", Locale.ENGLISH); // 01-12

            int month = Integer.valueOf(outputFormat.format(cal.getTime()));
            int day = Integer.valueOf(dateDay.format(dateFormatBuild.parse(now.substring(0, 10))));
            int Year = Integer.valueOf(dateYear.format(dateFormatBuild.parse(now.substring(0, 10))));
            roozh jCal = new roozh();
            jCal.GregorianToPersian(Year, month, day);
              jCalTxt = String.valueOf( jCal.getDay()+ Month(jCal.getMonth())  +jCal.getYear()  );
          //  jCalTxt = jCal.toString();

            Log.i("mohsenjamali", "FragmentFourDateError: " + jCalTxt);
        } catch (Exception e) {
            Log.i("mohsenjamali", "FragmentFourDateError: " + e.toString());
        }

        return jCalTxt;
    }

    private String changeNumber(String num) {
        num = num.replaceAll("۰", "0");
        num = num.replaceAll("۱", "1");
        num = num.replaceAll("۲", "2");
        num = num.replaceAll("۳", "3");
        num = num.replaceAll("۴", "4");
        num = num.replaceAll("۵", "5");
        num = num.replaceAll("۶", "6");
        num = num.replaceAll("۷", "7");
        num = num.replaceAll("۸", "8");
        num = num.replaceAll("۹", "9");
        return num;
    }

    private String Month(int month) {
        String monthTxt="";
        Log.i("mohsenjamali", "Month: "+month);
        switch (month) {
            case 1: {
                monthTxt = " فروردین ";
                break;
            }
            case 2: {
                monthTxt = " اردیبهشت ";
                break;
            }
            case 3: {
                monthTxt = " خرداد ";
                break;
            }
            case 4: {
                monthTxt = " تیر ";
                break;
            }
            case 5: {
                monthTxt = " مرداد ";
                break;
            }
            case 6: {
                monthTxt = " شهریور ";
                break;
            }
            case 7: {
                monthTxt = " مهر ";
                break;
            }
            case 8: {
                monthTxt = " آبان ";
                break;
            }
            case 9: {
                monthTxt = " آذر ";
                break;
            }
            case 10: {
                monthTxt = " دی ";
                break;
            }
            case 11: {
                monthTxt = " بهمن ";
                break;
            }
            case 12: {
                monthTxt = " اسفند ";
                break;
            }
        }

        return monthTxt;
    }
}

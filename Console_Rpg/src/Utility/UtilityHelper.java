package Utility;

public class UtilityHelper {

    // checks if a given string is whether an integer or not
    public static boolean isNumeric(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static double Round2(double d){
        return Math.round(d * 100)/ 100.0f;
    }

    public static String Decimal2(double d){
        return String.format("%.2f",Round2(d));
    }

    public static String CompressedString(String str){
        return str.toLowerCase().replace(" " , "");
    }


}

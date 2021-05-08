package Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static FileManager _master;
    public static FileManager Master(){
        if (_master == null){
            _master = new FileManager();
        }
        return _master;
    }

    static public ArrayList<String> ReadFileAsArray(String path){
        return ReadFileAsArray(path , 0);
    }

    static public ArrayList<String> ReadFileAsArray(String path, int skippedLines){
        ArrayList<String> lines = new ArrayList<String>();

        try {

            InputStream inputStream = FileManager.class.getResourceAsStream("/"+path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String row = "";
            while (null != (row = reader.readLine())){
                if (skippedLines > 0){
                    skippedLines -= 1;
                }else {
                    lines.add(row);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading " + path);
            e.printStackTrace();
        }

        return lines;
    }
//
//    static public ArrayList<String> ReadFileAsArray(String path, int skippedLines){
//        ArrayList<String> lines = new ArrayList<String>();
//
//        try {
//            File file = new File(path);
//            Scanner reader = new Scanner(file);
//            while (reader.hasNextLine()){
//                String row = reader.nextLine();
//                if (skippedLines > 0){
//                    skippedLines -= 1;
//                }else {
//                    lines.add(row);
//                }
//            }
//            reader.close();
//        }catch (FileNotFoundException e){
//            System.out.println("An error occurred while reading " + path);
//            e.printStackTrace();
//        }
//
//        return lines;
//    }

    // seems deprecated
    static public String Read_CSV(String path , boolean skipTitles){

        String result = "";

        try {

            File csvFile = new File(path);
            Scanner reader = new Scanner(csvFile);
            while (reader.hasNextLine()){
                String row = reader.nextLine();
                if (!skipTitles)
                {
                    result += row + "\n";
                }else {
                    skipTitles = false;
                }
            }
            reader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading " + path);
            e.printStackTrace();
        }

        return result;
    }

}

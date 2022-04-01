import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String out = System.getProperty("user.dir");
    
    public static void normalizeCSV(String src) throws FileNotFoundException { // normalizes .csv files

        File file = new File(out + "/" + src);
        Scanner sc = new Scanner(file);
        Scanner lineScanner;
        ArrayList<String> fileContents = new ArrayList<String>();

        while(sc.hasNext()) { // scans each line in file, adding each value separately from newline and breaks to arraylist
            lineScanner = new Scanner(sc.nextLine());
            lineScanner.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            while(lineScanner.hasNext()) {
                fileContents.add(lineScanner.next());
            }
            if(sc.hasNextLine()) {
                fileContents.add("\n");
            }
        }

        PrintWriter pw = new PrintWriter(file);
        for(int i = 1; i <= fileContents.size(); i++) { // scans through each index, converting to appropriate type and including commas where needed
            String content = fileContents.get(i - 1);
            boolean includeComma;
            if(i == fileContents.size()) { // checks to see if comma is needed based on breaks
                includeComma = false;
            } else {
                includeComma = !fileContents.get(i).equals("\n") && !fileContents.get(i - 1).equals("\n");
            }
            try { // parses each value from arraylist into it's data type and "normalizes" back to same file
                int val = Integer.parseInt(content);
                pw.printf("%+010d" + (includeComma ? "," : ""), val);
            } catch (NumberFormatException e) {
                try {
                    double val = Double.parseDouble(content);
                    if(val > 100 || val < 0.01)
                        pw.printf("%.2e" + (includeComma ? "," : ""), val);
                    else
                        pw.print(val + (includeComma ? "," : ""));
                } catch (NumberFormatException f) {
                    if(content.length() > 13)
                        pw.printf("%.10s..." + (includeComma ? "," : ""), content);
                    else if(content.length() == 0)
                        pw.print("N/A" + (includeComma ? "," : ""));
                    else
                        pw.print(content + (includeComma ? "," : ""));
                }
            }
        }
        pw.close();
        sc.close();
    }

    public static void normalizeTXT(String src) throws FileNotFoundException { // normalizes .txt file

        File file = new File(out + "/" + src);
        Scanner sc = new Scanner(file);
        Scanner lineScanner;
        ArrayList<String> fileContents = new ArrayList<String>();

        while(sc.hasNext()) { // scans each line in file, adding each value separately from newline and breaks to arraylist
            lineScanner = new Scanner(sc.nextLine());
            lineScanner.useDelimiter("\t");
            while(lineScanner.hasNext()) {
                fileContents.add(lineScanner.next());
            }
            if(sc.hasNextLine()) {
                fileContents.add("\n");
            }
        }

        PrintWriter pw = new PrintWriter(file);
        for(int i = 1; i <= fileContents.size(); i++) { // scans through each index, converting to appropriate type and including commas where needed
            String content = fileContents.get(i - 1);
            boolean includeComma;
            if(i == fileContents.size()) { // checks to see if comma is needed based on breaks
                includeComma = false;
            } else {
                includeComma = !fileContents.get(i).equals("\n") && !fileContents.get(i - 1).equals("\n");
            }
            try { // parses each value from arraylist into it's data type and "normalizes" back to same file
                int val = Integer.parseInt(content);
                pw.printf("%+010d" + (includeComma ? "\t" : ""), val);
            } catch (NumberFormatException e) {
                try {
                    double val = Double.parseDouble(content);
                    if(val > 100 || val < 0.01)
                        pw.printf("%.2e" + (includeComma ? "\t" : ""), val);
                    else
                        pw.print(val + (includeComma ? "\t" : ""));
                } catch (NumberFormatException f) {
                    if(content.length() > 13)
                        pw.printf("%.10s..." + (includeComma ? "\t" : ""), content);
                    else if(content.length() == 0)
                        pw.print("N/A" + (includeComma ? "\t" : ""));
                    else
                        pw.print(content + (includeComma ? "\t" : ""));
                }
            }
        }
        pw.close();
        sc.close();  
    }

    public static void writeToFile(File file, Scanner sc) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(file);

        if(file.getName().contains(".txt")) { // writes from .csv to .txt
            sc.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // delimiter for commas, ignore comma inside quotes
            while(sc.hasNext()) {
                try {
                    pw.write(sc.next() + (sc.hasNext() ? "\t" : ""));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else { // writes from .txt to .csv
            sc.useDelimiter("\t");
            while(sc.hasNext()) {
                try {
                    pw.write(sc.next() + (sc.hasNext() ? "," : ""));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        pw.close();
        sc.close();
    }

    public static void convertFile(String src, String dst) {
        
        Scanner sc;
        File file;

        try {

            File srcFileExists = new File(out + "/" + src);
            if(!srcFileExists.exists())
                throw new Exception("SOURCE FILE NOT FOUND, PLEASE CREATE ONE AND TRY AGAIN");

            if(src.contains(".csv")) { // convert from .txt to .csv
                if(dst.contains(".txt")) {
                    sc = new Scanner(new File(out + "/" + src));
                    file = new File(out + "/" + dst);
                    writeToFile(file, sc);
                } else if(dst.contains(".csv")) {
                    throw new Exception("SAME I/O, FILE'S CONTENTS DO NOT CHANGE");
                } else {
                    throw new Exception("CANNOT CONVERT FROM .csv TO NON .txt FILE");
                }
            }
            
            if(src.contains(".txt")) { // convert from .txt to .csv
                if(dst.contains(".csv")) {
                    sc = new Scanner(new File(out + "/" + src));
                    file = new File(out + "/" + dst);
                    writeToFile(file, sc);
                } else if(dst.contains(".txt")) {
                    throw new Exception("SAME I/O, FILE'S CONTENTS DO NOT CHANGE");
                } else {
                    throw new Exception("CANNOT CONVERT FROM .txt TO NON .csv FILE");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        
        Scanner user = new Scanner(System.in);

        while(true) {
            try {
                System.out.println(
                    "\nPlease enter one of the three commands:\n"
                    + "1) convert yourfilename.xxx yourfilename.yyy\n"
                    + "2) normalize yourfilename.xxx\n"
                    + "3) quit\n"
                );
                String[] str = user.nextLine().split(" ");
                switch(str[0]) { // input handler for each option
                    case "convert":
                        if(str.length == 3)
                            convertFile(str[1], str[2]);
                        else
                            throw new Exception("CORRECT COMMAND WITH INCORRECT FORMAT INPUTTED, PLEASE TRY AGAIN");
                        break;
                    case "normalize":
                        if(str.length == 2) {
                            if(str[1].contains(".csv"))
                                normalizeCSV(str[1]);
                            else
                                normalizeTXT(str[1]);
                        }
                        else
                            throw new Exception("CORRECT COMMAND WITH INCORRECT FORMAT INPUTTED, PLEASE TRY AGAIN");                     
                        break;
                    case "quit":
                        System.out.println("Terminating program...");
                        user.close();
                        System.exit(1);
                        break;
                    default:
                        throw new Exception("UNKNOWN COMMAND, PLEASE TRY AGAIN");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }
}
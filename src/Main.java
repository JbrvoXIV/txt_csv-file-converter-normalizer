import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static String out = System.getProperty("user.dir");
    
    public static void writeToFile(File file, Scanner sc) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(file);

        if(file.getName().contains(".txt")) {
            sc.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            while(sc.hasNext()) {
                try {
                    pw.write(sc.next() + (sc.hasNext() ? "\t" : ""));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
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
                String[] str = user.nextLine().split(" ");
                switch(str[0]) {
                    case "convert":
                        if(str.length == 3)
                            convertFile(str[1], str[2]);
                        else
                            throw new Exception("CORRECT COMMAND WITH INCORRECT FORMAT INPUTTED, PLEASE TRY AGAIN");
                        break;
                    case "normalize":
                        if(str.length == 2)
                            System.out.println("ENTERED NORMALIZE");
                        else
                            throw new Exception("CORRECT COMMAND WITH INCORRECT FORMAT INPUTTED, PLEASE TRY AGAIN");                     
                        System.out.println("OUTPUTTED NORMALIZE");
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
                System.out.println(e.getMessage());
            }
        }
    }
}
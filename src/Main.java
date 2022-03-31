import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static String out = System.getProperty("user.dir");
    public static Scanner sc;
    public static PrintWriter pw;
    public static File file;

    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner user = new Scanner(System.in);

        while(true) {
            try {
                String[] str = user.nextLine().split(" ");

                if(str[0].equals("convert")) {
                    
                    if(str[1].contains(".csv")) { // convert from .txt to .csv
                        if(str[2].contains(".txt")) {
                            sc = new Scanner(new File(out + "/" + str[1]));
                            sc.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                            file = new File(out + "/" + str[2]);
                            pw = new PrintWriter(file);
                            break;
                        } else if(str[2].contains(".csv")) {
                            throw new Exception("SAME I/O, FILE'S CONTENTS DO NOT CHANGE");
                        } else {
                            throw new Exception("CANNOT CONVERT FROM .csv TO NON .txt FILE");
                        }
                    }
                    
                    if(str[1].contains(".txt")) { // convert from .txt to .csv
                        if(str[2].contains(".csv")) {
                            sc = new Scanner(new File(out + "/" + str[1]));
                            sc.useDelimiter("\t");
                            file = new File(out + "/" + str[2]);
                            pw = new PrintWriter(file);
                            break;
                        } else if(str[2].contains(".txt")) {
                            throw new Exception("SAME I/O, FILE'S CONTENTS DO NOT CHANGE");
                        } else {
                            throw new Exception("CANNOT CONVERT FROM .txt TO NON .csv FILE");
                        }
                    }
                }

                if(str[0].equals("quit")) {
                    System.out.println("Terminating program...");
                    System.exit(1);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if(file.getName().contains(".txt")) {
            while(sc.hasNext()) {
                try {
                    pw.write(sc.next() + "\t");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            while(sc.hasNext()) {
                try {
                    pw.write(sc.next() + ",");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        pw.close();
        user.close();
    }
}
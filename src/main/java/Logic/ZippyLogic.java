package Logic;

import ZipAndUnzip.Zip;

import java.util.ArrayList;
import java.util.Scanner;

public class ZippyLogic {
    Scanner scanner = new Scanner(System.in);
    Zip zip = new Zip();
    String[] options = new String[] {"1. Zip file", "2. Zip multiple files", "3. Unzip file", "4. Zip Directory", "5. Unzip Directory", "6. Exit"};

    public void zippy() {
        String inputOption;

        welcomeMessage();

        while (true) {
            showOptions();

            inputOption = scanner.nextLine();

            switch (inputOption) {
                default:
                    System.out.println(">> Option not recognized...");
                    break;
                case "1": // 1. Zip file
                    System.out.println(">> Option selected: " + options[0]);

                    String path = getPathFromInput();

                    if (path != null) {
                        zip.zipFile(path);
                    }

                    break;

                case "2": // 2. Zip multiple files
                    System.out.println(">> Option selected: " + options[1]);

                    ArrayList<String> filesPaths = getPathsFromInput();

                    break;
                case "3": // 3. Unzip file
                    System.out.println(">> Option selected: " + options[2]);

                    break;
                case "4": // 4. Zip Directory
                    System.out.println(">> Option selected: " + options[3]);
                    break;
                case "5": // 5. Unzip Directory
                    System.out.println(">> Option selected: " + options[4]);
                    break;
                case "6": // 6. Exit
                    System.out.println(">> Option selected: " + options[5]);
                    return;
            }


        }

    }

    private void welcomeMessage() {
        System.out.println("-------------------");
        System.out.println(" Welcome to Zippy");
        System.out.println("-------------------");
    }

    private void showOptions() {
        System.out.println("Select option: ");

        for (String item : options) {
            System.out.println("--> " + item);
        }
    }

    private String getPathFromInput() {
        System.out.print("Add path of the file you want to zip: ");
        String path = scanner.nextLine();

        if (path != null) {
            return path;
        }
        return null;
    }

    private ArrayList<String> getPathsFromInput() {
        ArrayList<String> paths = new ArrayList<String>();
        boolean getPaths = true;

        while (getPaths) {
            String path = getPathFromInput();
            paths.add(path);

            System.out.println("Do you want to add another path? \n1. Yes\n2. No");
            String option = scanner.nextLine();

            switch (option){
                case "1":
                    break;
                case "2":
                    getPaths = false;
                    break;
            }
        }
        return paths;
    }



}

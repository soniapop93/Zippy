package Logic;

import java.util.Scanner;

public class ZippyLogic {
    Scanner scanner = new Scanner(System.in);
    String[] options = new String[] {"1. Zip file", "2. Unzip file", "3. Zip Directory", "4. Unzip Directory", "5. Exit"};

    public void zippy() {
        welcomeMessage();

        while (true) {
            showOptions();
            
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



}

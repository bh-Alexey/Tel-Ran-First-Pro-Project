import java.io.*;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {

        Scanner console = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        System.out.println("WELCOME TO PENSION AUDIT PROGRAM !!!");
        System.out.println("press ENTER to start..");
        scanner.nextLine();

        CommandMenu commandMenu = new CommandMenu();
        int choice;
        do {
            commandMenu.showMenu();
            choice = console.nextInt();
            commandMenu.answer(choice);
            commandMenu.standBy();
        }
        while (choice != 8);
    }
}
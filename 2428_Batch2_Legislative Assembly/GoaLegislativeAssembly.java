/*
 * Author: Olencia Fernandes
 * Roll no: 2428
 * Topic: Goa Legislative Assemby
 * Start Date: 15 July 2024
 * Modified Date: 22 july 2024
 * Description: To identify all the OOP concepts in the Goa Legislative Assembly
 */
package GoaLegislativeAssembly;
import java.util.Scanner;

/**
 * The GoaLegislativeAssembly class is the main entry point for the application.
 * It manages the legislative assembly's operations, including adding members,
 * handling bills, and displaying information based on user input.
 */
public class GoaLegislativeAssembly {
    
    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args command line arguments 
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AssemblyManager assemblyManager = new AssemblyManager();

        // Initialize Chief Minister and MLAs
        ChiefMinister cm1 = new ChiefMinister("Pramod Sawant", "Sanquelim", "BJP");
        MLA mla1 = new MLA("Shri. Nilesh Cabral", "Curchorim", "BJP");
        MLA mla2 = new MLA("Shri. Mauvin Godinho", "", "BJP");
        assemblyManager.addMla(cm1);
        assemblyManager.addMla(mla1);
        assemblyManager.addMla(mla2);

        // Initialize bills
        Bill bill1 = new Bill("The Goa Education Development Corporation (Amendment) Bill", "Government Bill", cm1, 2024);
        Bill bill2 = new Bill("The Goa Change of Name and Surname (Amendment) Bill", "Government Bill", mla1, 2022);
        Bill bill3 = new Bill("The Goa School Education (Amendment) Bill", "Government Bill", cm1, 2023);
        assemblyManager.addBill(bill1);
        assemblyManager.addBill(bill2);
        assemblyManager.addBill(bill3);

        // Displaying menu and processing user input
        boolean exit = false;
        while (!exit) {
            System.out.println(
                    "\nChoose an option:\n1. Display all bills made by a certain member\n2. Display all bills by term\n3. Council of Members\n4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    System.out.println("Enter member name:");
                    String memberName = scanner.nextLine();
                    assemblyManager.displayBillsByMla(memberName);
                    break;
                case 2:
                    System.out.println("Enter term:");
                    int term = scanner.nextInt();
                    assemblyManager.displayBillsByTerm(term);
                    break;
                case 3:
                    assemblyManager.displayAllMlas();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}

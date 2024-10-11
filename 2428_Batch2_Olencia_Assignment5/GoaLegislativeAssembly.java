// Author: Olencia Fernandes
// Roll no: 2429
// Topic: Goa Legislative Assembly
// Start Date: 15 July 2024
// Modified Date: 22 July 2024
// Description: To identify all the OOP concepts in the Goa Legislative Assembly


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Enum for Bill Types
enum BillType {
    GOVERNMENT, PRIVATE, FINANCE;
}

// Annotation for tracking modifications
@interface Modified {
    String date();
    String author();
}

// Base class for MLA
class MLA {
    private String name;
    private String constituency;
    private String party;

    public MLA(String name, String constituency, String party) {
        this.name = name;
        this.constituency = constituency;
        this.party = party;
    }

    public String getName() {
        return name;
    }

    public String getConstituency() {
        return constituency;
    }

    public String getParty() {
        return party;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    @Override
    public String toString() {
        return "MLA " + "\n" +
                "Name=" + name + "\n" +
                "Constituency=" + constituency + "\n" +
                "Party=" + party + "\n";
    }
}

// Single Inheritance
class ChiefMinister extends MLA {
    public ChiefMinister(String name, String constituency, String party) {
        super(name, constituency, party);
    }

    @Override
    public String toString() {
        return "Chief Minister " + "\n" +
                "Name=" + getName() + "\n" +
                "Constituency=" + getConstituency() + "\n" +
                "Party=" + getParty() + "\n";
    }
}

// Multilevel Inheritance
class Minister extends MLA {
    private String department;

    public Minister(String name, String constituency, String party, String department) {
        super(name, constituency, party);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Minister " + "\n" +
                "Name=" + getName() + "\n" +
                "Constituency=" + getConstituency() + "\n" +
                "Party=" + getParty() + "\n" +
                "Department=" + department + "\n";
    }
}

// Hybrid Inheritance
class Bill {
    private String title;
    private BillType type; // Using enumeration for Bill Type
    private MLA mla;
    private int term;

    public Bill(String title, BillType type, MLA mla, int term) {
        this.title = title;
        this.type = type;
        this.mla = mla;
        this.term = term;
    }

    public String getTitle() {
        return title;
    }

    public BillType getType() {
        return type;
    }

    public MLA getMla() {
        return mla;
    }

    public int getTerm() {
        return term;
    }

    public void displayInfo() {
        System.out.println(
                "\nBill Title: " + title + "\nType: " + type + "\nMember: " + mla.getName() + "\nTerm: " + term);
    }
}

// Hierarchical Inheritance
class Ordinance extends Bill {
    public Ordinance(String title, BillType type, MLA mla, int term) {
        super(title, type, mla, term);
    }

    @Override
    public void displayInfo() {
        System.out.println("Ordinance Info:");
        super.displayInfo();
    }
}

class AssemblyManager {
    private List<MLA> mlas;
    private List<Bill> bills;

    public AssemblyManager() {
        mlas = new ArrayList<>();
        bills = new ArrayList<>();
    }

    public void addMla(MLA mla) {
        mlas.add(mla);
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public List<MLA> getMlas() {
        return mlas;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void displayAllMlas() {
        for (MLA mla : mlas) {
            System.out.println(mla);
        }
    }

    public void displayBillsByMla(String mlaName) {
        boolean found = false;
        for (Bill bill : bills) {
            if (bill.getMla().getName().equalsIgnoreCase(mlaName)) {
                bill.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bills found for member: " + mlaName);
        }
    }

    public void displayBillsByTerm(int term) {
        boolean found = false;
        for (Bill bill : bills) {
            if (bill.getTerm() == term) {
                bill.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bills found for term: " + term);
        }
    }
}

// Main class
public class GoaLegislativeAssembly {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AssemblyManager assemblyManager = new AssemblyManager();

        // Creating instances of MLAs
        ChiefMinister cm1 = new ChiefMinister("Pramod Sawant", "Sanquelim", "BJP");
        Minister minister1 = new Minister("Shri. Nilesh Cabral", "Curchorim", "BJP", "Environment");
        MLA mla1 = new MLA("Shri. Mauvin Godinho", "Fatorda", "BJP");
        assemblyManager.addMla(cm1);
        assemblyManager.addMla(minister1);
        assemblyManager.addMla(mla1);

        // Creating instances of Bills
        Bill bill1 = new Bill("The Goa Education Development Corporation (Amendment) Bill", BillType.GOVERNMENT, cm1, 2024);
        Bill bill2 = new Bill("The Goa Change of Name and Surname (Amendment) Bill", BillType.GOVERNMENT, minister1, 2022);
        Ordinance ordinance1 = new Ordinance("The Goa Forest (Amendment) Ordinance", BillType.PRIVATE, mla1, 2023);
        assemblyManager.addBill(bill1);
        assemblyManager.addBill(bill2);
        assemblyManager.addBill(ordinance1);

        // Displaying menu and processing user input
        boolean exit = false;
        while (!exit) {
            System.out.println(
                    "\nChoose an option:\n1. Display all bills made by a certain member\n2. Display all bills by term\n3. Council of Members\n4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
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
        scanner.close();
    }
}


import java.util.ArrayList;
import java.util.List;

/**
 * The AssemblyManager class manages a collection of Members of the Legislative Assembly (MLAs)
 * and their associated bills. It provides methods to add MLAs and bills, as well as display
 * information about them.
 */
public class AssemblyManager {
    private List<MLA> mlas;      // List of MLAs
    private List<Bill> bills;     // List of bills

    /**
     * Constructs an AssemblyManager with empty lists of MLAs and bills.
     */
    public AssemblyManager() {
        mlas = new ArrayList<>();
        bills = new ArrayList<>();
    }

    /**
     * Adds an MLA to the assembly manager.
     *
     * @param mla the MLA to be added
     */
    public void addMla(MLA mla) {
        mlas.add(mla);
    }

    /**
     * Adds a bill to the assembly manager.
     *
     * @param bill the bill to be added
     */
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    /**
     * Returns the list of MLAs managed by the assembly manager.
     *
     * @return a list of MLAs
     */
    public List<MLA> getMlas() {
        return mlas;
    }

    /**
     * Returns the list of bills managed by the assembly manager.
     *
     * @return a list of bills
     */
    public List<Bill> getBills() {
        return bills;
    }

    /**
     * Displays all MLAs managed by the assembly manager.
     */
    public void displayAllMlas() {
        for (MLA mla : mlas) {
            System.out.println(mla);
        }
    }

    /**
     * Displays all bills proposed by a specified MLA.
     *
     * @param mlaName the name of the MLA whose bills are to be displayed
     */
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

    /**
     * Displays all bills for a specified term.
     *
     * @param term the term for which bills are to be displayed
     */
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

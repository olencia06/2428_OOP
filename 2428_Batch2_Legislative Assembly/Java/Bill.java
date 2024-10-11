/**
 * The Bill class represents a legislative bill in the assembly.
 * It contains information about the bill's title, type, the MLA who proposed it,
 * and the term during which it was proposed.
 */

public class Bill {
    private String title; // Title of the bill
    private String type;  // Type of the bill (e.g., Government Bill)
    private MLA mla;      // MLA who proposed the bill
    private int term;     // Term during which the bill was proposed

    /**
     * Constructs a Bill with the specified title, type, MLA, and term.
     *
     * @param title the title of the bill
     * @param type the type of the bill
     * @param mla the MLA who proposed the bill
     * @param term the term during which the bill was proposed
     */
    public Bill(String title, String type, MLA mla, int term) {
        this.title = title;
        this.type = type;
        this.mla = mla;
        this.term = term;
    }

    /**
     * Returns the title of the bill.
     *
     * @return the title of the bill
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the type of the bill.
     *
     * @return the type of the bill
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the MLA who proposed the bill.
     *
     * @return the MLA associated with the bill
     */
    public MLA getMla() {
        return mla;
    }

    /**
     * Returns the term during which the bill was proposed.
     *
     * @return the term of the bill
     */
    public int getTerm() {
        return term;
    }

    /**
     * Displays the information of the bill in a formatted manner.
     */
    public void displayInfo() {
        System.out.println(
                "\nBill Title: " + title +
                "\nType: " + type +
                "\nMember: " + mla.getName() +
                "\nTerm: " + term);
        System.out.println();
    }
}

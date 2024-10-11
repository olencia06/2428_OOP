/**
 * The MLA class represents a Member of the Legislative Assembly (MLA).
 * It contains information about the MLA's name, constituency, and political party.
 */

public class MLA {
    private String name;           // Name of the MLA
    private String constituency;   // Constituency represented by the MLA
    private String party;          // Political party of the MLA

    /**
     * Constructs an MLA with the specified name, constituency, and party.
     *
     * @param name         the name of the MLA
     * @param constituency the constituency of the MLA
     * @param party        the political party of the MLA
     */
    public MLA(String name, String constituency, String party) {
        this.name = name;
        this.constituency = constituency;
        this.party = party;
    }

    /**
     * Returns the name of the MLA.
     *
     * @return the name of the MLA
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the constituency of the MLA.
     *
     * @return the constituency of the MLA
     */
    public String getConstituency() {
        return constituency;
    }

    /**
     * Returns the political party of the MLA.
     *
     * @return the party of the MLA
     */
    public String getParty() {
        return party;
    }

    /**
     * Sets the name of the MLA.
     *
     * @param name the new name of the MLA
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the constituency of the MLA.
     *
     * @param constituency the new constituency of the MLA
     */
    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    /**
     * Sets the political party of the MLA.
     *
     * @param party the new party of the MLA
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * Returns a string representation of the MLA.
     *
     * @return a string containing the name, constituency, and party of the MLA
     */
    @Override
    public String toString() {
        return "MLA " + "\n" +
                "Name=" + name + "\n" +
                "Constituency=" + constituency + "\n" +
                "Party=" + party + "\n";
    }
}

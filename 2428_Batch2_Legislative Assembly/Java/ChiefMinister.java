/**
 * The ChiefMinister class represents a Chief Minister, 
 * which is a specialized type of Member of the Legislative Assembly (MLA).
 * It inherits properties from the MLA class and adds no additional fields.
 */

public class ChiefMinister extends MLA {
    
    /**
     * Constructs a ChiefMinister with the specified name, constituency, and party.
     *
     * @param name the name of the Chief Minister
     * @param constituency the constituency represented by the Chief Minister
     * @param party the political party of the Chief Minister
     */
    public ChiefMinister(String name, String constituency, String party) {
        super(name, constituency, party);
    }

    /**
     * Returns a string representation of the Chief Minister, including
     * their name, constituency, and party.
     *
     * @return a formatted string describing the Chief Minister
     */
    @Override
    public String toString() {
        return "Chief Minister " + "\n" +
                "Name=" + getName() + "\n" +
                "Constituency=" + getConstituency() + "\n" +
                "Party=" + getParty() + "\n";
    }
}

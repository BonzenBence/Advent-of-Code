public class GameCounter extends MainPart2 {

    private int numbersMatch;
    private int copies;

    /**
     * Counts Matched numbers and how mand Copies+
     * @param numbersMatch
     * @param copies
     */
    public GameCounter(int numbersMatch,int copies){
        this.numbersMatch = numbersMatch;
        this.copies = 1;

    }

    public void setNumbersMatch(int numbersMatch) {
        this.numbersMatch = numbersMatch;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getCopies() {
        return copies;
    }

    public int getNumbersMatch() {
        return numbersMatch;
    }
}

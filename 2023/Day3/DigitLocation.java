public class DigitLocation {
    char digit;
    int rowIndex;
    int colIndex;

    DigitLocation(char digit, int rowIndex, int colIndex) {
        this.digit = digit;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public char getDigit() {
        return digit;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }
}


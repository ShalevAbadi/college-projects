import java.util.Scanner;

public class StringInstruments extends AfekaInstruments {

	protected int numOfStrings;

	public StringInstruments(Scanner s) throws Exception {
		super(s);
		throwIfNumOfStringsNotMentioned(s);
		setNumOfStrings(s.nextInt());
		s.nextLine();
	}

	public void throwIfNumOfStringsNotMentioned(Scanner s) throws Exception {
		if (!s.hasNextInt()) {
			throw new Exception("Num of strings didn't mentioned");
		}
	}

	public StringInstruments(String brand, double price, int numOfStrings) throws Exception {
		super(brand, price);
		setNumOfStrings(numOfStrings);
	}

	public void setNumOfStrings(int numOfStrings) {
		this.numOfStrings = numOfStrings;
	}

	public int getNumOfStrings() {
		return this.numOfStrings;
	}

	@Override
	public boolean equals(Object other) {
		return super.equals(other) && isStringInstrument(other) && isEqualNumOfStrings(other);
	}

	public boolean isStringInstrument(Object other) {
		return other instanceof StringInstruments;
	}

	public boolean isEqualNumOfStrings(Object other) {
		return (((StringInstruments) other).getNumOfStrings() == this.getNumOfStrings());
	}

	@Override
	public String toString() {
		return (super.toString() + ", Number of strings:	" + getNumOfStrings() + "|");

	}
}

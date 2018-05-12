import java.util.ArrayList;

public class AfekaInventory<T extends MusicalInstrument> implements StorageManagement<T> {
	private ArrayList<T> instruments = new ArrayList<>();
	private double totalPrice;
	private boolean isSorted;

	public ArrayList<T> getInstruments() {
		return instruments;
	}

	public void setInstruments(ArrayList<T> arr) {
		this.instruments = arr;
	}

	public Number getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setTotalPrice() {
		double sum = 0;
		for (int i = 0; i < instruments.size(); i++) {
			sum = genericSum(sum, instruments.get(i).getPrice());
		}
		this.totalPrice = sum;
	}

	public boolean getIsSorted() {
		return isSorted;
	}

	public void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}

	public <N1 extends Number, N2 extends Number> double genericSum(N1 num1, N2 num2) {
		return num1.doubleValue() + num2.doubleValue();

	}

	@Override
	public void addAllStringInstruments(ArrayList<? extends MusicalInstrument> src, ArrayList<? super MusicalInstrument> dest) {
		for (int i = 0; i < src.size(); i++) {
			if (src.get(i) instanceof StringInstrument) {
				dest.add(src.get(i));
			}
		}
		isSorted = false;
		setTotalPrice();
	}

	@Override
	public void addAllWindInstruments(ArrayList<? extends MusicalInstrument> src, ArrayList<? super MusicalInstrument> dest) {
		for (int i = 0; i < src.size(); i++) {
			if (src.get(i) instanceof WindInstrument) {
				dest.add(src.get(i));
			}
		}
		isSorted = false;
		setTotalPrice();
	}

	public void SortByBrandAndPrice(ArrayList<MusicalInstrument> list) {
		MusicalInstrument temp;
		for (int i = 1; i < list.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (list.get(j).compareTo(list.get(j - 1)) < 0) {
					temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
			}
		}
		isSorted = true;
	}

	@Override
	public int binnarySearchByBrandAndPrice(ArrayList<MusicalInstrument> list, String brand, Number price) {
		int high = list.size() - 1, low = 0, middle;
		while (high >= low) {
			middle = (high + low) / 2;
			int compareResult = list.get(middle).getBrand().compareTo(brand);
			if (compareResult == 0) {
				return secondBinnarySearch(list, brand, price, high, low, middle);
			} else if (compareResult > 0) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}

	private int secondBinnarySearch(ArrayList<MusicalInstrument> list, String brand, Number price, int high, int low,
			int middle) {
		int res = middle;
		while (high >= low) {
			middle = (high + low) / 2;
			int compareBrands = list.get(middle).getBrand().compareTo(brand);
			if (compareBrands == 0 || list.get(middle).getPrice() == price) {
				return middle;
			} else if (compareBrands > 0 || list.get(middle).getPrice().doubleValue() > price.doubleValue()) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return res;
	}

	@Override
	public void addInstrument(ArrayList<? super MusicalInstrument> list, T instrument) {
		list.add(instrument);
		setTotalPrice();
	}

	@Override
	public boolean removeInstrument(ArrayList<MusicalInstrument> list, T instrument) {
		boolean remove = list.remove(instrument);
		setTotalPrice();
		return remove;
	}

	@Override
	public boolean removeAll(ArrayList<MusicalInstrument> list) {
		boolean remove = list.removeAll(list);
		setTotalPrice();
		return remove;
	}

	@Override
	public String toString() {
		return "------------------------------------------------------------------------- \n"
				+ "AFEKA MUSICAL INSTRUMENTS INVENTORY \n"
				+ "------------------------------------------------------------------------- \n" + instrumentsToString()
				+ "\n" + "Total Price:" + String.format("%.2f %4s Sorted: %b", totalPrice, "", isSorted);
	}

	private String instrumentsToString() {
		String res = "";
		if (instruments.size() == 0) {
			res += "There Is No Instruments To Show\n";
		} else {
			for (int i = 0; i < instruments.size(); i++) {
				res += (instruments.get(i).toString() + "\n");
			}
		}
		return res;
	}

	
}
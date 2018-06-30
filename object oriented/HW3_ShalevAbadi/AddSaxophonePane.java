
public class AddSaxophonePane extends AddInstrumentPane{

	@Override
	public MusicalInstrument getInstrumentToAdd() {
		String brand = brandField.getText();
		int price = Integer.parseInt(priceField.getText());
		return new Saxophone(brand, price);
	}
	
	
}

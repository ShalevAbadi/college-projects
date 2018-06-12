
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class AddGuitarPane extends AddStringInstrumentPane {
	
	Text type = new Text("Guitar Type:");
	final static ObservableList<String> TYPES_LIST = FXCollections.observableArrayList(Guitar.GUITAR_TYPE);
	private ComboBox<String> typesCombeBox = new ComboBox<>(TYPES_LIST);
	
	public AddGuitarPane() {
		typesCombeBox.setPromptText("Type");
		addRow(getRowCount(), type, typesCombeBox);
		brandPrompt = "Ex: Gibson";
		pricePrompt = "Ex: 7500";
		numberOfStringPrompt = "Ex: 6";
		setPrompts();
	}
}

package View.update;


import Control.Logic.SystemTblLogic;
import Exceptions.MissingInputException;
import Model.SystemTbl;
import View.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class systemParametsController {

	@FXML
	private AnchorPane addReco;

	@FXML
	private Button back;

	@FXML
	private TextField t1;

	@FXML
	private TextField t2;

	@FXML
	private TextField t3;

	@FXML
	private TextField t4;

	@FXML
	private Button saveButton;

	@FXML
	private Label labelSuccess;

	@FXML
	private TextField t5;

	@FXML
	private TextField t6;

	@FXML
	private TextField t7;

	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}

	@FXML
	void saveProduct(ActionEvent event) throws MissingInputException{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Update Product");
		alert.setHeaderText("");

		String t11 = t1.getText();
		String t22 = t2.getText();
		String t33 = t3.getText();
		String t44 = t4.getText();
		String t55 = t5.getText();
		String t66 = t6.getText();
		String t77 = t7.getText();


		try {

			if (t11.isEmpty()) {
				throw new MissingInputException("TransactionMinSize");
			}
			if (t22.isEmpty()) {
				throw new MissingInputException("TransactionMaxSize");
			}
			if (t33.isEmpty()) {
				throw new MissingInputException("TransactionSizeForExpansion");
			}
			if (t44.isEmpty()) {
				throw new MissingInputException("PriceForExpansion");
			}
			if (t55.isEmpty()) {
				throw new MissingInputException("DiscountPercentPerFee");
			}
			if (t66.isEmpty()) {
				throw new MissingInputException("PriceForDiscount");
			}
			if (t77.isEmpty()) {
				throw new MissingInputException("TransactionSizeFree");
			}

			int t111 = Integer.parseInt(t11);
			int t222 = Integer.parseInt(t22);
			int t333 = Integer.parseInt(t33);
			int t444 = Integer.parseInt(t44);
			Double t555 = Double.parseDouble(t55);
			int t666 = Integer.parseInt(t66);
			int t777 = Integer.parseInt(t77);

			if (SystemTblLogic.getInstance().updateSystemParameters("11", t111, t222, t333, t444, t555, t666, t777)) {
				alert.setHeaderText("Success");
				alert.setContentText("System Parmeterss Updated succesfully!");
				alert.show();			
				initialize();
			} else {
				alert.setHeaderText("Unable to update.");
				alert.setContentText("Sorry, please try again later.");
				alert.show();
			}


		} catch (MissingInputException e) {
		}


	}



	//Initialize the combobox with the available categories
	public void initialize() {
		SystemTbl sys = SystemTblLogic.getInstance().getALLSystemParameters("11");
		t1.setText(String.valueOf(sys.getTransactionMinSize()));
		t2.setText(String.valueOf(sys.getTransactionMaxSize()));
		t3.setText(String.valueOf(sys.getTransactionSizeForExpansion()));
		t4.setText(String.valueOf(sys.getPriceForExpansion()));
		t5.setText(String.valueOf(sys.getDiscountPercentPerFee()));
		t6.setText(String.valueOf(sys.getPriceForDiscount()));
		t7.setText(String.valueOf(sys.getTransactionSizeFree()));

		//Forces numbers only
		t7.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				t7.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		//Forces numbers only
		t1.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				t1.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});


		//Forces numbers only
		t2.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				t2.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		//Forces numbers only
		t3.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				t3.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		//Forces numbers only
		t4.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				t4.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		//Forces numbers only
		t5.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				t5.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});

		//Forces numbers only
		t6.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				t6.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});



	}

}
package View.add;


import java.util.ArrayList;
import Control.SysData;
import Control.Logic.WalletLogic;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.User;
import View.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class addBSWalletController {

	@FXML
	private AnchorPane addReco;

	@FXML
	private ImageView btcAmount;

	@FXML
	private Button back;

	@FXML
	private TextField amount;

	@FXML
	private ComboBox<Integer> category;

	@FXML
	private Button addButton;

	@FXML
	private Button clearButton;

	@FXML
	private Label labelSuccess;

	@FXML
	private CheckBox cbOnComp;

	@FXML
	private CheckBox cbOnSmartPhone;

	@FXML
	private CheckBox cbOnTablet;

	@FXML
	private TextField generatedPrice;

	@FXML
	void calculatePrice(ActionEvent event) {
		if (!category.getSelectionModel().isEmpty()) {
			double calculated = 0.0;
			calculated = ((category.getSelectionModel().getSelectedItem().intValue())*(WalletLogic.getInstance().getPriceForExpansion()))/100; //convert satoshi to btc(dividing by 100 instead of 100mil so numbers show up.)
			generatedPrice.setText(String.valueOf(calculated));
		}
	}

	@FXML
	void addBSWallet(ActionEvent event) throws MissingInputException, ListNotSelectedException{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Product");
		alert.setHeaderText("");

		SysData.getInstance();
		User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
		String addrs = loggedIn.getPublicAddress();
		String signt = loggedIn.getUserSignature();


		try {
			double calculated = 0.0;
			if (category.getSelectionModel().isEmpty()) {
				throw new ListNotSelectedException("Transaction Size");
			}
			else {
				calculated = ((category.getSelectionModel().getSelectedItem().intValue())*(WalletLogic.getInstance().getPriceForExpansion()))/100; //convert satoshi to btc(dividing by 100 instead of 100mil so numbers show up.)
				generatedPrice.setText(String.valueOf(calculated));
			}

			if (amount.getText().isEmpty()) {
				throw new MissingInputException("Amount");
			}

			double finalPrice = Double.parseDouble(generatedPrice.getText());
			double newAmount = Double.parseDouble(amount.getText());
			Boolean cbOnCompCheck = cbOnComp.isSelected();
			Boolean cbOnSmartPhoneCheck = cbOnSmartPhone.isSelected();
			Boolean cbOnTabletCheck = cbOnTablet.isSelected();

			calculated = ((category.getSelectionModel().getSelectedItem().intValue())*(WalletLogic.getInstance().getPriceForExpansion()))/100; //convert satoshi to btc(dividing by 100 instead of 100mil so numbers show up.)
			
			String newWalletAddress = WalletLogic.getInstance().addWallet(finalPrice, cbOnCompCheck, cbOnSmartPhoneCheck, cbOnTabletCheck, newAmount-calculated, 0, addrs, signt);
			if (newWalletAddress!=null && newAmount-calculated>=0) {
				if( WalletLogic.getInstance().addBSWallet(newWalletAddress, category.getSelectionModel().getSelectedItem())){
					alert.setHeaderText("Success");
					alert.setContentText("You have purchased a new BS Wallet. Generated Address: " + newWalletAddress);
					alert.show();			
					initialize();
					category.getSelectionModel().selectFirst();
				}
			} else {
				alert.setHeaderText("Error");
				alert.setContentText("Unable to Make this Purchase");
				alert.show();
			}


		} catch (ListNotSelectedException e) {
		} catch (MissingInputException e) {
		}
	}

	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}


	@FXML
	void clearForm(ActionEvent event) {
		initialize();
		category.getSelectionModel().selectFirst();

	}

	//Initialize the combobox and fields
	public void initialize() {
		amount.setText("");
		cbOnComp.setSelected(false);
		cbOnSmartPhone.setSelected(false);
		cbOnTablet.setSelected(false);
		generatedPrice.setText("");
		labelSuccess.setText("");

		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for(int i=WalletLogic.getInstance().getTransactionMinSize(); i<=WalletLogic.getInstance().getTransactionMaxSize(); i+=5)
			sizes.add(i);

		category.getItems().setAll(sizes);

		amount.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^\\\\d*\\\\.\\\\d+|\\\\d+\\\\.\\\\d*$")) {
				amount.setText(newValue.replaceAll("^\\\\d*\\\\.\\\\d+|\\\\d+\\\\.\\\\d*$", ""));
			}
		});

	}

}
package View.add;

import Control.Logger;
import Control.Logic.RecommendationLogic;
import Control.Logic.UserLogic;
import Exceptions.EmailNotValidException;
import Exceptions.MissingInputException;
import View.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class addUserController {

    @FXML
    private Button clearButton;

    @FXML
    private TextField publicAdd;

    @FXML
    private TextField phone;

    @FXML
    private PasswordField pass;

    @FXML
    private AnchorPane addReco;

    @FXML
    private Button back;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userSig;

    @FXML
    private Label labelSuccess;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

	/**
	 * goes back to previous page
	 * 
	 * @param event back button is pressed
	 */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    
	@FXML
	/**
	 * Adds a new User once the sign up button is pressed
	 * 
	 * @param event  button is pressed
	 * @throws IOException           input exception might occur
	 * @throws MissingInputException missing input exception 
	 * @throws EmailNotValidException if email is not valid
	 *    
	 */
    void addUser(ActionEvent event) throws MissingInputException, EmailNotValidException {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sign Up");
		alert.setHeaderText("");

		String user = username.getText();
		String psw = pass.getText();
		String cell = phone.getText();
		String emailAdd = email.getText();
				
		try {

			if (user.isEmpty()) {
				throw new MissingInputException("Username");
			}
			if (psw.isEmpty()) {
				throw new MissingInputException("Password");
			}
			
			if (cell.isEmpty()) {
				throw new MissingInputException("Cell Phone");
			}
			if (emailAdd.isEmpty()) {
				throw new MissingInputException("Email");
			}

			if(!isEmail(emailAdd)) {
				throw new EmailNotValidException();
			}
			
			//Check here that username is not taken++++++++++++++++++++++++++++++++
			//Generate a random and not taken userSig and userAdd
			//then call this if below
			
			if (UserLogic.getInstance().addUser(publicAddress, userSignature, user, psw, emailAdd, cell))) {
				Logger.log("Successful sign up");
				initialize();
				labelSuccess.setText("Thank you for signing up! Your public Address and Signature are:");

				//EDIT THIS to show generated values+++++++++++++++++
				userSig.setText("");
				publicAdd.setText("");	
				userSig.setVisible(true);
				publicAdd.setVisible(true);
				
			} else {
				Logger.log("Error signing up user");
				alert.setHeaderText("Sign Up Failed");
				alert.setContentText("We are sorry, please try again later.");
				alert.show();
			}


		} catch (MissingInputException e) {
		}
		catch (EmailNotValidException e) {
		}
    }

	public void initialize() {
		userSig.setVisible(false);
		publicAdd.setVisible(false);
		
		userSig.setText("");
		publicAdd.setText("");	
		phone.setText("");
		pass.setText("");
		email.setText("");
		username.setText("");
		
		labelSuccess.setText("");
		

		phone.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("^([0-9]+\\\\.?[0-9]*|[0-9]*\\\\.[0-9]+)$")) {
				phone.setText(newValue.replaceAll("^([0-9]+\\\\.?[0-9]*|[0-9]*\\\\.[0-9]+)$", ""));
			}
		});
		

		userSig.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				publicAdd.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

	}
	
	//Checks if email is valid
	public boolean isEmail(String s) {
	    return s.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
	}
	
	/**
	 * Clears everything in the form.
	 * @param event when clear button is pressed.
	 */
    @FXML
    void clearForm(ActionEvent event) {
		userSig.setVisible(false);
		publicAdd.setVisible(false);
		
		userSig.setText("");
		publicAdd.setText("");	
		phone.setText("");
		pass.setText("");
		email.setText("");
		username.setText("");
		
		labelSuccess.setText("");
    }


}

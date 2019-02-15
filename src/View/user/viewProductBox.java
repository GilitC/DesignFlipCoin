package View.user;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;

public class viewProductBox {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="dialogPane"
    private DialogPane dialogPane; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert dialogPane != null : "fx:id=\"dialogPane\" was not injected: check your FXML file 'viewProductBox.fxml'.";

    }
}

package View.user;

import java.util.ArrayList;

import Control.SysData;
import Control.Logic.WalletLogic;
import Model.BKWallet;
import Model.BSWallet;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import View.WindowManager;

public class viewMyWalletsController {


	@FXML
	private AnchorPane viewRecommend;

	@FXML
	private Button back;

	@FXML
	private ListView<BSWallet> listbswallets;

	@FXML
	private ListView<BKWallet> listbkwallets;

	User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
	private String addrs = loggedIn.getPublicAddress();
	private String signt = loggedIn.getUserSignature();

	public void initialize() {

		ObservableList<BSWallet> bsw;
		ObservableList<BKWallet> bck;
		
		ArrayList<BSWallet> bs = WalletLogic.getInstance().getAllMyBCSpaceWallets(addrs, signt);
		ArrayList<BKWallet> bk = WalletLogic.getInstance().getAllMyBCKnoltsWallets(addrs, signt);
		
		if(!bs.isEmpty()) {
			  bsw = FXCollections.observableArrayList(bs);
			  listbswallets.setItems(bsw);
			}
		
		if(!bk.isEmpty()) {
			  bck = FXCollections.observableArrayList(bk);
			  listbkwallets.setItems(bck);
			}
		
	}

	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}
}



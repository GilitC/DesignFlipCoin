package View.user;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Control.ImageLoader;
import Control.SysData;
import Control.Logic.ProductLogic;
import Model.Product;
import Model.User;
import View.WindowManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class viewItemsForSaleContoller {

	public static List<Product> productList;
	
	private static final Object GRID_LOCK = new Object();
	private Integer id = 0;
	@FXML
	public FlowPane flowPane;

	public Set<Integer> _selectedItems;

	@FXML
	private Button back;

	@FXML
	public Text totalAmount;

	@FXML
	public GridPane gridPane;

	@FXML
	public Text totalPrice;

	@FXML
	private ScrollPane scp;

	@FXML
	private AnchorPane viewRecommend;

	User loggedIn = SysData.getLoggedInUser(); // Get the logged In User
	private String addrs = loggedIn.getPublicAddress();
	private String signt = loggedIn.getUserSignature();

	public void initialize() {

		     /*ColumnConstraints col1 = new ColumnConstraints();
		     col1.setPercentWidth(25);
		     ColumnConstraints col2 = new ColumnConstraints();
		     col2.setPercentWidth(50);
		     ColumnConstraints col3 = new ColumnConstraints();
		     col3.setPercentWidth(25);
		     gridPane.getColumnConstraints().addAll(col1,col2,col3);*/
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		scp.setBackground(Background.EMPTY);
		_selectedItems = new HashSet<>();
		scp.setFitToHeight(true);

		if(productList == null)
		{
			productList = ProductLogic.getInstance().getProdListWITHOUTSellingUser(addrs, signt);
		}
		
		updateProducts(productList);

	}

	@FXML
	void openSearch(ActionEvent event) {
//		WindowManager.goBack();
		// Open a popup with search
		
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search.fxml"));
		    Parent root1 = (Parent) fxmlLoader.load();
		    Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.initStyle(StageStyle.UNDECORATED);
		    stage.setTitle("Search Products");
		    stage.setScene(new Scene(root1));  
		    stage.show();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	private ImageView getLoadingImage() {
		ImageView loadingAnimation = new ImageView();
		loadingAnimation.setImage(createImage(this.getClass().getResource("loading.gif").toExternalForm()));
		loadingAnimation.setFitWidth(20);
		loadingAnimation.setFitHeight(20);
		loadingAnimation.setPreserveRatio(true);
		return loadingAnimation;
	}

	private synchronized int getNextFreeSpot() {
		return id++;
	}

	private void updateProducts(List<Product> productsList) {
		int row = 0;
		gridPane.getChildren().removeAll();
		id = 0;
		for (int i = 0; i < 10; i++) {

			for (Product p : productsList) {
				ImageLoader task = new ImageLoader(p);

				
				
				task.setOnSucceeded((succeededEvent) -> {
					ImageView image = task.getValue();
					image.setId(String.valueOf(id));
					image.setUserData(p);
					image.setFitWidth(130);
					image.setFitHeight(100);
					image.setPreserveRatio(true);
					ColorAdjust effect = new ColorAdjust();
					effect.setBrightness(0.5);
					image.setEffect(effect);

					VBox vbox = new VBox();
					HBox hbox = new HBox();
					hbox.setAlignment(Pos.CENTER_LEFT);
					hbox.setPadding(new Insets(0, 20, 0, 0));
					vbox.setPadding(new Insets(0, 0, 20, 0));
					Text productTitle = new Text("Name: " + p.getProductName());
					Text productPrice = new Text("Price: " + p.getPricePerUnit()+" BTC / unit");
					Text productSellerID = new Text("Seller: " + p.getSellerSignature());
					vbox.getChildren().add(0, productTitle);
					vbox.getChildren().add(1, productPrice);
					vbox.getChildren().add(2, productSellerID);
					
					
					hbox.getChildren().add(image);
					hbox.getChildren().add(vbox);


					synchronized (GRID_LOCK) {
						int thisItemID = getNextFreeSpot();
						System.out.println("Item ID: " + thisItemID);
						int testX = (int) (thisItemID % 2);
						int testY = (int) (thisItemID / 2);
						System.out.println("ID: " + thisItemID + " (" + testX + "," + testY + ")");
						gridPane.add(hbox, testX, testY);
					}

					image.setOnMouseClicked(event -> {
						// Update cart

						Integer imageID = Integer.parseInt(image.getId());
						if (_selectedItems.contains(imageID)) {
							_selectedItems.remove(imageID);
							ColorAdjust blackout = new ColorAdjust();
							blackout.setBrightness(0.5);
							image.setEffect(blackout);
							image.setCache(true);

							image.setCacheHint(CacheHint.SPEED);
						} else {
							_selectedItems.add(imageID);
							ColorAdjust blackout = new ColorAdjust();
							blackout.setBrightness(0);
							image.setEffect(blackout);
							image.setCache(true);
							image.setCacheHint(CacheHint.SPEED);
						}
						displaySelected();

					});

				});
				ExecutorService executorService = Executors.newSingleThreadExecutor();
				executorService.execute(task);
				executorService.shutdown();
			}
		}
	}

	private Image createImage(String url) {
		// You have to set an User-Agent in case you get HTTP Error 403
		// respond while you trying to get the Image from URL.
		URLConnection conn = null;
		try {
			conn = new URL(url).openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.setRequestProperty("User-Agent", "Wget/1.13.4 (linux-gnu)");

		try (InputStream stream = conn.getInputStream()) {
			return new Image(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void displaySelected() {
		Double sumPrice = 0.0;
		for (Integer i : _selectedItems) {
			HBox theBox = (HBox) gridPane.getChildren().get(i);
			Product p = (Product) theBox.getChildren().get(0).getUserData();
			sumPrice += p.getPricePerUnit();
		}
		totalAmount.setText("" + _selectedItems.size());
		totalPrice.setText("$" + sumPrice.toString());
	}
}

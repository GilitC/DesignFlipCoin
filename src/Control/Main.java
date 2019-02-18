package Control;
	
import javafx.application.Application;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import Control.Logger;
import Control.SysData;
import Model.Consts;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperReport;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class Main extends Application {
	
	private static String USER_NAME = "FlipCoinDesign";  // GMail user name (just the part before "@gmail.com")
	private static String PASSWORD = "zydmltwgnjzpocqr"; // GMail password
	private static String RECIPIENT = "gilit.cherf@gmail.com";
	private static String MSUBJECT = "Flip Coin Transfer";
	private static String MBODY = " "; //fillout


	public static String getUSER_NAME() {
		return USER_NAME;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	public static String getRECIPIENT() {
		return RECIPIENT;
	}

	public static void setRECIPIENT(String rECIPIENT) {
		RECIPIENT = rECIPIENT;
	}

	public static String getMSUBJECT() {
		return MSUBJECT;
	}

	public static void setMSUBJECT(String mSUBJECT) {
		MSUBJECT = mSUBJECT;
	}

	public static String getMBODY() {
		return MBODY;
	}

	public static void setMBODY(String mBODY) {
		MBODY = mBODY;
	}
	
	public static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props);


		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for( int i = 0; i < to.length; i++ ) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("sent email");
		}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	
	public void start(Stage primaryStage) {
		try {
			
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/View/login.fxml"));
			Scene scene = new Scene(root,800,600);
			
			scene.getStylesheets().add(getClass().getResource("/View/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			
			scene.getWindow().centerOnScreen();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT }; // list of recipient email addresses
		String subject = MSUBJECT;
		String body = MBODY;

		//sendFromGMail(from, pass, to, subject, body); // Sends email to recipient
		
		Logger.initializeMyFileWriter();
		Logger.log("Welcome");
		launch(args);
	}

	
}

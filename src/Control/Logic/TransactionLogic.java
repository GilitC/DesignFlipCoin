package Control.Logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import Model.Consts;
import Model.Product;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public abstract class TransactionLogic {

	/**
	 * outputs report at runtime.
	 * 
	 * @return
	 */
	public static JFrame compileTransactionsReport(Date givenDate) {
		try {
			System.out.println("Defining class.forName");
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				System.out.println("Connection initiated");
				HashMap<String, Object> toSend = new HashMap<>();
				toSend.put("myDate", givenDate);
				System.out.println("Attempting to open jasper: "
						+ TransactionLogic.class.getResource("../../View/TransactionsReport.jasper"));
				JasperPrint print = JasperFillManager.fillReport(
						TransactionLogic.class.getResourceAsStream("../../View/TransactionsReport.jasper"), toSend,
						conn);
				JFrame frame = new JFrame("Transactions Report");
				frame.getContentPane().add(new JRViewer(print));
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				return frame;
			} catch (SQLException | JRException | NullPointerException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void updateTXStatus(Integer txID) {
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_UPDATE_TX_CONFIRMED)) {
				int i = 1;
				stmt.setInt(i++, txID); // can't be null
				if (stmt.executeUpdate() == 0) // TX was already executed
				{
					return;
				}

				PreparedStatement stmt_amt = conn.prepareStatement(Consts.SQL_GET_TX_AMOUNT);
				stmt_amt.setInt(1, txID);
				ResultSet amtRS = stmt_amt.executeQuery();
				Double payAmount = 0.0;
				while (amtRS.next()) {
					payAmount = amtRS.getDouble("PayValue");
				}

				if (txID > 0 && payAmount > 0) {
					OrderLogic.updateOrder(txID, payAmount);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Import transactions from XML
	 */
	public static void importConfirmedTransactions() {
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File("xml/exportTx.xml"));
			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("Transaction");
			for (int i = 0; i < nl.getLength(); i++) {

				if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nl.item(i);
					Integer txID = Integer.parseInt(el.getElementsByTagName("transactionID").item(0).getTextContent());
					System.out.println(nl.item(i).getNodeName() + " " + nl.item(i).getTextContent());
					updateTXStatus(txID);
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static Integer getOrderID(Integer txID) {
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_GET_ORDER_ID_BY_TX)) {
				int i = 1;
				stmt.setInt(i++, txID); // can't be null
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					return rs.getInt("orderID");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}


    /**
     * exports pay transactions from db to json.
     */
	public static Boolean exportPayTransactionsToJSON() {
    	   try {
               Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
               try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                       PreparedStatement stmt = conn.prepareStatement(
                               Consts.SQL_SEL_TRANSPAY_PENDING_STATUS);
                       ResultSet rs = stmt.executeQuery()) {
            	   JsonArray txs = new JsonArray();
                   while (rs.next()) {
                	   JsonObject tx = new JsonObject();
                	   
                	   for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
                		   tx.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                	   
                	   txs.add(tx);
                   }
                   
            	   JsonObject doc = new JsonObject();
            	   doc.put("Transactions_info", txs);
                   
                   File file = new File("json/txs.json");
                   file.getParentFile().mkdir();
                   
                   try (FileWriter writer = new FileWriter(file)) {
                	   writer.write(Jsoner.prettyPrint(doc.toJson()));
                	   writer.flush();
                       System.out.println("Transactions data exported successfully!");
                       return true;
                   } catch (IOException e) {
                	   e.printStackTrace();
                   }
               } catch (SQLException | NullPointerException e) {
                   e.printStackTrace();
               }
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }	
	return false;
	}
	
}

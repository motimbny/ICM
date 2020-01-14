package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Messages;
import entity.requestSuper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class SupervisorShowRequestsController.
 */
public class SupervisorShowRequestsController implements Initializable {
	
	/** The rows. */
	private ObservableList<requestSuper> rows;
	
	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new supervisor show requests controller.
	 */
	public SupervisorShowRequestsController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The showre BTN. */
	@FXML
	private Button showreBTN;

	/** The person BTN. */
	@FXML
	private Button personBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The request table. */
	@FXML
	private TableView<requestSuper> requestTable;

	/** The Request ID. */
	@FXML
	private TableColumn<requestSuper, Integer> RequestID;

	/** The Request status. */
	@FXML
	private TableColumn<requestSuper, String> RequestStatus;

	/** The Request process stage. */
	@FXML
	private TableColumn<requestSuper, String> RequestProcessStage;

	/** The Suprvisor update request BTN. */
	@FXML
	private Button SuprvisorUpdateRequestBTN;

	/** The Suprvisor close request BTN. */
	@FXML
	private Button SuprvisorCloseRequestBTN;

	/** The Suprvisor suspend request BTN. */
	@FXML
	private Button SuprvisorSuspendRequestBTN;

	/** The Suprvisor extension request BTN. */
	@FXML
	private Button SuprvisorExtensionRequestBTN;

	/** The Message BTN. */
	@FXML
	private Button MessageBTN;

	/** The search. */
	@FXML
	private Button search;
	
	/** The Suprvisor time. */
	@FXML
	private Button SuprvisorTime;
	
	/** The request id to. */
	@FXML
	private TextField requestIdTo;
	
	/** The sus not. */
	@FXML
	private Label susNot;

	/** The close not. */
	@FXML
	private Label closeNot;
	
	/** The stagenotmatch. */
	@FXML
	private Label stagenotmatch;

	/**
	 * Close request.
	 *
	 * @param event the event
	 */
	@FXML
	void closeRequest(MouseEvent event) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Alert pop up");
		Label label1 = new Label("Are you sure you want to close request?");
		label1.setFont(new Font("Arial", 14));
		Button button1 = new Button("Yes");
		Button button2 = new Button("No");
		button1.addEventHandler(ActionEvent.ACTION, (e) -> popupwindow.close());
		button1.addEventHandler(ActionEvent.ACTION, (e) -> {
			if (requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStage()
					.contentEquals("closing")) {
				
				String x = requestTable.getId();

				this.closeRequest(
						requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId());
			} else
				closeNot.setVisible(true);
		});
		button2.setOnAction(e -> popupwindow.close());
		button1.setStyle("-fx-border-color:green");
		button2.setStyle("-fx-border-color:red");
		VBox layout = new VBox(10);
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1, button1, button2);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 350, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

	/**
	 * Vis not.
	 *
	 * @param event the event
	 */
	@FXML
	void visNot(MouseEvent event) {
		closeNot.setVisible(false);
		susNot.setVisible(false);
		 SuprvisorUpdateRequestBTN.setStyle("");
		   SuprvisorTime.setStyle("");
		   SuprvisorCloseRequestBTN.setStyle("");
	}
	
	/**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHelp");
		MainAllControllers.changeWin();
	}
	
	/**
	 * Mouse click event, if "Home" button clicked, open the screen of "Home".
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHome");
		MainAllControllers.changeWin();
	}
	 
 	/**
 	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields.
 	 *
 	 * @param event the event
 	 * @throws IOException Signals that an I/O exception has occurred.
 	 */
	@FXML
	void goLogoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}
	  @FXML
	    void showRequestInfo(MouseEvent event) 
	    {
		  requestTable.setOnMouseClicked((MouseEvent ev ) -> 
		  {
	            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
	            {
	                try 
	                {
	                	MainAllControllers.request=requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId();
	                	MainAllControllers.nowWin="SupervisorShowRequests";
						MainAllControllers.setWindowVar("SupervisorRequestDetalies");
						MainAllControllers.changeWin();
					} 
	                catch (IOException e)
	                {
						e.printStackTrace();
					}                
	            }
	       
		  if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1){
		  {
			  if(requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStage().equals("supervisorApprovel"))
			   {
				   SuprvisorUpdateRequestBTN.setStyle("-fx-background-color: #ffdd99");
			   }
			   if(requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStage().equals("waitingSupervisorApproveEvaluationTime"))
			   {
				   SuprvisorTime.setStyle("-fx-background-color: #ffdd99");
			   }
			   if(requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStage().equals("waitingSupervisorApproveExecutionTime"))
			   {
				   SuprvisorTime.setStyle("-fx-background-color: #ffdd99");
			   }
			   if(requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStage().equals("closing"))
			   {
				   SuprvisorCloseRequestBTN.setStyle("-fx-background-color: #ffdd99");
			   }
		   }
		  }});
	    }
	 
	/**
	 * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information".
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorPersonalInfo");
		MainAllControllers.changeWin();
	}
	
	/**
	 * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests".
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}
	
	/**
	 * Mouse click event, if "Messages" button clicked, open the screen of "Messages".
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void messagePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorMessages");
		MainAllControllers.changeWin();
	}

	/**
	 * View time.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void viewTime(MouseEvent event) throws IOException {
		ArrayList<Object> arry = new ArrayList<Object>();
		int idadd = requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId();
		DBmessage dbm;
		arry.add(idadd);
		dbm = new DBmessage(MessageType.approveTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	/**
	 * Sets the time window.
	 *
	 * @param list the new time window
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setTimeWindow(ArrayList<Object> list) throws IOException {
		if(list.get(1).equals(1))
		{
			MainAllControllers.request = (int)list.get(0);
			MainAllControllers.setWindowVar("SupervisorTimeRequest");
			MainAllControllers.changeWin();
		}
		else if(list.get(1).equals(0))
		{
			stagenotmatch.setVisible(true);
		}
	}

	/**
	 * Suspend request.
	 *
	 * @param event the event
	 */
	@FXML
	void suspendRequest(MouseEvent event) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Alert pop up");
		Label label1 = new Label("Are you sure you want to suspend the request?");
		label1.setFont(new Font("Arial", 14));
		Button button1 = new Button("Yes");
		Button button2 = new Button("No");
		button1.addEventHandler(ActionEvent.ACTION, (e) -> popupwindow.close());
		button1.addEventHandler(ActionEvent.ACTION, (e) -> {
			if (requestTable.getSelectionModel().getSelectedIndex() == -1)
				susNot.setVisible(true);
			else if (requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStatus()
					.contentEquals("Active")) {

				this.suspendRequest(
						requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId());
			} else
				susNot.setVisible(true);
		});
		button2.setOnAction(e -> popupwindow.close());
		button1.setStyle("-fx-border-color:green");
		button2.setStyle("-fx-border-color:red");
		VBox layout = new VBox(10);
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1, button1, button2);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 350, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

	/**
	 * Suspend request.
	 *
	 * @param id the id
	 */
	private void suspendRequest(int id) {
		ArrayList<Object> arry = new ArrayList<Object>();
		int idadd = id;
		DBmessage dbm;
		arry.add(idadd);
		dbm = new DBmessage(MessageType.suspendRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	/**
	 * Close request.
	 *
	 * @param id the id
	 */
	private void closeRequest(int id) {

		ArrayList<Object> arry = new ArrayList<Object>();
		int idadd = id;
		DBmessage dbm;
		arry.add(idadd);
		dbm = new DBmessage(MessageType.closeRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	/**
	 * View extension report.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void viewExtensionReport(MouseEvent event) throws IOException {
		try {
			MainAllControllers.request = requestTable.getItems()
					.get(requestTable.getSelectionModel().getSelectedIndex()).getId();
			MainAllControllers.setWindowVar("SupervisorExtentionRequest");
			MainAllControllers.changeWin();
		} catch (Exception e) {
		}
	}

	/**
	 * Update request.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void UpdateRequest(MouseEvent event) throws IOException {
		try {
			if (!requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStatus()
					.equals("Closed")) {
				MainAllControllers.nowWin = "ITRequestSuperviser";
				MainAllControllers.request = requestTable.getItems()
						.get(requestTable.getSelectionModel().getSelectedIndex()).getId();
				MainAllControllers.setWindowVar("SupervisorUpdateRequest");
				MainAllControllers.changeWin();
			} else
				stagenotmatch.setVisible(true);

		} catch (Exception e) {

		}

	}

	/**
	 * Search request.
	 *
	 * @param event the event
	 */
	@FXML
	void searchRequest(MouseEvent event) {
		if (requestIdTo.getText().equals("")) {
			try {
				MainAllControllers.setWindowVar("SupervisorShowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
		} else {
			ArrayList<Object> arry = new ArrayList<Object>();
			arry.add(Integer.parseInt(requestIdTo.getText()));
			DBmessage dbm;
			dbm = new DBmessage(MessageType.SearchReqSupervisor, arry);
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
		RequestStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		RequestProcessStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		requestTable.setRowFactory(tv -> new TableRow<requestSuper>() 
		{
			@Override
            public void updateItem(requestSuper item, boolean empty) 
			{
                super.updateItem(item, empty);
                if (item == null) 
                {
                    setStyle("");
                } 
                else if (item.getCurrentStage().equals("supervisorApprovel")||item.getCurrentStage().equals("waitingSupervisorApproveEvaluationTime")) 
                {
                    setStyle("-fx-background-color: #ffdd99;");
                } 
                else if (item.getCurrentStage().equals("waitingSupervisorApproveExecutionTime"))
                {
                    setStyle("-fx-background-color: #ffdd99;");
                }
                else if (item.getCurrentStage().equals("closing"))
                {
                    setStyle("-fx-background-color: #ff6666;");
                }
                else if (!item.getCurrentStage().equals("waitingSupervisorApproveExecutionTime")||!item.getCurrentStage().equals("closing")) 
                {
                    setStyle("");
                }
                else if (!item.getCurrentStage().equals("supervisorApprovel")||!item.getCurrentStage().equals("waitingSupervisorApproveEvaluationTime")) 
                {
                    setStyle("");
                } 
            }
		});
		requestServer();
	}

	/**
	 * Request server.
	 */
	public void requestServer() {
		DBmessage dbm;
		dbm = new DBmessage(MessageType.superviserRequestShow, null);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Clear table.
	 */
	public void clearTable() {
		requestTable.getItems().clear();
	}

	/**
	 * Sets the text in table.
	 *
	 * @param list the new text in table
	 */
	public void setTextInTable(ArrayList<Object> list) {
		rows = FXCollections.observableArrayList();
		for (Object r : list)
			rows.add((requestSuper) r);
		requestTable.setItems(rows);
	}

	/**
	 * Setvisable.
	 */
	public void setvisable() {
		stagenotmatch.setVisible(true);
	}

}

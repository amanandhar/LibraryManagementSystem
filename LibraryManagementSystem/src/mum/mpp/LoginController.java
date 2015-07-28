package mum.mpp;

import java.util.Hashtable;
import java.util.Map;

import mum.mpp.util.FileUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController extends LoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField txtPassword;
	
	private String username, password, filePath;
	
	/**
	 * Called when the user clicks on the submit button.
	 */
	@FXML
	private void handleSubmitTask() {
		username = txtUsername.getText();
		password = txtPassword.getText();
		//filePath = getClass().getClassLoader().getResource("LoginInfo.xml").toString();
		filePath = LoginController.class.getResource("LoginInfo.xml").getPath();
		filePath = filePath.substring(1,filePath.length());
		Map<String,String> map = new Hashtable<>();
		map = FileUtil.isValid(filePath, username, password);
		if(map != null) {
			if(map.get("role").equals("librarian")) {
				System.out.println("Hello " + map.get("username") + " Your role is " + map.get("role"));
			}
			else if(map.get("role").equals("administrator")) {
				System.out.println("Hello " + map.get("username") + " Your role is " + map.get("role"));
			}
			else if(map.get("role").equals("both")) {
				System.out.println("Hello " + map.get("username") + " Your role is " + map.get("role"));
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("");
			alert.setContentText("Either username or password is incorrect!");
			alert.showAndWait();
			txtUsername.setText("");
			txtPassword.setText("");
		}
	}
	
	@FXML
	private void handleCancelTask() {
		System.exit(0);
	}
}

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ConectaSql;

import model.Person;

public class userListController implements Initializable {

    @FXML
    private Button bBack;

    @FXML
    private Button goChat;

    @FXML
    private TextField insertText;

    @FXML
    private ListView<Person> userList;
    
    
    
    logInHandle login;
    
    ConectaSql sql;
    Person person;

    @FXML
    void chatHandle(ActionEvent event) throws IOException {
    	String g= insertText.getText();
    	this.person=sql.getUser(g);
    	if(person!=null) {
    		FXMLLoader loader= new FXMLLoader(getClass().getResource("/vista/messagesFinal.fxml"));
    	Parent root = loader.load();
    	messagesController controller= loader.getController();
    	controller.setPerson(person);
    	Scene scene=new Scene(root);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	stage.show();
    	Stage myStage= (Stage) this.goChat.getScene().getWindow();
    	myStage.close();
    	}
    	else {
    		System.out.println("Error");
    	}
    	
    	
    }
    messagesController controller() {
    	
		return null;
    	
    }
    
    
    @FXML
    void backController(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("/vista/logInFinal.fxml"));
    	Parent root = loader.load();
    	Scene scene=new Scene(root);
    	Stage stage = new Stage();
    	stage.setScene(scene);
    	stage.show();
    	Stage myStage= (Stage) this.bBack.getScene().getWindow();
    	myStage.close();
    }
    
    public void setPerson(Person p) {
    	
    	this.person = p;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			sql= new ConectaSql();
			ArrayList<Person> person = sql.getPersonas();
			for(int i=0;i<person.size();i++) {
				userList.getItems().add(person.get(i));
			}
			
//			Aqui estaria el metodo para coger la persona de la scene anterior
			
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
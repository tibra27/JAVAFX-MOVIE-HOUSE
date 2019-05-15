package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class addInfoController {
	
	private Stage warningStage;
	
	@FXML
	private Label fill1;
	@FXML
	private Label fill2;
	@FXML
	private Label fill3;
	@FXML
	private Label fill4;
	@FXML
	private TextField moviename;
	@FXML
	private TextField genre;
	@FXML
	private TextField price;
	@FXML
	private TextField imdb;
	@FXML
	private Label status;
	@FXML
	private Label head;
	@FXML
	private Label fill5;
	@FXML
	private TextField descript;
	@FXML
	private ImageView registerlogo;
	@FXML
	private Label fill6;
	@FXML
	private Label fill7;
	@FXML
	private Label fill8;
	@FXML
	private Label fill9;
	@FXML
	private TextField trailer;
	@FXML
	private Label fill10;
	@FXML
	private TextField link;
	@FXML
	private TextField but1;
	@FXML
	private TextField but3;
	@FXML
	private TextField but2;
	@FXML
	private Button delete; 

	// Event Listener on Button.onAction
	@FXML
	public void addDetails(ActionEvent event) throws SQLException, IOException 
	{
		if(moviename.getText().equals("") || genre.getText().equals("")|| price.getText().equals("")|| imdb.getText().equals("")|| descript.getText().equals("")|| trailer.getText().equals("")|| link.getText().equals(""))
		{
			Alert alert = new Alert(AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            
			alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Please enter all details");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
		}
		else
		{
			String mname=moviename.getText();
			String gen=genre.getText();
			String pric=price.getText();
			String imd=imdb.getText();
			String decr=descript.getText();
			String trail=trailer.getText();
			String lin=link.getText();
			String file1=but1.getText();
			String file2=but2.getText();
			String file3=but3.getText();
			
			
			String str="insert into movie (MOVIE_NAME,GENRE,PRICE,IMDB_RATING,DESCRIPTION,TRAILER,COVER_IMAGE,IMAGE_1,IMAGE_2,DOWNLOAD_LINK" + 
			") values('"+ mname+"','"+ gen +"',"+pric+","+imd+",'"+ decr +"','"+ trail +"','"+ file1 +"','"+ file2 +"','"+ file3 +"','"+lin +"')";
			Statement st=Database.getStatement();
			st.executeUpdate(str);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            
			alert.initOwner(warningStage);
            alert.getDialogPane().setContentText("Movie is Added Successfully");
            alert.getDialogPane().setHeaderText(null);
            alert.showAndWait().filter(response -> response == ButtonType.OK);
			status.setText("Added Successfully");
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage=new Stage();
			FXMLLoader loader =new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/addInfo.fxml").openStream());
			
			//addInfoController control=(addInfoController)loader.getController();
			//control.Getuser(loginUserName.getText());
			Scene scene = new Scene(root,542,534);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setMinWidth(542);
	        primaryStage.setMinHeight(534);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		}
	}
	
	@FXML
	public void addTrailer(MouseEvent event)
	{
		FileChooser file = new FileChooser();
		file.setInitialDirectory(new File("C:\\Users\\Ashish\\Desktop\\Image")); // choose from specified location
		//file.getExtensionFilters().addAll(new ExtensionFilter("PDF File","*.pdf"));  /// show only specifed file
		File selectedfile = file.showOpenDialog(null);
		if(selectedfile!=null)
		{
			String path=selectedfile.getAbsolutePath();
			StringBuffer buff =new StringBuffer("");
			for(int i=0;i<path.length();i++)
			{
				if(path.charAt(i)=='\\')
				{
					buff.append("/");
				}
				else
				{
					buff.append(path.charAt(i));
				}
				
			}
			trailer.setText(buff.toString());
		}
		else
		{
			System.out.println("Invalid File");
		}
	}
	
	@FXML
	public void addLink(MouseEvent event)
	{
		FileChooser file = new FileChooser();
		file.setInitialDirectory(new File("C:\\Users\\Ashish\\Desktop\\Image")); // choose from specified location
		//file.getExtensionFilters().addAll(new ExtensionFilter("PDF File","*.pdf"));  /// show only specifed file
		File selectedfile = file.showOpenDialog(null);
		if(selectedfile!=null)
		{
			String path=selectedfile.getAbsolutePath();
			StringBuffer buff =new StringBuffer("");
			for(int i=0;i<path.length();i++)
			{
				if(path.charAt(i)=='\\')
				{
					buff.append("/");
				}
				else
				{
					buff.append(path.charAt(i));
				}
				
			}
			link.setText(buff.toString());
		}
		else
		{
			System.out.println("Invalid File");
		}
	}
	
	// Event Listener on Button[#but1].onAction
	@FXML
	public void addCover(MouseEvent event)
	{
		FileChooser file = new FileChooser();
		file.setInitialDirectory(new File("C:\\Users\\Ashish\\Desktop\\Image")); // choose from specified location
		//file.getExtensionFilters().addAll(new ExtensionFilter("PDF File","*.pdf"));  /// show only specifed file
		File selectedfile = file.showOpenDialog(null);
		if(selectedfile!=null)
		{
			String path=selectedfile.getAbsolutePath();
			StringBuffer buff=new StringBuffer("file:///");
			for(int i=0;i<path.length();i++)
			{
				if(path.charAt(i)=='\\')
				{
					buff.append("/");
				}
				else
				{
					buff.append(path.charAt(i));
				}
				
			}
			but1.setText(buff.toString());
		}
		else
		{
			System.out.println("Invalid File");
		}
	}
	// Event Listener on Button[#but3].onAction
	@FXML
	public void addImage2(MouseEvent event) 
	{
		FileChooser file = new FileChooser();
		file.setInitialDirectory(new File("C:\\Users\\Ashish\\Desktop\\Image")); // choose from specified location
		//file.getExtensionFilters().addAll(new ExtensionFilter("PDF File","*.pdf"));  /// show only specifed file
		File selectedfile = file.showOpenDialog(null);
		if(selectedfile!=null)
		{
			String path=selectedfile.getAbsolutePath();
			StringBuffer buff=new StringBuffer("file:///");
			for(int i=0;i<path.length();i++)
			{
				if(path.charAt(i)=='\\')
				{
					buff.append("/");
				}
				else
				{
					buff.append(path.charAt(i));
				}
				
			}
			but3.setText(buff.toString());
		}
		else
		{
			System.out.println("Invalid File");
		}
	}
	// Event Listener on Button[#but2].onAction
	@FXML
	public void addImage1(MouseEvent event) 
	{
		FileChooser file = new FileChooser();
		file.setInitialDirectory(new File("C:\\Users\\Ashish\\Desktop\\Image")); // choose from specified location
		//file.getExtensionFilters().addAll(new ExtensionFilter("PDF File","*.pdf"));  /// show only specifed file
		File selectedfile = file.showOpenDialog(null);
		if(selectedfile!=null)
		{
			String path=selectedfile.getAbsolutePath();
			StringBuffer buff=new StringBuffer("file:///");
			for(int i=0;i<path.length();i++)
			{
				if(path.charAt(i)=='\\')
				{
					buff.append("/");
				}
				else
				{
					buff.append(path.charAt(i));
				}
				
			}
			but2.setText(buff.toString());
		}
		else
		{
			System.out.println("Invalid File");
		}
	}
	
	@FXML
	public void signOut(ActionEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/login.fxml").openStream());
		Scene scene = new Scene(root,300,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void processDelete(ActionEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage=new Stage();
		FXMLLoader loader =new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/delete.fxml").openStream());
		Scene scene = new Scene(root,300,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

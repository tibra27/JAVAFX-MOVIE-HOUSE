package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;

import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.WindowEvent;

public class mediaController implements Initializable
{
	@FXML
	private MediaView media;
	@FXML
	private Slider VolumeSlider;
	@FXML
	private Label heading;
	
	
	private MediaPlayer mp;
	private Media me;
	
	
//	public void close()
//	{
//		
//		mp.stop();
//		
//	}
	
	
	@FXML
	public void play(ActionEvent e)
	{
		mp.play();
		mp.setRate(1);
	}
	
	@FXML
	public void pause(ActionEvent e)
	{
		mp.pause();
	}
	@FXML
	public void fast(ActionEvent e)
	{
		
		mp.setRate(2);
	}
	@FXML
	public void slow(ActionEvent e)
	{
		
		mp.setRate(0.5);
	}
	@FXML
	public void reload(ActionEvent e)
	{
		
		mp.seek(mp.getStartTime());
		mp.play();
	}
	@FXML
	public void start(ActionEvent e)
	{
		
		mp.seek(mp.getStartTime());
		mp.stop();
	}
	@FXML
	public void last(ActionEvent e)
	{
		//mp.seek(mp.getTotalDuration());
		mp.stop();
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//String path=new File("src/media/endgame.mp4").getAbsolutePath();
		String path=Main.trailPath;
		
		me=new Media(new File(path).toURI().toString());
		mp=new MediaPlayer(me);
		media.setMediaPlayer(mp);
		//mp.setAutoPlay(true);
		
		DoubleProperty width = media.fitWidthProperty();
		DoubleProperty height = media.fitHeightProperty();
		
		width.bind(Bindings.selectDouble(media.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(media.sceneProperty(), "height"));
		
		VolumeSlider.setValue(mp.getVolume()*100);//initial
		VolumeSlider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				// TODO Auto-generated method stub
				mp.setVolume(VolumeSlider.getValue()/100);
			}
			
		});
		
	}

	public void close() 
	{
		mp.stop();
		
	}

//	public void func() 
//	{
//		
//		mp.stop();
//		
//	}
	
	
}

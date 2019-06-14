package ca.uvic.seng330.assn3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.MediaView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MediaStreamController {

  @FXML
  private Button backButton;

  @FXML
  private MediaView mediaStreamer;

  @FXML
  private Button playButton;

  @FXML
  private Button pauseButton;

  private Media media;

  private MediaPlayer mediaPlayer;

  private Camera c;

  @FXML
  void onBackClick(ActionEvent event) throws IOException {
    Parent hubView = FXMLLoader.load(getClass().getResource("/CameraView.fxml"));
    Scene hubViewScene = new Scene(hubView);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hubViewScene);

    window.show();
    if(c != null) {
      c.stopStream();
    }
  }

  @FXML
  void onPauseClick(ActionEvent event) {

    if (mediaPlayer != null) {
      mediaPlayer.pause();
    }

  }

  @FXML
  void onPlayClick(ActionEvent event) {

    c = new Camera();

    if (c.isStreaming()) {
      mediaPlayer.play();
    } else {
      // Pressed play for the first time.
      media = new Media("https://www.radiantmediaplayer.com/media/bbb-360p.mp4");
      int w = media.getWidth();
      int h = media.getHeight();
      mediaPlayer = new MediaPlayer(media);
      mediaPlayer.play();
      c.startStream();
      mediaStreamer.setMediaPlayer(mediaPlayer);
      mediaStreamer.setPreserveRatio(true);
    }
  }

  public Camera getCurrentCamera() {
    return this.c;
  }
}



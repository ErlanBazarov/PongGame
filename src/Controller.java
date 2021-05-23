import java.sql.Time;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Controller {

    private double player1Y; 
    private double player2Y; 
    private double player1X; 
    private double player2X; 
    private double playerWidth = 20;
    private double playerHeight = 80;
    private double ballX;
    private double ballY;
    private double ballXSpeed = 2.2;
    private double ballYSpeed = 1;
    private double playerYSpeed = 5;
    private double ballR = 10;
    private boolean isWPressed = false;
    private boolean isSPressed = false;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private Timeline timeline;

    @FXML
    private Canvas canvas;

    @FXML
    void onKeyPressed(KeyEvent event) {
      if (event.getCode() == KeyCode.W){
        isWPressed = true;
      }
      if (event.getCode() == KeyCode.S){
        isSPressed = true;
      }
      if (event.getCode() == KeyCode.UP){
        isUpPressed = true;
      }
      if (event.getCode() == KeyCode.DOWN){
        isDownPressed = true;
      }
    }

    @FXML
    void onKeyReleased(KeyEvent event) {
      if (event.getCode() == KeyCode.W){
        isWPressed = false;
      }
      if (event.getCode() == KeyCode.S){
        isSPressed = false;
      }
      if (event.getCode() == KeyCode.UP){
        isUpPressed = false;
      }
      if (event.getCode() == KeyCode.DOWN){
        isDownPressed = false;
      }
    }
    
    public void initialize () {
      player1Y = canvas.getHeight() / 2;
      player2Y = canvas.getHeight() / 2;
      player1X = 0;
      player2X = canvas.getWidth() - playerWidth;
      ballX = canvas.getWidth() / 2;
      ballY = canvas.getHeight() / 2;

      GraphicsContext gC = canvas.getGraphicsContext2D();
      timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gC)));
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.play();
    }

    private boolean isBallTouchingPlayer (double playerX, double playerY, boolean isFirstPlayer) {
      if ((ballX + ballR >= playerX && ballX + ballR <= playerX + Math.abs(ballXSpeed) && !isFirstPlayer) 
        || (ballX <= playerX + playerWidth && ballX >= playerX + playerWidth - Math.abs(ballXSpeed) && isFirstPlayer)) {
        if ((ballY > playerY && ballY < playerY + playerHeight) 
          || (ballY + ballR > playerY && ballY + ballR < playerY + playerHeight)){
            return true;
          }
      }
      return false;
    }

    private boolean isBallTouchingRoofOrFloor () {
      if (ballY <= 0 || ballY + ballR >= canvas.getHeight())
        return true;
      return false;
    }

    private boolean isPlayer1Missed () {
      if (ballX <= 0) return true;
      return false;
    }

    private boolean isPlayer2Missed () {
      if (ballX >= canvas.getWidth()) return true;
      return false;
    }

    private void run(GraphicsContext gC){
      gC.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
      gC.setFill(Color.WHITE);
      gC.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

      gC.setFill(Color.BLACK);
      gC.fillRect(player1X, player1Y, playerWidth, playerHeight);
      gC.setFill(Color.BLACK);
      gC.fillRect(player2X, player2Y, playerWidth, playerHeight);

			ballX += ballXSpeed;
			ballY += ballYSpeed;
			gC.fillOval(ballX, ballY, ballR, ballR);

      if (isWPressed){
        player1Y -= playerYSpeed;
      }
      if (isSPressed){
        player1Y += playerYSpeed;
      }
      if (isUpPressed){
        player2Y -= playerYSpeed;
      }
      if (isDownPressed){
        player2Y += playerYSpeed;
      }

      if (isBallTouchingPlayer(player2X, player2Y, false)){
        ballXSpeed *= -1.2;
        ballYSpeed *= -1.6;
      }
      if (isBallTouchingPlayer(player1X, player1Y, true)){
        ballXSpeed *= -1.2;
        ballYSpeed *= -1.6;
      }
      if (isBallTouchingRoofOrFloor()){
        ballYSpeed *= -1;
      }

      gC.setTextAlign(TextAlignment.CENTER);
      gC.setTextBaseline(VPos.CENTER);
      if (isPlayer1Missed()){
        gC.fillText("player 2 won" , canvas.getWidth() / 2, canvas.getHeight() / 2);
        timeline.stop();
      }
      if (isPlayer2Missed()){
        gC.fillText("player 1 won" , canvas.getWidth() / 2, canvas.getHeight() / 2);
        timeline.stop();
      }
    }

}

package com.example.stickheroap;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final double MAX_STICK_LENGTH = 200;

    @Override
    public void start(Stage primarystage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

        primarystage.setTitle("Stickhero");
        primarystage.setScene(new Scene(root,600,400));
        primarystage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }



    public class StickHeroGame {

        private Character character;
        private Platform platform;
        private Stick stick;
        private Score score;

        @FXML
        private Label scoreLabel;

        private int currentScore;

        public StickHeroGame() {
            character = new Character();
            platform = new Platform();
            stick = new Stick();
            score = new Score();
        }

        public void startGame() {
            // Initialize the game elements and set up the game loop
            character.reset();
            platform.reset();
            stick.reset();
            currentScore = 0;

            Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), event -> {
                updateGame();
            }));
            gameLoop.setCycleCount(Timeline.INDEFINITE);
            gameLoop.play();
        }

        private void updateGame() {
            // Update game logic here
            character.move();
            stick.update(character, platform);

            // Check for collision
            if (stick.collidesWithPlatform(platform)) {
                // If the stick collides with the platform, update the score
                currentScore++;
                scoreLabel.setText("Score: " + currentScore);

                // Reset the game elements for the next jump
                character.reset();
                platform.reset();
                stick.reset();
            }

            // Check for game over condition (customize this based on your game rules)
            if (character.getX() > platform.getX() + platform.getWidth()) {
                gameOver();
            }
        }

        private void gameOver() {
            // Implement game over logic (e.g., show game over screen, save high score, etc.)
            System.out.println("Game Over! Your score: " + currentScore);
        }
    }


    public class Character {
        private static double x;

        public Character() {
            // Initialize character properties
            reset();
        }

        public void reset() {
            // Reset character position and other properties
            x = 0;
        }

        public void move() {
            // Implement character movement logic
            // For simplicity, just increase the x-coordinate in this example
            x += 2;
        }

        public static double getX() {
            return x;
        }
    }

    public class Platform {
        private double x;
        private double width;

        public Platform() {
            // Initialize platform properties
            reset();
        }

        public void reset() {
            // Reset platform position and other properties
            x = 200; // Adjust the starting position as needed
            width = 50; // Adjust the platform width as needed
        }

        public double getX() {
            return x;
        }

        public double getWidth() {
            return width;
        }
    }

    public class Stick {
        // Implement Stick class logic (e.g., stretching, collision detection with platform)
        // You may need to adjust this based on your game requirements

        private double length;
        private boolean stretching;
        private double stretchSpeed;

        public Stick() {
            // Initialize stick properties
            reset();
        }

        public void reset() {
            // Reset stick properties
            length = 0;
            stretching = true;
            stretchSpeed = 2.5; // Adjust stretch speed as needed
        }

        public void stretch() {
            // Stretch the stick
            if (stretching) {
                length += stretchSpeed;
            }
        }

        public void stopStretching() {
            // Stop stretching the stick
            stretching = false;
        }

        public boolean collidesWithPlatform(Platform platform) {
            // Check if the stick collides with the platform
            double stickEndX = Character.getX() + length;
            double platformEndX = platform.getX() + platform.getWidth();

            return stickEndX >= platform.getX() && Character.getX() <= platformEndX;
        }


        public void update(Character character, Platform platform) {
            if (stretching) {
                stretch();
            }

            // Check for collision with the platform
            if (collidesWithPlatform(platform)) {
                stopStretching();
            }

            // Check if the stick has reached its maximum length
            if (length >= HelloApplication.MAX_STICK_LENGTH) {
                stopStretching();
            }
        }

    }


    public class Score {
        // Implement Score class logic (e.g., keeping track of the player's score)
        // You may need to adjust this based on your game requirements
    }


}



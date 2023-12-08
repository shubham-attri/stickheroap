package com.example.stickheroap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private Rectangle character;

    @FXML
    private Rectangle platform;

    @FXML
    private Rectangle stick;

    @FXML
    private Label scoreLabel;


    private int score;
    private boolean stretching;

    public void initialize() {
        // Initialize game state
        score = 0;
        stretching = false;

        // Set up the game loop
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            updateGame();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    @FXML
    private void handleStretchButton(ActionEvent event) {
        if (!stretching) {
            stretching = true;
        }
    }

    private void updateGame() {
        // Update game logic here
        if (stretching) {
            stretchStick();
        }

        // Check for collision
        if (stick.getBoundsInParent().intersects(platform.getBoundsInParent())) {
            handleCollision();
        }

        // Other game logic...
    }

    private void stretchStick() {
        // Implement stick stretching logic
        stick.setWidth(stick.getWidth() + 2);
    }

    private void handleCollision() {
        // Implement collision handling logic
        stretching = false; // Stop stretching the stick
        stick.setWidth(0); // Reset stick width
        score++;
        scoreLabel.setText("Score: " + score);

        // Other collision handling logic...
    }

}
package com.courier.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.courier.model.Package;
import com.courier.service.MockTrackingService;

/**
 * Search screen where users enter tracking ID
 */
public class SearchView {
    
    private Stage stage;
    private MockTrackingService trackingService;
    private TextField trackingIdField;
    private Label errorLabel;
    
    public SearchView(Stage stage) {
        this.stage = stage;
        this.trackingService = new MockTrackingService();
    }
    
    public Scene createScene() {
        // Main container
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #f0f4f8;");
        
        // Title
        Label titleLabel = new Label("Courier Tracking System");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        titleLabel.setStyle("-fx-text-fill: #2c3e50;");
        
        // Subtitle
        Label subtitleLabel = new Label("Track your package in real-time");
        subtitleLabel.setFont(Font.font("Arial", 16));
        subtitleLabel.setStyle("-fx-text-fill: #7f8c8d;");
        
        // Tracking ID input
        Label inputLabel = new Label("Enter Tracking ID:");
        inputLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        inputLabel.setStyle("-fx-text-fill: #34495e;");
        
        trackingIdField = new TextField();
        trackingIdField.setPromptText("e.g., TRK001, TRK002, TRK003");
        trackingIdField.setMaxWidth(400);
        trackingIdField.setFont(Font.font("Arial", 14));
        trackingIdField.setStyle(
            "-fx-background-color: white; " +
            "-fx-border-color: #bdc3c7; " +
            "-fx-border-radius: 5; " +
            "-fx-background-radius: 5; " +
            "-fx-padding: 10;"
        );
        
        // Search button
        Button searchButton = new Button("Track Package");
        searchButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        searchButton.setStyle(
            "-fx-background-color: #3498db; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 12 30; " +
            "-fx-background-radius: 5; " +
            "-fx-cursor: hand;"
        );
        searchButton.setOnMouseEntered(e -> 
            searchButton.setStyle(
                "-fx-background-color: #2980b9; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 12 30; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
            )
        );
        searchButton.setOnMouseExited(e -> 
            searchButton.setStyle(
                "-fx-background-color: #3498db; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 12 30; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
            )
        );
        
        // Error label
        errorLabel = new Label();
        errorLabel.setFont(Font.font("Arial", 12));
        errorLabel.setStyle("-fx-text-fill: #e74c3c;");
        errorLabel.setVisible(false);
        
        // Sample tracking IDs info
        Label sampleLabel = new Label("Sample Tracking IDs: TRK001 (Delivered), TRK002 (In Transit), TRK003 (Out for Delivery)");
        sampleLabel.setFont(Font.font("Arial", 11));
        sampleLabel.setStyle("-fx-text-fill: #95a5a6;");
        sampleLabel.setWrapText(true);
        sampleLabel.setMaxWidth(500);
        sampleLabel.setAlignment(Pos.CENTER);
        
        // Search button action
        searchButton.setOnAction(e -> handleSearch());
        
        // Allow Enter key to search
        trackingIdField.setOnAction(e -> handleSearch());
        
        // Add all components
        root.getChildren().addAll(
            titleLabel,
            subtitleLabel,
            new VBox(5, inputLabel, trackingIdField),
            searchButton,
            errorLabel,
            sampleLabel
        );
        
        return new Scene(root, 700, 500);
    }
    
    private void handleSearch() {
        String trackingId = trackingIdField.getText().trim();
        
        // Validate input
        if (trackingId.isEmpty()) {
            showError("Please enter a tracking ID");
            return;
        }
        
        // Search for package
        Package pkg = trackingService.getPackageByTrackingId(trackingId);
        
        if (pkg != null) {
            // Package found - show tracking results
            errorLabel.setVisible(false);
            TrackingResultView resultView = new TrackingResultView(stage, pkg, this);
            stage.setScene(resultView.createScene());
        } else {
            // Package not found
            showError("Tracking ID not found. Please check and try again.");
        }
    }
    
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
    
    public void clearInput() {
        trackingIdField.clear();
        errorLabel.setVisible(false);
    }
}
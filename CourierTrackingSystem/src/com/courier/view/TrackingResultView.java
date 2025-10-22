package com.courier.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.courier.model.Checkpoint;
import com.courier.model.Package;
import java.time.format.DateTimeFormatter;

/**
 * Tracking results screen showing package details and checkpoint timeline
 */
public class TrackingResultView {
    
    private Stage stage;
    private Package packageInfo;
    private SearchView searchView;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    
    public TrackingResultView(Stage stage, Package packageInfo, SearchView searchView) {
        this.stage = stage;
        this.packageInfo = packageInfo;
        this.searchView = searchView;
    }
    
    public Scene createScene() {
        // Main container
        VBox root = new VBox(0);
        root.setStyle("-fx-background-color: #f0f4f8;");
        
        // Header
        VBox header = createHeader();
        
        // Content area with scroll
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f0f4f8; -fx-background-color: #f0f4f8;");
        
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        // Package details card
        VBox detailsCard = createDetailsCard();
        
        // Status timeline
        VBox timelineCard = createTimelineCard();
        
        content.getChildren().addAll(detailsCard, timelineCard);
        scrollPane.setContent(content);
        
        root.getChildren().addAll(header, scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        return new Scene(root, 800, 600);
    }
    
    private VBox createHeader() {
        VBox header = new VBox(10);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: #2c3e50;");
        
        // Back button and title row
        HBox topRow = new HBox(15);
        topRow.setAlignment(Pos.CENTER_LEFT);
        
        Button backButton = new Button("← Back to Search");
        backButton.setFont(Font.font("Arial", 12));
        backButton.setStyle(
            "-fx-background-color: transparent; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: white; " +
            "-fx-border-radius: 3; " +
            "-fx-background-radius: 3; " +
            "-fx-padding: 8 15; " +
            "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e -> 
            backButton.setStyle(
                "-fx-background-color: rgba(255,255,255,0.1); " +
                "-fx-text-fill: white; " +
                "-fx-border-color: white; " +
                "-fx-border-radius: 3; " +
                "-fx-background-radius: 3; " +
                "-fx-padding: 8 15; " +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e -> 
            backButton.setStyle(
                "-fx-background-color: transparent; " +
                "-fx-text-fill: white; " +
                "-fx-border-color: white; " +
                "-fx-border-radius: 3; " +
                "-fx-background-radius: 3; " +
                "-fx-padding: 8 15; " +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnAction(e -> {
            searchView.clearInput();
            stage.setScene(searchView.createScene());
        });
        
        Label titleLabel = new Label("Package Tracking Details");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: white;");
        
        topRow.getChildren().addAll(backButton, titleLabel);
        
        // Tracking ID
        Label trackingIdLabel = new Label("Tracking ID: " + packageInfo.getTrackingId());
        trackingIdLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
        trackingIdLabel.setStyle("-fx-text-fill: #ecf0f1;");
        
        // Current status badge
        Label statusBadge = new Label(packageInfo.getCurrentStatus().getDisplayName());
        statusBadge.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        String statusColor = getStatusColor(packageInfo.getCurrentStatus().name());
        statusBadge.setStyle(
            "-fx-background-color: " + statusColor + "; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 8 20; " +
            "-fx-background-radius: 20;"
        );
        
        header.getChildren().addAll(topRow, trackingIdLabel, statusBadge);
        
        return header;
    }
    
    private VBox createDetailsCard() {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setStyle(
            "-fx-background-color: white; " +
            "-fx-background-radius: 10; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);"
        );
        
        Label cardTitle = new Label("Package Information");
        cardTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        cardTitle.setStyle("-fx-text-fill: #2c3e50;");
        
        // Two column layout
        HBox columnsBox = new HBox(40);
        
        // Left column
        VBox leftColumn = new VBox(12);
        leftColumn.getChildren().addAll(
            createInfoRow("Sender:", packageInfo.getSenderName()),
            createInfoRow("From:", packageInfo.getSenderAddress()),
            createInfoRow("Description:", packageInfo.getDescription()),
            createInfoRow("Weight:", packageInfo.getWeight() + " kg")
        );
        
        // Right column
        VBox rightColumn = new VBox(12);
        rightColumn.getChildren().addAll(
            createInfoRow("Recipient:", packageInfo.getRecipientName()),
            createInfoRow("To:", packageInfo.getRecipientAddress()),
            createInfoRow("Phone:", packageInfo.getRecipientPhone()),
            createInfoRow("Est. Delivery:", 
                packageInfo.getEstimatedDelivery() != null ? 
                packageInfo.getEstimatedDelivery().format(DateTimeFormatter.ofPattern("dd MMM yyyy")) : 
                "N/A")
        );
        
        columnsBox.getChildren().addAll(leftColumn, rightColumn);
        HBox.setHgrow(leftColumn, Priority.ALWAYS);
        HBox.setHgrow(rightColumn, Priority.ALWAYS);
        
        card.getChildren().addAll(cardTitle, columnsBox);
        
        return card;
    }
    
    private VBox createTimelineCard() {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setStyle(
            "-fx-background-color: white; " +
            "-fx-background-radius: 10; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 2);"
        );
        
        Label cardTitle = new Label("Tracking Timeline");
        cardTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        cardTitle.setStyle("-fx-text-fill: #2c3e50;");
        
        VBox timeline = new VBox(0);
        
        // Add checkpoints in reverse order (newest first)
        for (int i = packageInfo.getCheckpoints().size() - 1; i >= 0; i--) {
            Checkpoint checkpoint = packageInfo.getCheckpoints().get(i);
            boolean isLast = (i == 0);
            timeline.getChildren().add(createCheckpointItem(checkpoint, isLast));
        }
        
        card.getChildren().addAll(cardTitle, timeline);
        
        return card;
    }
    
    private VBox createCheckpointItem(Checkpoint checkpoint, boolean isLast) {
        VBox item = new VBox(5);
        
        HBox mainRow = new HBox(15);
        mainRow.setAlignment(Pos.TOP_LEFT);
        
        // Timeline indicator
        VBox indicator = new VBox(0);
        indicator.setAlignment(Pos.TOP_CENTER);
        indicator.setMinWidth(30);
        indicator.setMaxWidth(30);
        
        // Circle
        Region circle = new Region();
        circle.setMinSize(14, 14);
        circle.setMaxSize(14, 14);
        String circleColor = getStatusColor(checkpoint.getStatus().name());
        circle.setStyle(
            "-fx-background-color: " + circleColor + "; " +
            "-fx-background-radius: 7; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 3;"
        );
        
        // Vertical line
        Region line = new Region();
        line.setMinWidth(2);
        line.setMaxWidth(2);
        line.setPrefHeight(60);
        line.setStyle("-fx-background-color: #e0e0e0;");
        
        if (!isLast) {
            indicator.getChildren().addAll(circle, line);
        } else {
            indicator.getChildren().add(circle);
        }
        
        // Content
        VBox content = new VBox(5);
        VBox.setVgrow(content, Priority.ALWAYS);
        
        // Status and location
        Label statusLabel = new Label(checkpoint.getStatus().getDisplayName());
        statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        statusLabel.setStyle("-fx-text-fill: #2c3e50;");
        
        Label locationLabel = new Label(checkpoint.getLocation());
        locationLabel.setFont(Font.font("Arial", 12));
        locationLabel.setStyle("-fx-text-fill: #7f8c8d;");
        
        Label descriptionLabel = new Label(checkpoint.getDescription());
        descriptionLabel.setFont(Font.font("Arial", 11));
        descriptionLabel.setStyle("-fx-text-fill: #95a5a6;");
        descriptionLabel.setWrapText(true);
        
        Label timeLabel = new Label(checkpoint.getTimestamp().format(dateFormatter));
        timeLabel.setFont(Font.font("Arial", 10));
        timeLabel.setStyle("-fx-text-fill: #bdc3c7;");
        
        content.getChildren().addAll(statusLabel, locationLabel, descriptionLabel, timeLabel);
        
        mainRow.getChildren().addAll(indicator, content);
        HBox.setHgrow(content, Priority.ALWAYS);
        
        item.getChildren().add(mainRow);
        item.setPadding(new Insets(5, 0, 5, 0));
        
        return item;
    }
    
    private HBox createInfoRow(String label, String value) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        
        Label labelText = new Label(label);
        labelText.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        labelText.setStyle("-fx-text-fill: #7f8c8d;");
        labelText.setMinWidth(120);
        
        Label valueText = new Label(value != null ? value : "N/A");
        valueText.setFont(Font.font("Arial", 13));
        valueText.setStyle("-fx-text-fill: #2c3e50;");
        valueText.setWrapText(true);
        
        row.getChildren().addAll(labelText, valueText);
        HBox.setHgrow(valueText, Priority.ALWAYS);
        
        return row;
    }
    
    private String getStatusColor(String status) {
        switch (status) {
            case "ORDER_PLACED":
                return "#95a5a6";
            case "PICKED_UP":
                return "#3498db";
            case "IN_TRANSIT":
                return "#f39c12";
            case "OUT_FOR_DELIVERY":
                return "#9b59b6";
            case "DELIVERED":
                return "#27ae60";
            case "FAILED_DELIVERY":
                return "#e74c3c";
            case "RETURNED":
                return "#c0392b";
            default:
                return "#95a5a6";
        }
    }
}
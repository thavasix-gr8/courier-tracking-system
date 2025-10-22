package com.courier;

import javafx.application.Application;
import javafx.stage.Stage;
import com.courier.view.SearchView;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Courier Tracking System");
        
        // Create and show search view
        SearchView searchView = new SearchView(primaryStage);
        primaryStage.setScene(searchView.createScene());
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
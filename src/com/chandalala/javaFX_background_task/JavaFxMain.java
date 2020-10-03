package com.chandalala.javaFX_background_task;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxMain  extends Application {

    /**
     * Background threads in UI applications
     *  we use the javafx.concurrent Package
     *  we use a class that extends the Task class
     *  the Task class implements the Runnable interface, Wherever we use Runnable objects in console applications
     *  We use Task Objects in javaFx applications
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }


}

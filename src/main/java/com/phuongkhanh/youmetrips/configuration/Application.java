package com.phuongkhanh.youmetrips.configuration;

import com.phuongkhanh.youmetrips.presentation.windows.LoginWindow;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ApplicationContext context = DaggerApplicationContext.create();

        LoginWindow loginWindow = context.loginWindow();
        loginWindow.attach(primaryStage);
        loginWindow.getStage().show();

        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
    }
}

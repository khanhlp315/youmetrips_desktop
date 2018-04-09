package com.phuongkhanh.youmetrips.configuration;

import com.phuongkhanh.youmetrips.presentation.windows.LoginWindow;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = DaggerApplicationContext.create();

        LoginWindow loginWindow = context.loginWindow();
        loginWindow.attach( primaryStage );
        loginWindow.getStage().show();
    }

    public static void main( String[] args ) {
        launch( args );
    }
}

package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class Main extends Application{
    double appCX = 300;
    double appCY = 225;

    Color appBack = Color.rgb(255,128,64);
    Color sunBody = Color.rgb(255,255,0);
    Color sunMouth = Color.rgb(255,0,0);
    Color sunEye = Color.rgb(0,128,128);

    public static void main (String args[]) {
        launch(args); // main method
    }
    @Override
    public void start(Stage primaryStage) // start - is the main entry point fo all JavaFX applications
    {
        Group root = new Group(); // making the root of all scene's objects
        Scene scene = new Scene (root, 600, 450); // making the scene for root object with size of 500*400 pixels
        scene.setFill(appBack); // set the color of scene

        drawSunBody(root);
        drawSunLine(root);
        drawSunMouth(root);
        drawSunEyes(root);

        primaryStage.setScene(scene); // specifying the scene to be used on this stage
        primaryStage.show(); // show the scene
    }

    public void drawSunEyes(Group root) {
        Rectangle left = new Rectangle(appCX - 70, appCY - 50, 25, 25);
        left.setFill(sunEye);
        root.getChildren().add(left);

        Rectangle right = new Rectangle(appCX + 30, appCY - 50, 25, 25);
        right.setFill(sunEye);
        root.getChildren().add(right);
    }

    public void drawSunMouth(Group root) {
        Polygon sunMouthPolygon = new Polygon(
                appCX - 50, appCY + 25,
                appCX + 50, appCY + 25,
                appCX, appCY + 50
        );
        sunMouthPolygon.setFill(sunMouth);
        root.getChildren().add(sunMouthPolygon);
    }

    public void drawSunBody(Group root) {
        Polygon sunBodyPolygon = new Polygon(
                appCX - 150, appCY - 50,
                appCX - 100, appCY - 120,
                appCX, appCY - 150,
                appCX + 140, appCY - 50,
                appCX + 130, appCY + 40,
                appCX + 10, appCY + 100,
                appCX - 110, appCY + 50);
        sunBodyPolygon.setFill(sunBody);
        root.getChildren().add(sunBodyPolygon);
    }

    public void drawSunLine(Group root) {
        Line sunLine1 = new Line (appCX, appCY, appCX + 200, appCY);
        sunLine1.setStroke(sunBody);
        sunLine1.setStrokeWidth(10);
        root.getChildren().add(sunLine1);

        Line sunLine2 = new Line (appCX, appCY, appCX + 150, appCY + 150);
        sunLine2.setStroke(sunBody);
        sunLine2.setStrokeWidth(10);
        root.getChildren().add(sunLine2);

        Line sunLine3 = new Line (appCX, appCY, appCX, appCY + 200);
        sunLine3.setStroke(sunBody);
        sunLine3.setStrokeWidth(10);
        root.getChildren().add(sunLine3);

        Line sunLine4 = new Line (appCX, appCY, appCX + 150, appCY - 150);
        sunLine4.setStroke(sunBody);
        sunLine4.setStrokeWidth(10);
        root.getChildren().add(sunLine4);

        Line sunLine7 = new Line (appCX, appCY, appCX - 200, appCY);
        sunLine7.setStroke(sunBody);
        sunLine7.setStrokeWidth(10);
        root.getChildren().add(sunLine7);

        Line sunLine8 = new Line (appCX, appCY, appCX, appCY - 200);
        sunLine8.setStroke(sunBody);
        sunLine8.setStrokeWidth(10);
        root.getChildren().add(sunLine8);

        Line sunLine5 = new Line (appCX, appCY, appCX - 150, appCY + 150);
        sunLine5.setStroke(sunBody);
        sunLine5.setStrokeWidth(10);
        root.getChildren().add(sunLine5);

        Line sunLine6 = new Line (appCX, appCY, appCX - 150, appCY - 150);
        sunLine6.setStroke(sunBody);
        sunLine6.setStrokeWidth(10);
        root.getChildren().add(sunLine6);
    }
}
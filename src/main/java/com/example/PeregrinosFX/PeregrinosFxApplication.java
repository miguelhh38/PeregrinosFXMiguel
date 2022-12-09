package com.example.PeregrinosFX;

import com.example.PeregrinosFX.config.StageManager;
import com.example.PeregrinosFX.controller.MenuPrincipalController;
import com.example.PeregrinosFX.view.FxmlView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

import static com.sun.javafx.application.ParametersImpl.getParameters;
import static javafx.application.Application.launch;

@SpringBootApplication
public class PeregrinosFxApplication extends Application{

    protected ConfigurableApplicationContext springContext;
    protected StageManager stageManager;

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = springBootApplicationContext();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageManager = springContext.getBean(StageManager.class, stage);
        stage.getIcons().add(new Image(getClass().getResource("/img/logoConcha.png").openStream()));
        stage.setResizable(false);
        displayInitialScene();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    /**
     * Useful to override this method by sub-classes wishing to change the first
     * Scene to be displayed on startup. Example: Functional tests on main
     * window.
     */

    private ConfigurableApplicationContext springBootApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(PeregrinosFxApplication.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        return builder.run(args);
    }
    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.MENUPRINCIPAL);
    }




}

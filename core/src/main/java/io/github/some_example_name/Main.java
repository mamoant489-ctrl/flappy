package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.screens.ScreenBackgrounds;
import io.github.some_example_name.screens.ScreenGame;
import io.github.some_example_name.screens.ScreenMenu;
import io.github.some_example_name.screens.ScreenRestart;

public class Main extends Game {

    public SpriteBatch batch;
    public OrthographicCamera camera;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

    public ScreenMenu screenMenu;
    public ScreenGame screenGame;
    public ScreenRestart screenRestart;
    public ScreenBackgrounds screenBackgrounds;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this);
        screenRestart = new ScreenRestart(this);
        screenBackgrounds = new ScreenBackgrounds(this);
        setScreen(screenMenu);
    }


    @Override
    public void dispose() {
        screenMenu.dispose();
        screenGame.dispose();
        screenRestart.dispose();
        screenBackgrounds.dispose();

        batch.dispose();
    }
}

package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.TextButton;

public class ScreenRestart implements Screen {

    Main main;
    MovingBackground background;
    TextButton buttonRestart;
    TextButton buttonMenu;
    PointCounter pointCounter;

    int gamePoints;

    public ScreenRestart(Main main){

        this.main = main;

        pointCounter = new PointCounter(750, 530);
        buttonRestart = new TextButton(100, 400, "Restart", 400, 150);
        buttonMenu = new TextButton(100, 200, "Menu", 400, 150);
        background = new MovingBackground("backgrounds/restart_bg.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {

            Vector3 touch = main.camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonRestart.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenGame);
            }

            if (buttonMenu.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenMenu);
            }
        }
        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        background.draw(main.batch);
        buttonRestart.draw(main.batch);
        buttonMenu.draw(main.batch);
        pointCounter.draw(main.batch, gamePoints);

        main.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        buttonRestart.dispose();
        buttonMenu.dispose();
    }
}

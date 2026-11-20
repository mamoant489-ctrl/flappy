package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.TextButton;

public class ScreenMenu implements Screen {

    Main main;
    MovingBackground background;
    TextButton buttonStart;
    TextButton buttonExit;
    TextButton buttonBackgrounds;

    public ScreenMenu(Main main){

        this.main = main;
        background = new MovingBackground("backgrounds/restart_bg.png");

        buttonStart = new TextButton(100, 400, "Start", 400, 150);
        buttonExit = new TextButton(100, 200, "Exit", 400, 150);
        buttonBackgrounds = new TextButton(600, 400, "Backgrounds", 600, 150);

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

            if (buttonStart.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenGame);
            }

            if (buttonExit.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
            }

            if (buttonBackgrounds.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenBackgrounds);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        background.draw(main.batch);
        buttonStart.draw(main.batch);
        buttonExit.draw(main.batch);
        buttonBackgrounds.draw(main.batch);

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
        buttonStart.dispose();
        buttonExit.dispose();
        buttonBackgrounds.dispose();
    }
}


package io.github.some_example_name.screens;

import static io.github.some_example_name.Main.SCR_HEIGHT;
import static io.github.some_example_name.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.Main;
import io.github.some_example_name.components.BackgroundManager;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.TextButton;

public class ScreenBackgrounds implements Screen {

    Main main;
    ScreenMenu menu;

    MovingBackground background;
    Texture previewTexture;

    TextButton buttonPrevious;
    TextButton buttonNext;
    TextButton buttonApply;
    TextButton buttonBack;

    int selectedBackground;

    public ScreenBackgrounds(Main main) {

        this.main = main;
        this.menu = menu;

        selectedBackground = BackgroundManager.getSelectedBackground();

        background = new MovingBackground("backgrounds/restart_bg.png");

        buttonPrevious = new TextButton(50, 150, "Previous", 400, 100);

        buttonNext = new TextButton(870, 150, "Next", 350, 100);

        buttonApply = new TextButton(490, 150, "Apply", 350, 100);

        buttonBack = new TextButton(50, 30, "Back", 300, 100);

        updatePreview();
    }

    private void updatePreview() {

        if (previewTexture != null) {
            previewTexture.dispose();
        }

        previewTexture = new Texture(
            Gdx.files.internal(
                BackgroundManager.getBackground(selectedBackground)
            )
        );
    }

    private void nextBackground() {

        selectedBackground++;

        if (selectedBackground >= BackgroundManager.getBackgroundCount()) {
            selectedBackground = 0;
        }

        updatePreview();
    }

    private void previousBackground() {

        selectedBackground--;

        if (selectedBackground < 0) {
            selectedBackground = BackgroundManager.getBackgroundCount() - 1;
        }

        updatePreview();
    }

    private void applyBackground() {
        BackgroundManager.setSelectedBackground(selectedBackground);
        main.setScreen(main.screenMenu);
    }

    @Override
    public void show() {
        selectedBackground = BackgroundManager.getSelectedBackground();

        updatePreview();

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {

            Vector3 touch = main.camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            int x = (int) touch.x;
            int y = (int) touch.y;

            if (buttonPrevious.isHit(x, y)) {
                previousBackground();

            } else if (buttonNext.isHit(x, y)) {
                nextBackground();

            } else if (buttonApply.isHit(x, y)) {
                applyBackground();

            } else if (buttonBack.isHit(x, y)) {
                main.setScreen(main.screenMenu);
            }
        }

        ScreenUtils.clear(0, 0, 0, 1);

        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);

        main.batch.begin();

        background.draw(main.batch);

        float maxWidth = SCR_WIDTH * 0.75f;
        float maxHeight = SCR_HEIGHT * 0.45f;

        float scale = Math.min(
            maxWidth / previewTexture.getWidth(),
            maxHeight / previewTexture.getHeight()
        );

        float previewWidth = previewTexture.getWidth() * scale;

        float previewHeight = previewTexture.getHeight() * scale;

        float previewX = (SCR_WIDTH - previewWidth) / 2;

        float previewY = SCR_HEIGHT * 0.4f;

        main.batch.draw(previewTexture, previewX, previewY, previewWidth, previewHeight);

        buttonPrevious.draw(main.batch);
        buttonNext.draw(main.batch);
        buttonApply.draw(main.batch);
        buttonBack.draw(main.batch);

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

        if (previewTexture != null) {
            previewTexture.dispose();
        }

        background.dispose();

        buttonPrevious.dispose();
        buttonNext.dispose();
        buttonApply.dispose();
        buttonBack.dispose();
    }
}

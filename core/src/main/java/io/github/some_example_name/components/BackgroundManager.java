
package io.github.some_example_name.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class BackgroundManager {

    private static final String PREFS_NAME = "game_settings";
    private static final String SELECTED_BACKGROUND = "selected_background";

    private static final String[] BACKGROUNDS = {
        "backgrounds/game1_bg.png",
        "backgrounds/game2_bg.png",
        "backgrounds/game3_bg.png"
    };

    public static int getSelectedBackground() {
        Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);

        int index = prefs.getInteger(SELECTED_BACKGROUND, 0);

        if (index < 0 || index >= BACKGROUNDS.length) {
            index = 0;
        }

        return index;
    }

    public static void setSelectedBackground(int index) {

        if (index < 0 || index >= BACKGROUNDS.length) {
            return;
        }

        Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);

        prefs.putInteger(SELECTED_BACKGROUND, index);
        prefs.flush();
    }

    public static String getGameBackground() {
        return BACKGROUNDS[getSelectedBackground()];
    }

    public static String getBackground(int index) {
        if (index < 0 || index >= BACKGROUNDS.length) {
            return BACKGROUNDS[0];
        }

        return BACKGROUNDS[index];
    }

    public static int getBackgroundCount() {
        return BACKGROUNDS.length;
    }
}

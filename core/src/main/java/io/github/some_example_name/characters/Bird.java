package io.github.some_example_name.characters;

import static io.github.some_example_name.Main.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {

    private int x, y;
    private int speed;
    private int jumpHeight;
    private final int maxHeightOfJump = 150;
    private boolean jump;
    private Texture[] framesArray;
    private int width;
    private int height;
    private int frameCounter;

    public Bird(int x, int y, int speed, int width, int height){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        frameCounter = 0;

        framesArray = new Texture[]{
            new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png"),
        };
    }

    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void fly(){
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;

        return true;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

        public void dispose(){
        for (Texture frame : framesArray) {
            frame.dispose();
        }
    }
}

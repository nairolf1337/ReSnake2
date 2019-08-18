package de.nairolf.reSnake;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

public class Game extends PApplet {
    //TileMap tm;
    Environment en;
    PFont font;

    public void settings() {
        size(640, 580);
    }

    public void setup() {
        /*tm = new TileMap(createGraphics(width, height), 16, 12);
        tm.setTiles(color(0xff,0,0xff), new PVector[]{new PVector(0, 0), new PVector(15, 0)});
        tm.drawTiles();*/
        font = createFont("Bitstream Charter", 18, true);
        textFont(font);

        en = new Environment(createGraphics(640, 480), 16, 12);
        frameRate(60);
    }

    public void draw() {
        en.tick();
        background(0,0,0);
        fill(255);
        text("length: "+en.getScore(), 50, 50);
        image(en.getDrawSurface(), 0, 100);
    }

    public void keyPressed() {
        switch(keyCode) {
            case UP: en.sn.setHeadOrientation(Orientation.UP);
            break;
            case LEFT: en.sn.setHeadOrientation(Orientation.LEFT);
            break;
            case RIGHT: en.sn.setHeadOrientation(Orientation.RIGHT);
            break;
            case DOWN: en.sn.setHeadOrientation(Orientation.DOWN);
            break;
            default:
        }
    }

    public static void main(String... args) {
        PApplet.main("de.nairolf.reSnake.Game");


    }
}

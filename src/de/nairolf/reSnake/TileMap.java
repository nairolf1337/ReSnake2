package de.nairolf.reSnake;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileMap {
    final PGraphics drawSurface;
    final int width;
    final int height;
    private int tileMap[][];
    private int tileWidth, tileHeight;

    public TileMap(PGraphics drawSurface, int width, int height) {
        this.drawSurface = drawSurface;
        this.width = width;
        this.height = height;
        this.tileMap = new int[width][height];
        this.tileWidth = drawSurface.width / width;
        this.tileHeight = drawSurface.height / height;
    }

    PVector toScreenCoordinates(float x, float y) {
        float xScreen = PApplet.map(x, 0, width, 0, drawSurface.width);
        float yScreen = PApplet.map(y, 0, height, 0, drawSurface.height);

        return new PVector(xScreen, yScreen);
    }

    PVector toScreenCoordinates(PVector mapCoordinates) {
        return toScreenCoordinates(mapCoordinates.x, mapCoordinates.y);
    }

    public void setTile(int x, int y, int color) {
        tileMap[x][y] = color;
    }
    public void setTile(PVector tileCoordinate, int color) { setTile((int)tileCoordinate.x, (int)tileCoordinate.y, color);}

    public int getTile(int x, int y) {
        return tileMap[x][y];
    }

    public void setTiles(int color, List<PVector> tiles) {
        for(PVector p : tiles)
            setTile((int)p.x, (int)p.y, color);
    }

    public void setTiles(int color, PVector p[]) {
        for (PVector pVector : p) {
            tileMap[(int)pVector.x][(int)pVector.y] = color;
        }
    }

    public void drawTiles() {
        int posX = 0, posY = 0;

        drawSurface.beginDraw();
        for(int y = 0; y < height; ++y) {
            posX = 0;
            for(int x = 0; x < width; ++x) {
                drawSurface.fill(tileMap[x][y]);
                drawSurface.rect(posX, posY, tileWidth, tileHeight);
                posX += tileWidth;
            }
            posY += tileHeight;
        }
        drawSurface.endDraw();
    }

    void resetTiles() {
        for(int[] row : tileMap) {
            Arrays.fill(row, 0);
        }
    }
}

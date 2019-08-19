package de.nairolf.reSnake;

import processing.core.PGraphics;
import processing.core.PVector;

import java.util.Arrays;
import java.util.Random;

public class Environment {
    final TileMap tileMap;
    final int width, height;
    boolean[][] walkableTiles;
    int tickCounter = 1;
    boolean newHeadToAdd = false;
    boolean lose = false;
    Snake sn = new Snake(this, Orientation.LEFT, 5, new PVector(3,4), new PVector(3,5));

    Random rnd = new Random();
    PVector foodPos;

    public Environment(PGraphics pg, int width, int height) {
        tileMap = new TileMap(pg, width, height);
        this.width = width;
        this.height = height;
        walkableTiles = new boolean[width][height];
        foodPos = rndUnoccupiedTile();

        for(boolean[] array : walkableTiles)
            Arrays.fill(array, true);

        for(PVector p : sn) {
            walkableTiles[(int)p.x][(int)p.y] = false;
        }
    }

    PVector rndUnoccupiedTile() {
        PVector p = new PVector();

        do {
            p.x = rnd.nextInt(width);
            p.y = rnd.nextInt(height);
        } while(sn.segments.contains(foodPos));

        return p;
    }

    private int modulus(int x, int y) {
        int result = x % y;

        if(result < 0)
            result += y;

        return result;
    }

    PVector nextPosition(Snake sn) {
        PVector nextPos = PVector.add(sn.getHead(), sn.getHeadOrientation().moveDelta);

        nextPos.x = modulus((int)nextPos.x, width);
        nextPos.y = modulus((int)nextPos.y, height);

        if(walkableTiles[(int)nextPos.x][(int)nextPos.y] == false) {
            lose = true;
        }

        walkableTiles[(int)nextPos.x][(int)nextPos.y] = false;
        if(nextPos.equals(foodPos)) {
            newHeadToAdd = true;
            foodPos = rndUnoccupiedTile();
        }
        else {
            PVector lastSegment = sn.segments.get(sn.segments.size()-1);
            walkableTiles[(int)lastSegment.x][(int)lastSegment.y] = true;
        }

        return nextPos;
    }

    static int color(int r, int g, int b) {
        return 0xFF000000 | ((r&0xFF) << 16) | ((g&0xFF) << 8) | (b&0xFF);
    }

    void drawSnake(Snake sn) {
        tileMap.setTiles(color(255,0,0), sn.segments);
        tileMap.setTile((int)foodPos.x, (int)foodPos.y, color(0,255,127));
    }

    void tick() {
        if(!lose) {
            if (tickCounter % sn.speed == 0) {
                if (newHeadToAdd) {
                    newHeadToAdd = false;
                    sn.move(true);
                } else
                    sn.move(false);
            }
            ++tickCounter;
        }
    }

    public int getScore() {
        return sn.segments.size();
    }

    PGraphics getDrawSurface() {
        tileMap.resetTiles();
        drawSnake(sn);
        tileMap.drawTiles();
        return tileMap.drawSurface;
    }
}

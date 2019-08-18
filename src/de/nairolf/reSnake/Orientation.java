package de.nairolf.reSnake;

import processing.core.PVector;

public enum Orientation {
    UP, DOWN, LEFT, RIGHT;

    public Orientation invalidMoveDir;
    public PVector moveDelta;

    static {
        UP.invalidMoveDir = DOWN;
        UP.moveDelta = new PVector(0, -1);

        DOWN.invalidMoveDir = UP;
        DOWN.moveDelta = new PVector(0,1);

        LEFT.invalidMoveDir = RIGHT;
        LEFT.moveDelta = new PVector(-1, 0);

        RIGHT.invalidMoveDir = LEFT;
        RIGHT.moveDelta = new PVector(1, 0);
    }
}

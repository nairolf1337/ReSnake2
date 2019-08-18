package de.nairolf.reSnake;

import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Snake implements Iterable<PVector> {
    Environment environment;
    List<PVector> segments = new ArrayList<PVector>();
    Orientation headOrientation;
    int speed = 0;


    public Snake(Environment environment, Orientation initialOrientation, int speed, PVector... positions) {
        segments = new ArrayList<PVector>(Arrays.asList(positions));
        this.headOrientation = initialOrientation;
        this.environment = environment;
        this.speed = speed;
    }

    Orientation getHeadOrientation() {
        return headOrientation;
    }

    void setHeadOrientation(Orientation headOrientation) {
        if(this.headOrientation.invalidMoveDir != headOrientation)
            this.headOrientation = headOrientation;
    }

    void move(boolean newHead) {
        PVector newHeadSegment = environment.nextPosition(this);

        if(!environment.newHeadToAdd) {
            for (int i = segments.size() - 1; i > 0; --i)
                segments.set(i, segments.get(i - 1));
            segments.set(0, newHeadSegment);
        }
        else
            segments.add(0, newHeadSegment);
    }

    public int getSpeed() {
        return speed;
    }

    public PVector getHead() {
        return segments.get(0);
    }

    @Override
    public Iterator<PVector> iterator() {
        return segments.iterator();
    }
}

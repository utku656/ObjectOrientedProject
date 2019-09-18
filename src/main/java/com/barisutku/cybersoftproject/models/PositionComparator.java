package com.barisutku.cybersoftproject.models;

import java.util.Comparator;

public class PositionComparator implements Comparator<Position> {

    @Override
    public int compare(Position alive1, Position alive2) {
        return Integer.compare(alive1.getPosition(), alive2.getPosition());
    }
}

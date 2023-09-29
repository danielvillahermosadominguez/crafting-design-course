package com.codurance.katayst.marsrovers;

import static java.util.Arrays.stream;

enum Direction {
    NORTH("N", "W", "E"),
    WEST("W", "S", "N"),
    SOUTH("S", "E", "W"),
    EAST("E", "N", "S");
    private final String current;
    private final String left;
    private final String right;


    Direction(String current, String left, String right) {
        this.current = current;
        this.left = left;
        this.right = right;
    }

    public String getCurrent() {
        return current;
    }

    private Direction rotate(String nextDirection) {
        var result = stream(values())
                .filter(direction -> direction.getCurrent().equals(nextDirection))
                .findFirst();

        return !result.isPresent() ? null : result.get();
    }

    public Direction rotateRight() {
        return rotate(right);
    }

    public Direction rotateLeft() {
        return rotate(left);
    }
}
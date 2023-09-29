package com.codurance.katayst.marsrovers;

import java.util.List;
import java.util.Optional;

public class Grid {
    private static final int MAX_HEIGHT = 10;
    private static final int MAX_WIDTH = 10;
    private final List<Coordinate> obstacles;

    public Grid(List<Coordinate> obstacles) {
        this.obstacles = obstacles;
    }

    Optional<Coordinate> move(Coordinate coordinate, Direction direction) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (direction == Direction.NORTH) {
            y = (y + 1) % MAX_HEIGHT;
        }

        if (direction == Direction.SOUTH) {
            y = (y > 0) ? y - 1 : MAX_HEIGHT - 1;
        }

        if (direction == Direction.EAST) {
            x = (x + 1) % MAX_WIDTH;
        }

        if (direction == Direction.WEST) {
            x = x > 0 ? x - 1 : MAX_WIDTH - 1;
        }

        var nextPosition = new Coordinate(x, y);

        var obstacle = findObstacle(nextPosition);

        return obstacle.isPresent()
                ? Optional.empty()
                : Optional.of(nextPosition);
    }

    private Optional<Coordinate> findObstacle(Coordinate position) {
        return obstacles
                .stream()
                .filter(obs -> obs.equals(position))
                .findFirst();
    }
}

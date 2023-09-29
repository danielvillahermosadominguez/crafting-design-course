package com.codurance.katayst.marsrovers;

import java.util.Optional;

public class Rover {
    private final Grid grid;
    private Direction direction = Direction.NORTH;

    private Coordinate position = new Coordinate(0, 0);
    private Optional<Object> obstacle = Optional.empty();

    public Rover(Grid grid) {
        this.grid = grid;
    }

    public String move(String commands) {
        for (var command : commands.toCharArray()) {
            if (command == 'L') {
                direction = direction.rotateLeft();
            }
            if (command == 'R') {
                direction = direction.rotateRight();
            }
            if (command == 'M') {
                var nextPosition = grid.move(position, direction);
                if(nextPosition.isPresent()){
                    position = nextPosition.get();
                }
                else {
                    obstacle = Optional.of(position);
                }
            }
        }

        return obstacle.isPresent()
                ? formatOutput("O:%s:%s:%s")
                : formatOutput("%s:%s:%s");
    }

    private String formatOutput(String format) {
        return String.format(format,
                position.getX(),
                position.getY(),
                direction.getCurrent()
        );
    }
}

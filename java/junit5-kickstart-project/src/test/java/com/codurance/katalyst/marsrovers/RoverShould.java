package com.codurance.katalyst.marsrovers;

import com.codurance.katayst.marsrovers.Coordinate;
import com.codurance.katayst.marsrovers.Grid;
import com.codurance.katayst.marsrovers.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RoverShould {

    private Rover rover;
    private Grid grid;

    @BeforeEach
    public void
    beforeEach() {
        grid = new Grid(List.of());
        rover = new Rover(grid);
    }

    @Test
    public void
    start_return_the_same_position_if_not_receive_any_instruction() {
        assertThat(rover.move("")).isEqualTo("0:0:N");
    }

    @ParameterizedTest
    @CsvSource({
            "L,0:0:W",
            "LL,0:0:S",
            "LLL,0:0:E",
            "LLLL,0:0:N"
    })
    public void
    rotate_to_the_left(String direction, String output) {
        assertThat(rover.move(direction)).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "R,0:0:E",
            "RR,0:0:S",
            "RRR,0:0:W",
            "RRRR,0:0:N",
    })
    public void
    rotate_to_the_right(String direction, String output) {
        assertThat(rover.move(direction)).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "M,0:1:N",
            "MM,0:2:N",
            "MMM,0:3:N",
            "MMMM,0:4:N"
    })
    public void
    move_to_the_north(String direction, String output) {
        assertThat(rover.move(direction)).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "RM,1:0:E",
            "RMM,2:0:E",
            "RMMM,3:0:E",
            "RMMMM,4:0:E",
    })
    public void
    move_to_the_east(String direction, String output) {
        assertThat(rover.move(direction)).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "MMMMMRRMMM,0:2:S",
            "MMMMMRRMMMM,0:1:S",
            "MMMMMRRMMMMM,0:0:S",
    })
    public void
    move_to_the_south(String direction, String output) {
        assertThat(rover.move(direction)).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "RMMMMMLLMMM,2:0:W",
            "RMMMMMLLMMMM,1:0:W",
            "RMMMMMLLMMMMM,0:0:W",
    })
    public void
    move_to_the_west(String direction, String output) {
        assertThat(rover.move(direction)).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "MMMMMMMMMM,0:0:N",
            "RMMMMMMMMMM,0:0:E",
            "RRM,0:9:S",
            "LM,9:0:W",
    })
    public void
    wrap_around(String direction, String output) {
        assertThat(rover.move(direction)).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "MMMM,O:0:3:N",
            "RRRMMMM,O:8:0:W",
    })
    public void
    crash_with_obstacle(String direction, String output) {
        var obstacles = new ArrayList<Coordinate>();
        obstacles.add(new Coordinate(0,4));
        obstacles.add(new Coordinate(7,0));
        var grid = new Grid(obstacles);
        var rover = new Rover(grid);
        assertThat(rover.move(direction)).isEqualTo(output);
    }
}

package com.xyzcorp.unnamedvariables;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Stream;

import static com.xyzcorp.unnamedvariables.SideEffects.sideEffect;
import static org.assertj.core.api.Assertions.assertThat;

public class UnnamedVariablesTest {
    @Test
    void testUnnamedVariables() {
        List<Order> orders = Order.createTwentyOrders();
        int total = 0;
        for (Order _ : orders)    // Unnamed variable
            total++;
        assertThat(total).isEqualTo(20);
    }

    @Test
    void testSideEffectForLoop() {
        for (int i = 0, _ = sideEffect(); i < 20; i++) {
            System.out.println(i);
        }
    }

    @SuppressWarnings("LoopStatementThatDoesntLoop")
    @Test
    void testIgnoreResults() {
        Queue<Integer> q = new LinkedList<>(List.of(10, 30, 50, 12, 11, 9, 20)); // x1, y1, z1, x2, y2, z2, ...
        Point point = null;
        while (q.size() >= 3) {
            var x = q.remove();
            var y = q.remove();
            var _ = q.remove();        // Unnamed variable
            point = new Point(x, y);
            break;
        }
        assertThat(point).isNotNull();
        assertThat(point.x()).isEqualTo(10);
        assertThat(point.y()).isEqualTo(30);
    }

    @Test
    void testCatchBlock() {
        String s = "Oh oh";
        try {
            int i = Integer.parseInt(s);
            System.out.println(STR."Amount is \{i}");
        } catch (NumberFormatException _) {        // Unnamed variable
            System.out.println(STR."Bad number: \{s}");
        }
    }

    @Test
    void testTryWithResources() throws SQLException {
        try (var _ = FakeDatabase.getConnection()) {    // Unnamed variable
            System.out.println("Doing something without the connection");
        }
    }

    @Test
    void testWithinALambda() {
        Optional<Integer> result = Stream.of(1, 10).reduce((integer, _) -> integer + 1);
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(2);
    }

    @Test
    void testAsTypePattern() {
        Ball ball = BallMachine.getBall();
        var description = switch (ball) {
            case RedBall _ -> "Red Ball";
            case GreenBall _ -> "Green Ball";
            case BlueBall _ -> "Blue Ball";
        };
        assertThat(description).isEqualTo("Red Ball");
    }

    @Test
    void testNestedTypePattern() {
        var boxOfBall = new BallBox<Ball>(new GreenBall(LocalDate.now()));
        String description = switch (boxOfBall) {
            case BallBox(RedBall _) -> "Box of Red Ball";   // Unnamed pattern variable
            case BallBox(BlueBall _) -> "Box of Blue Ball";   // Unnamed pattern variable
            case BallBox(GreenBall _) -> "Box of Green Ball";  // Unnamed pattern variable
            case BallBox(var _) -> "Box of Unknown Ball";  // Unnamed pattern variable
        };
        assertThat(description).isEqualTo("Box of Green Ball");
    }

    @Test
    void testNestedTypePatternWithCombinations() {
        var boxOfBall = new BallBox<Ball>(new BlueBall(30L));
        String description = switch (boxOfBall) {
            case BallBox(RedBall _), BallBox(BlueBall _) -> "Box of Blue or Red Ball";   // Unnamed pattern variable
            case BallBox(GreenBall _) -> "Box of Green Ball";  // Unnamed pattern variable
            case BallBox(var _) -> "Box of Unknown Ball";  // Unnamed pattern variable
        };
        assertThat(description).isEqualTo("Box of Blue or Red Ball");
    }
}

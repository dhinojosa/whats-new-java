package com.xyzcorp.sealed.functional;

public class Evaluator {
    static int evaluate(Expression expression) {
        return switch (expression) {
            case Constant c -> c.number();
            case Sum s -> evaluate(s.left()) + evaluate(s.right());
            case Subtract s -> evaluate(s.left()) - evaluate(s.right());
            case Multiply s -> evaluate(s.left()) * evaluate(s.right());
        };
    }

    /**
     * New Java 21 <a href="https://openjdk.org/jeps/440">Record Pattern Match</a>
     *
     * @since 21
     */
    static int evaluatePatternMatchAdvanced(Expression expression) {
        return switch (expression) {
            case Constant(var n) -> n;
            case Sum(var left, var right) -> evaluate(left) + evaluate(right);
            case Subtract(var left, var right) -> evaluate(left) - evaluate(right);
            case Multiply(var left, var right) -> evaluate(left) * evaluate(right);
        };
    }
}

package com.xyzcorp.sealed.functional;

public class Runner {
    public static void main(String[] args) {
        Expression expression =
            new Multiply(new Constant(40),
                new Sum(new Constant(40), new Constant(60)));
        System.out.println(evaluate(expression));
    }

    private static int evaluate(Expression expression) {
        return switch (expression) {
            case Constant(var n)               -> n;
            case Sum     (var left, var right) -> evaluate(left) + evaluate(right);
            case Subtract(var left, var right) -> evaluate(left) - evaluate(right);
            case Multiply(var left, var right) -> evaluate(left) * evaluate(right);
        };
    }
}

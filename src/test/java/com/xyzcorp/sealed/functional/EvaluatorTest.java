package com.xyzcorp.sealed.functional;

import org.junit.jupiter.api.Test;

public class EvaluatorTest {

    @Test
    void testBasicEvaluation() {
        Expression expression = new Multiply(new Constant(40),
            new Sum(new Constant(40), new Constant(60)));
        System.out.println(Evaluator.evaluate(expression));
    }

    @Test
    void testAdvancedEvaluation() {
        Expression expression = new Multiply(new Constant(40),
            new Sum(new Constant(40), new Constant(60)));
        System.out.println(Evaluator.evaluatePatternMatchAdvanced(expression));
    }
}

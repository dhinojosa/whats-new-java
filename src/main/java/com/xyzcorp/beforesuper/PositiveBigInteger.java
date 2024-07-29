package com.xyzcorp.beforesuper;

import java.math.BigInteger;

public class PositiveBigInteger extends BigInteger {
    public PositiveBigInteger(int value) {
       if (value < 0) throw new IllegalArgumentException("Number must be positive");
       super(Integer.toString(value)); //super must be the first call in a constructor
    }
}

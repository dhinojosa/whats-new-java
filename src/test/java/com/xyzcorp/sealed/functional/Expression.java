package com.xyzcorp.sealed.functional;

public sealed interface Expression permits Constant, Sum, Subtract, Multiply {
}



package com.xyzcorp.sealed2;

public sealed abstract class Shape permits Circle, Triangle {
    public abstract int area();
}

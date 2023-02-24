package com.xyzcorp.sealed.shapes;

public sealed abstract class Shape permits Circle, Triangle, Rectangle {
    public abstract int area();
}

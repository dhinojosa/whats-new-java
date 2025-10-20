package com.xyzcorp.streamgatherers;

import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Zippers {

    public static <T2> ObjToObjZipperBuilder<T2> zip(Stream<T2> other) {
        return new ObjToObjZipperBuilder<>(other);
    }

    public static <T2> ObjToIntZipperBuilder zip(IntStream other) {
        return new ObjToIntZipperBuilder(other);
    }

    public static record ObjToObjZipperBuilder<T2>(Stream<T2> other)
    {

    public <T1, R> ObjToObjZipper<T1, T2, R> with(BiFunction<T1, T2, R> zipperFunction) {
    		return new ObjToObjZipper<>(other, zipperFunction);
    	}

    }

    public static record ObjToIntZipperBuilder(IntStream other) {

    <T1, R> ObjToIntZipper<T1, R> with(ObjToIntZipper.ObjIntBiFunction<T1, R> zipperFunction) {
        return new ObjToIntZipper<>(other, zipperFunction);
    }
}}

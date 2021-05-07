package com.xyzcorp.sealed;

import java.util.StringJoiner;
import java.util.function.Function;

public final class MySome<T> extends MyOption<T>{
    private T t;
    public MySome(T t) {
        this.t = t;
    }

    @Override
    public <U> MyOption<U> map(Function<T, U> function) {
        return new MySome<>(function.apply(t));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MySome.class.getSimpleName() + "[", "]")
            .add("t=" + t)
            .toString();
    }
}

package com.xyzcorp.sealed;

import java.util.function.Function;

public final class MyNone extends MyOption<Void> {
    @Override
    public <U> MyOption<U> map(Function<Void, U> function) {
        return null;
    }
}

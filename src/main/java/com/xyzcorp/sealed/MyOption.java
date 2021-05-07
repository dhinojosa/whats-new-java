package com.xyzcorp.sealed;

import java.util.function.Function;

public abstract sealed class MyOption<T> permits MyNone, MySome {

   public abstract <U> MyOption<U> map(Function<T, U> function);
}

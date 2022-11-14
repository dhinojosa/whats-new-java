package com.xyzcorp.sealed.optionals;

public sealed interface MyOption<T> permits MyNone, MySome {
}

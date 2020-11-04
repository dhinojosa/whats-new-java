package com.xyzcorp.sealed;

public sealed interface MyOption<T> permits MyNone, MySome {
}

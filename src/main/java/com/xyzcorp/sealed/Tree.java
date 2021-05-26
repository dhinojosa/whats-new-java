package com.xyzcorp.sealed;

sealed interface Tree<A> permits Leaf, Branch {}

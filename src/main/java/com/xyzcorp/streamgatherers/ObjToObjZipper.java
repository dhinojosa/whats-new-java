package com.xyzcorp.streamgatherers;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public record ObjToObjZipper<T1, T2, R>(Stream<T2> other, BiFunction<T1, T2, R> zipperFunction) implements Gatherer<T1, Iterator<T2>, R> {

  @Override
  public Supplier<Iterator<T2>> initializer() {
    return () -> other.iterator();
  }

  @Override
  public Integrator<Iterator<T2>, T1, R> integrator() {
    return Integrator.ofGreedy((state, element, downstream) -> {
      if (state.hasNext()) {
        return downstream.push(zipperFunction.apply(element, state.next()));
      }

      return false;
    });
  }
}

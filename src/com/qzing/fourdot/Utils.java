package com.qzing.fourdot;
@FunctionalInterface
public interface Utils<F,T> {
    T run(F str);
}

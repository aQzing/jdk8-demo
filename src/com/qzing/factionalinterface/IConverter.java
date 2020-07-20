package com.qzing.factionalinterface;
@FunctionalInterface
public interface IConverter<F,T> {
    T convert(F numStr);
}


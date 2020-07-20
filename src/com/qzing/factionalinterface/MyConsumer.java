package com.qzing.factionalinterface;

import java.util.function.Consumer;

public class MyConsumer<T> implements Consumer {
    @Override
    public void accept(Object o) {
        System.out.println("....."+o);
    }
}

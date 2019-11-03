package com.snippets.tao.androidsnippets.demo.handler;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocal<T> {

    private Map<Thread, T> map;

    public ThreadLocal() {
        map = new HashMap<>();
    }

    public void set(T obj) {
        map.put(Thread.currentThread(), obj);
    }

    public T get() {
        return map.get(Thread.currentThread());
    }
}

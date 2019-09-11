package com.snippets.tao.androidsnippets.source.concurrency.chapter1;

import net.jcip.annotations.NotThreadSafe;

/**
Listing1.1. Non-threadsafe Sequence Generator.
 */

@NotThreadSafe
public class UnsafeSequece {
    private int value;

    public int getNext() {
        return value++;
    }
}

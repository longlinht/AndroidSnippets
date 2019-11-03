package com.snippets.tao.androidsnippets.source.concurrency.chapter1;

import net.jcip.annotations.GuardedBy;

/**
 * Listing1.2. Thread-safe Sequence Generator.
 *
 */
public class Sequence {

    @GuardedBy("this")
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}

package com.snippets.tao.androidsnippets.source.concurrency.chapter2;

import net.jcip.annotations.NotThreadSafe;

/**
 *
 * Listing2.3.Race condition in lazy initialization.Don't do this.
 *
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }

}


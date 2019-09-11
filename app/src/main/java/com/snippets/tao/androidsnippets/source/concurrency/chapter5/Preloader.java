package com.snippets.tao.androidsnippets.source.concurrency.chapter5;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Listing 5.12. Using FutureTask to preload data that is need later.
 */

public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {

        @Override
        public ProductInfo call() {
            return loadProductInfo();
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws InterruptedException {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw lancherThrowable(e.getCause());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RuntimeException lancherThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }

    private ProductInfo loadProductInfo() {
        return null;
    }
}

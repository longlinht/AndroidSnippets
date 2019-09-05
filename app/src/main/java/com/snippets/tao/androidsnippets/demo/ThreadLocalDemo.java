package com.snippets.tao.androidsnippets.demo;

import android.support.annotation.NonNull;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Tao He on 19-8-2.
 * hetaoof@gmail.com
 */


public class ThreadLocalDemo {

    public static void startDemo() throws InterruptedException {

        int threads = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(threads);
        final InnerClass innerClass = new InnerClass();
        for (int i=1; i<=threads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0; j<4; j++) {
                        innerClass.add(String.valueOf(j));
                        innerClass.print();
                    }

                    innerClass.set("hello world");
                    countDownLatch.countDown();
                }
            }, "thread- " + i).start();
        }
        countDownLatch.await();
    }


    private static class InnerClass {

        public void add(String newString) {
            StringBuilder stringBuilder = new StringBuilder();
            Counter.counter.set(stringBuilder.append(newString));
        }

        public void print() {
            System.out.printf("Thread name:%s , ThreadLocal hashcode:%s, Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }

        public void set(@NonNull String words) {
            Counter.counter.set(new StringBuilder(words));
            System.out.printf("Set, Thread name:%s , ThreadLocal hashcode:%s,  Instance hashcode:%s, Value:%s\n",
                    Thread.currentThread().getName(),
                    Counter.counter.hashCode(),
                    Counter.counter.get().hashCode(),
                    Counter.counter.get().toString());
        }
    }

    private static class Counter {

        @NonNull
        private static ThreadLocal<StringBuilder> counter = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };

    }
}

package com.snippets.tao.androidsnippets.source.concurrency.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tao He on 2019-09-26.
 * hetaoof@gmail.com
 *
 * Starup 3 threads to print ABC serial 10 times, Thread A print A, Thread B print B, Thread C print C;
 *
 */
public class PrintThreadWithReentrantLock {
    private char curLetter = 'A';

    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void start() {
        System.out.println("-----------------------------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new ThreadA('A'));
        executorService.execute(new ThreadB('B'));
        executorService.execute(new ThreadC('C'));
        executorService.shutdown();
    }

    private class ThreadA implements Runnable {

        private char name;
        public ThreadA(char n) {
            name = n;
        }

        @Override
        public void run() {
            for (int i=0; i<10; i++) {
                try {
                    lock.lock();
                    if (curLetter != name) {
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(name);
                    curLetter = 'B';
                    conditionB.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    private class ThreadB implements Runnable {

        private char name;
        public ThreadB(char n) {
            name = n;
        }

        @Override
        public void run() {
            for (int i=0; i<10; i++) {
                try {
                    lock.lock();
                    if (curLetter != name) {
                        try {
                            conditionB.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(name);
                    curLetter = 'C';
                    conditionC.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class ThreadC implements Runnable {

        private char name;
        public ThreadC(char n) {
            name = n;
        }

        @Override
        public void run() {
            for (int i=0; i<10; i++) {
                try {
                    lock.lock();
                    if (curLetter != name) {
                        try {
                            conditionC.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(name);
                    curLetter = 'A';
                    conditionA.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

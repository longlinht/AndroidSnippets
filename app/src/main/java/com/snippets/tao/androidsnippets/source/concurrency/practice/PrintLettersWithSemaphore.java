package com.snippets.tao.androidsnippets.source.concurrency.practice;

import com.snippets.tao.androidsnippets.logger.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Tao He on 2019-09-26.
 * hetaoof@gmail.com
 *
 * Starup 3 threads to print ABC serial 10 times, Thread A print A, Thread B print B, Thread C print C;
 *
 */






public class PrintLettersWithSemaphore {
    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);


    public void start() {
        System.out.println("-----------------------------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new ThreadA());
        executorService.execute(new ThreadB());
        executorService.execute(new ThreadC());
        executorService.shutdown();
    }

    private class ThreadA implements Runnable {

        @Override
        public void run() {
            for (int i=0; i<10; i++) {
                try {
                    semaphoreA.acquire();
                    System.out.println("A");
                    semaphoreB.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class ThreadB implements Runnable {

        @Override
        public void run() {
            for (int i=0; i<10; i++) {
                try {
                    semaphoreB.acquire();
                    System.out.println("B");
                    semaphoreC.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ThreadC implements Runnable {

        @Override
        public void run() {
            for (int i=0; i<10; i++) {
                try {
                    semaphoreC.acquire();
                    System.out.println("C");
                    semaphoreA.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

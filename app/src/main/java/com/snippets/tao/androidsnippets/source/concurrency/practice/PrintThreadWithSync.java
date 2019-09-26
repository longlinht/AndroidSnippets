package com.snippets.tao.androidsnippets.source.concurrency.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tao He on 2019-09-26.
 * hetaoof@gmail.com
 *
 * Starup 3 threads to print ABC serial 10 times, Thread A print A, Thread B print B, Thread C print C;
 *
 */
public class PrintThreadWithSync {
    private Letters letters = new Letters();

    public void start() {
        System.out.println("-----------------------------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new PrintThread('A'));
        executorService.execute(new PrintThread('B'));
        executorService.execute(new PrintThread('C'));
        executorService.shutdown();
    }

    class PrintThread implements Runnable {

        private char name;

        public PrintThread(char n) {
            name = n;
        }

        @Override
        public void run() {
            try {
                for (int i=0; i<10; i++) {
                    synchronized (letters) {
                        if (letters.getLetter() != name) {
                            letters.wait();
                        }

                        letters.print();
                        letters.nextLetter();
                        letters.notifyAll();
                    }
                }
            } catch (InterruptedException e) {

            }
        }
    }

}

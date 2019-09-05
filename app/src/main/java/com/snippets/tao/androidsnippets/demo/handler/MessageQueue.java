package com.snippets.tao.androidsnippets.demo.handler;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageQueue {

    private BlockingDeque<Message> blockingDeque = new LinkedBlockingDeque<>();

    public Message next() {
        try {
            return blockingDeque.take();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public void enqueueMessage(Message message) {
        try {
            blockingDeque.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

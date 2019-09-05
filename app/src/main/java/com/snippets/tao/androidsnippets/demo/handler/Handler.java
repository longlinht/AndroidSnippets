package com.snippets.tao.androidsnippets.demo.handler;

public class Handler {

    private MessageQueue messageQueue;

    public Handler() {
        Looper looper = Looper.myLooper();
        if (looper == null) {
            throw new RuntimeException("Can't create handler inside thread that not called Loop.prepared()");
        }

        this.messageQueue = looper.messageQueue;
    }

    public void sendMessage(Message message) {
        message.target = this;
        messageQueue.enqueueMessage(message);
    }

    public void handleMessage(Message message) {

    }
}

package com.snippets.tao.androidsnippets.demo.handler;

public class Message {
    Handler target;
    public Object obj;
    public int what;

    @Override
    public String toString() {
        return "what=" + what + "obj=" +obj;
    }
}

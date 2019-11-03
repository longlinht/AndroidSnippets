package com.snippets.tao.androidsnippets.source.concurrency.practice;

/**
 * Created by Tao He on 2019-09-26.
 * hetaoof@gmail.com
 */
public class Letters {
    private char letter = 'A';

    public char nextLetter() {
        if (letter == 'A') {
            letter = 'B';
        } else if (letter == 'B') {
            letter = 'C';
        } else if (letter == 'C') {
            letter = 'A';
        }
        return letter;
    }

    public char getLetter() {
        return letter;
    }

    public void print() {
        System.out.println(letter);
    }
}

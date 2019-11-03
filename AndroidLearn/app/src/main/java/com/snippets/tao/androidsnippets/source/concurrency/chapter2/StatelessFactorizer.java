package com.snippets.tao.androidsnippets.source.concurrency.chapter2;

import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;

/**
 * Listing2.1.A Stateless Servlet.
 * Stateless objects are always thread-safe.
 */

@ThreadSafe
public class StatelessFactorizer implements Servlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }

    private BigInteger extractFromRequest(ServletRequest request) {
        return BigInteger.valueOf(0);
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[5];
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {

    }
}

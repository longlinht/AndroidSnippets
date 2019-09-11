package com.snippets.tao.androidsnippets.source.concurrency.chapter2;

import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Listing2.4.Servlet counts request using AtomicLong.
 */

@ThreadSafe
public class CountingFactorizer implements Servlet {

    private final AtomicLong count = new AtomicLong();

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
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

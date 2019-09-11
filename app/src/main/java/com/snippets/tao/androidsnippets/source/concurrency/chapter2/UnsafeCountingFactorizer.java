package com.snippets.tao.androidsnippets.source.concurrency.chapter2;


import net.jcip.annotations.NotThreadSafe;

import java.math.BigInteger;

/**
 * Listing2.2 Servlet that Counts Requests without the Necessary Synchronization.Don't Do this.
 */

@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {

    private long count = 0;
    public long getCount() { return count; }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
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


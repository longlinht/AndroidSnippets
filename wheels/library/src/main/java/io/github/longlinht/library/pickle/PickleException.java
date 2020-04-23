package io.github.longlinht.library.pickle;

/**
 * Pickle Exception 用于统一捕获Pickle 产生的异常
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */
public class PickleException extends RuntimeException {

    public PickleException() {
    }

    public PickleException(String detailMessage) {
        super(detailMessage);
    }

    public PickleException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public PickleException(Throwable throwable) {
        super(throwable);
    }
}

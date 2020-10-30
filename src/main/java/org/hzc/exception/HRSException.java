package org.hzc.exception;




public class HRSException extends Exception {
    public HRSException(String message) {
        super(message);
    }

    public HRSException(String message, Throwable cause) {
        super(message, cause);
    }
}

package org.hzc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ResponseStatus优先自定义的handler exeption resolver
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalFormatException extends HRSException {
    public IllegalFormatException(String message) {
        super(message);
    }

    public IllegalFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}

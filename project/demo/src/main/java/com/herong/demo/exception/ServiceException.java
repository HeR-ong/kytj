package com.herong.demo.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -8248917601755821571L;

    public ServiceException(Throwable e) {
        super(e);
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }
}

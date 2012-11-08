package com.lsy.vehicle.dao;


public class EngineDaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EngineDaoException() {
        super();
    }

    public EngineDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EngineDaoException(String message) {
        super(message);
    }

    public EngineDaoException(Throwable cause) {
        super(cause);
    }

}

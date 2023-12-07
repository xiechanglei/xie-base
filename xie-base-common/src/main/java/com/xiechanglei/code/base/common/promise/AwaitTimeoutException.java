package com.xiechanglei.code.base.common.promise;

public class AwaitTimeoutException extends  RuntimeException{
    public static final AwaitTimeoutException INSTANCE = new AwaitTimeoutException();
}

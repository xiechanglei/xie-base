package com.xiechanglei.code.base.common.async;

public class AwaitTimeoutException extends  RuntimeException{
    public static final AwaitTimeoutException INSTANCE = new AwaitTimeoutException();
}

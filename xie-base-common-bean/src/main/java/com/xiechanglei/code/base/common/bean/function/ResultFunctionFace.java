package com.xiechanglei.code.base.common.bean.function;

public interface ResultFunctionFace<T, R> {
    R process(T t);
}

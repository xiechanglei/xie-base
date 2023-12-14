package io.github.xiechanglei.base.common.bean.function;

public interface ResultFunctionFace<T, R> {
    R process(T t);
}

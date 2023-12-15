package io.github.xiechanglei.base.common.function;

/**
 * 合成函数
 */
public interface ComposeFunction<T, R> {
    R apply(T t,T t1);
}

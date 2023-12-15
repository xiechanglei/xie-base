package io.github.xiechanglei.base.common.function;

/**
 * 合成函数
 */
public interface ComposeFunction<T, R> {
    R process(T t,T t1);
}

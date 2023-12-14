package io.github.xiechanglei.base.common.async;

public interface AsyncMessageProducer<K, T> {

    T getResponse(K key, long timeout) throws Exception;

    void putResponse(K key, T response);
}

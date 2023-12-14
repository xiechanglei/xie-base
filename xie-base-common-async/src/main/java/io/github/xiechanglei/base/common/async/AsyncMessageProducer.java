package io.github.xiechanglei.base.common.async;

public interface AsyncMessageProducer<K, T> {

    T wait(K key, long timeout) throws Exception;

    void put(K key, T response);
}

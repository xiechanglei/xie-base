package com.xiechanglei.code.base.common.promise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异步处理器
 */

public class LockAsyncMessageProducer<K, T> implements AsyncMessageProducer<K, T> {
    private final Map<K, AsyncLock> keyMap = new ConcurrentHashMap<>();
    private final Map<K, T> responseMap = new ConcurrentHashMap<>();

    public T getResponse(K key, long timeout) throws Exception {
        if (keyMap.containsKey(key)) {
            throw new Exception("key exists!");
        }
        AsyncLock lock = AsyncLock.create();
        keyMap.put(key, lock);
        lock.lock(timeout);
        if (keyMap.containsKey(key)) {
            keyMap.remove(key);
            throw AwaitTimeoutException.INSTANCE;
        } else {
            keyMap.remove(key);
            return responseMap.remove(key);
        }
    }

    public void putResponse(K key, T response) {
        if (keyMap.containsKey(key)) {
            responseMap.put(key, response);
            keyMap.remove(key).unlock();
        }
    }

}
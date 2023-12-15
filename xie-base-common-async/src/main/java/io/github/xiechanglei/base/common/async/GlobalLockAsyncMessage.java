package io.github.xiechanglei.base.common.async;

public class GlobalLockAsyncMessage {
    public static final LockAsyncMessageProducer<Object, Object> lockAsyncMessageProducer = new LockAsyncMessageProducer<>();

    @SuppressWarnings("unchecked")
    public static <T> T wait(Object key, long timeout) throws Exception {
        return (T) lockAsyncMessageProducer.await(key, timeout);
    }

    public static void put(Object key, Object response) {
        lockAsyncMessageProducer.put(key, response);
    }
}

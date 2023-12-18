package io.github.xiechanglei.base.rbac.init;
import io.github.xiechanglei.base.common.function.ComposeFunction;
import io.github.xiechanglei.base.common.function.SimpleFunction;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class StoreHandler<T> {
    private final SimpleFunction<T, Object> keyProcessor;
    private final ComposeFunction<T, Boolean> modifyProcessor;
    private final List<T> all;
    private final Map<Object, T> needStore = new HashMap<>();
    private final Map<Object, T> needUpdate = new HashMap<>();

    public StoreHandler(List<T> all, SimpleFunction<T, Object> keyProcessor, ComposeFunction<T, Boolean> modifyProcessor) {
        this.all = all;
        this.keyProcessor = keyProcessor;
        this.modifyProcessor = modifyProcessor;
    }

    public void add(T new_) {
        Object key = keyProcessor.apply(new_);
        Optional<T> first = all.stream().filter(a -> keyProcessor.apply(a).equals(key)).findFirst();
        if (first.isPresent()) {
            T old = first.get();
            needStore.put(key, first.get());
            if (modifyProcessor.apply(old, new_)) {
                needUpdate.put(key, old);
            }
        } else {
            needUpdate.put(key, new_);
        }
    }

    public List<T> getNeedDelete() {
        return all.stream().filter(a -> !needStore.containsKey(keyProcessor.apply(a))).collect(Collectors.toList());
    }
}

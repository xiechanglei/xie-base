package io.github.xiechanglei.base.rbac.init;

import io.github.xiechanglei.base.common.bean.function.CompareFunctionFace;
import io.github.xiechanglei.base.common.bean.function.ResultFunctionFace;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class StoreHandler<T> {
    private  ResultFunctionFace<T, Object> keyProcessor;
    private  CompareFunctionFace<T, Boolean> modifyProcessor;
    private List<T> all;
    private Map<Object, T> needStore = new HashMap<>();
    private Map<Object, T> needUpdate = new HashMap<>();

    public StoreHandler(List<T> all, ResultFunctionFace<T, Object> keyProcessor, CompareFunctionFace<T, Boolean> modifyProcessor) {
        this.all = all;
        this.keyProcessor = keyProcessor;
        this.modifyProcessor = modifyProcessor;
    }

    public void add(T new_) {
        Object key = keyProcessor.process(new_);
        Optional<T> first = all.stream().filter(a -> keyProcessor.process(a).equals(key)).findFirst();
        if (first.isPresent()) {
            T old = first.get();
            needStore.put(key, first.get());
            if (modifyProcessor.process(old, new_)) {
                needUpdate.put(key, old);
            }
        } else {
            needUpdate.put(key, new_);
        }
    }

    public List<T> getNeedDelete() {
        return all.stream().filter(a -> !needStore.containsKey(keyProcessor.process(a))).collect(Collectors.toList());
    }
}

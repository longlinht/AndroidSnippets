package com.snippets.tao.androidsnippets.demo;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Optional;
import java.util.function.Supplier;

public class NullResolve {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            // 可能会抛出空指针异常，直接返回一个空的 Optional 对象
            return Optional.empty();
        }
    }

}

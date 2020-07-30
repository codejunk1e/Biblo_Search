package com.robin.biblosearch.utils;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public final class RxResult<T> {

    private final T result;
    private final Throwable error;

    private RxResult(@Nullable T result, @Nullable Throwable error) {
        this.result = result;
        this.error = error;
    }

    @NonNull
    public static <T> RxResult<T> success(@NonNull T result) {
        return new RxResult(result, null);
    }

    @NonNull
    public static <T> RxResult<T> error(@NonNull Throwable error) {
        return new RxResult(null, error);
    }

    @Nullable
    public T getResult() {
        return result;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }
}
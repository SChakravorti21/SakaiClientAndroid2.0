package com.example.development.sakaiclient20.common;

/**
 * Created by Development on 8/5/18.
 */

public abstract class Builder<TSource, TResult> {

    protected TSource source;
    protected TResult result;

    public Builder(TSource source) {
        this.source = source;
    }

    public abstract Builder<TSource, TResult> build() throws Exception;

    public TResult getResult() {
        return this.result;
    }
}

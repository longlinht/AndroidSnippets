package io.github.longlinht.library.pickle.impl;

import com.google.gson.Gson;
import io.github.longlinht.library.pickle.Converter;

import java.lang.reflect.Type;

/**
 *
 * Created by Tao He on 18-4-27.
 * hetaoof@gmail.com
 *
 */

public class GsonConverter implements Converter {

    private final Gson gson;

    public GsonConverter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> String toJson(T value) {
        return gson.toJson(value);
    }

    @Override
    public <T> T fromJson(String json, Type clazz) {
        return gson.fromJson(json, clazz);
    }
}

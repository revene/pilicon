package com.pilicon.concurrency.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class ImmutableExample2 {

    private  static Map<Integer,Integer> MAP = Maps.newHashMap();

    static {
        MAP.put(1,4);
        MAP.put(2,7);
        MAP = Collections.unmodifiableMap(MAP);
    }

    public static void main(String[] args) {
        MAP.put(1,3);

    }
}

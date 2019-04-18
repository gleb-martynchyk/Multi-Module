package com.jazzteam.martynchyk.locator;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private Map<String, Object> services = new HashMap();

    public void add(String name, Object instance) {
        if (!has(name)) {
            services.put(name, instance);
        }
    }

    public boolean has(String name) {
        return services.containsKey(name);
    }

    public Object get(String name) {
        return services.get(name);
    }
}

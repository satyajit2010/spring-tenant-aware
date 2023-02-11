package com.sample.tenantaware;

import java.util.HashMap;
import java.util.Map;

public class TenantAwareRequestContext {
    public static final String TENANT = "tenant";
    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal
            .withInitial(HashMap::new);

    public static <T> T getAttribute(String name){
        return (T) CONTEXT.get().get(name);
    }

    public static void setAttribute(String name, Object value){
        CONTEXT.get().put(name, value);
    }

    public static <T> T removeAttribute(String name){
        return (T) CONTEXT.get().remove(name);
    }

    public static String getTenant(){
        return getAttribute(TENANT);
    }

    public static void setTenant(String tenant){
        setAttribute(TENANT, tenant);
    }

    public static void resetTenant(){
        removeAttribute(TENANT);
    }

}

package com.sample.tenantaware;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiTenantBean {
    /**
     * config properties to initialize the MultiTenantBean
     * */
    Class<? extends TenantAwareConfig<?>> config();
}

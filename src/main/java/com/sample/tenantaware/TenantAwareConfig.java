package com.sample.tenantaware;

public interface TenantAwareConfig<T> {
    T initialize();
}

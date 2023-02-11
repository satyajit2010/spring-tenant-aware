package com.sample.tenantaware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.TargetSource;

import java.util.Map;
@Slf4j
public class TenantAwareTargetSource implements TargetSource {
    private final Map<String, Object> tenantBeansMap;
    private final Class<?> targetClass;

    private final Object defaultTarget;

    public TenantAwareTargetSource(Map<String, Object> tenantBeansMap, Object defaultTarget) {
        this.defaultTarget = defaultTarget;
        this.targetClass = defaultTarget.getClass();
        this.tenantBeansMap = tenantBeansMap;
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getTarget() {
        String tenant = TenantAwareRequestContext.getTenant();
        log.info("Getting target for {}", tenant);
        return tenantBeansMap.getOrDefault(tenant, defaultTarget);
    }

    @Override
    public void releaseTarget(Object target) {
        log.info("Release target: {}", target);
    }
}

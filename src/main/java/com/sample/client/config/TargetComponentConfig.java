package com.sample.client.config;

import com.sample.client.service.TargetComponent;
import com.sample.tenantaware.TenantAwareConfig;
import lombok.Data;

@Data
public class TargetComponentConfig implements TenantAwareConfig<TargetComponent> {
    private String a;
    private String b;

    @Override
    public TargetComponent initialize() {
        return new TargetComponent("a: " + a + " b: " + b);
    }
}

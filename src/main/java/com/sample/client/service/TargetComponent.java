package com.sample.client.service;

import com.sample.client.config.TargetComponentConfig;
import com.sample.tenantaware.MultiTenantBean;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@MultiTenantBean(config = TargetComponentConfig.class)
public class TargetComponent{
    private String msg = "I'm fallback";

    public String echo() {
        return msg;
    }
}

package com.sample.client.api;

import com.sample.client.service.TargetComponent;
import com.sample.tenantaware.TenantAwareRequestContext;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("echo")
@AllArgsConstructor
public class EchoController {
    private final TargetComponent targetComponent;

    @GetMapping("{tenant}")
    public String echo(@PathVariable String tenant) {
        try {
            TenantAwareRequestContext.setTenant(tenant);
            return targetComponent.echo();
        } finally {
            TenantAwareRequestContext.resetTenant();
        }
    }

}

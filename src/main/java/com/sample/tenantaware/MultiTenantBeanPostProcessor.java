package com.sample.tenantaware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MultiTenantBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private Environment environment;
    private List<String> tenants;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        MultiTenantBean annotation = bean.getClass().getAnnotation(MultiTenantBean.class);
        if(annotation !=null) {
            log.info("Initializing MultiTenantAware bean::::" + beanName);
            Map<String, Object> tenantBeansMap = new HashMap<>();
            tenants.forEach(tenant -> {
                log.debug("initializing config for: " + tenant);
                TenantAwareConfig<?> tenantConfig = Binder.get(environment)
                        .bind(tenant, Bindable.of(annotation.config()))
                        .get();
                tenantBeansMap.put(tenant, tenantConfig.initialize());
                log.info(tenantConfig.toString());
            });
            ProxyFactory factory = new ProxyFactory(bean);
            factory.setTargetSource(new TenantAwareTargetSource(tenantBeansMap, bean));
            return factory.getProxy();
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        environment = beanFactory.getBean(Environment.class);
        tenants = environment.getProperty("tenants", List.class);
    }
}

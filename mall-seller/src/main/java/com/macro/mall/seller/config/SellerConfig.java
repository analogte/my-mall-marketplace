package com.macro.mall.seller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SellerConfig {

    @Bean
    public static org.springframework.beans.factory.config.BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new org.springframework.beans.factory.config.BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName)
                    throws org.springframework.beans.BeansException {
                if (bean instanceof springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider
                        || bean instanceof springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(
                    java.util.List<T> mappings) {
                java.util.List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(java.util.stream.Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private java.util.List<org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping> getHandlerMappings(
                    Object bean) {
                try {
                    java.lang.reflect.Field field = org.springframework.util.ReflectionUtils.findField(bean.getClass(),
                            "handlerMappings");
                    field.setAccessible(true);
                    return (java.util.List<org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping>) field
                            .get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}

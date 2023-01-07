package WebApp.Utils;

import WebApp.DataBase.Entities.Audit;
import WebApp.DataBase.Services.AuditService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProxyPostProcessor implements BeanPostProcessor {
    private final AuditService auditService;

    public ProxyPostProcessor(@Autowired AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public Object postProcessBeforeInitialization(@Nullable Object bean, @Nullable String beanName)
            throws BeansException {

        if (bean != null) {
            Class<?> beanClass = bean.getClass();
            if (Arrays.stream(beanClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(Audited.class))) {
                List<Class<?>> interfaces = this.getAllInterfaces(beanClass);

                return Proxy.newProxyInstance(beanClass.getClassLoader(), interfaces.toArray(new Class<?>[0]),
                        (proxy, method, args) -> {
                            if (Arrays.stream(bean.getClass().getMethods())
                                    .filter(m -> m.isAnnotationPresent(Audited.class))
                                    .anyMatch(m -> m.getName().equals(method.getName())))
                                auditService.log(new Audit(method.getName(), ZonedDateTime.now()));

                            return method.invoke(bean, args);
                        });
            }
        }

        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(@Nullable Object bean, @Nullable String beanName)
            throws BeansException {
        return bean;
    }

    private List<Class<?>> getAllInterfaces(Class<?> source) {
        List<Class<?>> interfaces = new ArrayList<>();

        do {
            interfaces.addAll(Arrays.asList(source.getInterfaces()));
            source = source.getSuperclass();
        } while (source != null);

        return interfaces;
    }
}

package jmetrics;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Metrics("")
@Priority(Interceptor.Priority.APPLICATION)
public class MetricsInterceptor implements Serializable {
  @Inject MetricsRepository metrics;
  
  @AroundInvoke
  public Object aroundInvoke(InvocationContext ic) throws Exception {
    Method method = ic.getMethod();
    Metrics annotation = method.getAnnotation(Metrics.class);
    if (annotation != null) {
      metrics.increment(annotation.value());
    }
    return ic.proceed();
  }
}

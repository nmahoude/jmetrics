package jmetrics.interceptors;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import jmetrics.MetricsRepository;
import jmetrics.annotations.Counter;
import jmetrics.metrics.CounterInfo;

@Interceptor
@Counter("")
@Priority(Interceptor.Priority.APPLICATION)
public class CounterInterceptor implements Serializable {
  @Inject MetricsRepository metrics;
  
  @AroundInvoke
  public Object aroundInvoke(InvocationContext ic) throws Exception {
    Method method = ic.getMethod();
    Counter counter = method.getAnnotation(Counter.class);

    CounterInfo counterInfo = metrics.get(counter);
    
    counterInfo.before();
    
    Object result = ic.proceed();

    counterInfo.after();
    metrics.publish(counterInfo);
    return result;
  }
}

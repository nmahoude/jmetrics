package jmetrics.interceptors;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import jmetrics.MetricsProducer;
import jmetrics.MetricsRepository;
import jmetrics.annotations.Counter;
import jmetrics.metrics.CounterInfo;

@Interceptor
@Counter(name="")
@Priority(Interceptor.Priority.APPLICATION)
public class CounterInterceptor implements Serializable {
  @Inject MetricsRepository metrics;
  
  @AroundInvoke
  public Object aroundInvoke(InvocationContext ic) throws Exception {
    if (ic.getMethod().getDeclaringClass() == MetricsProducer.class) return ic.proceed();

    Method method = ic.getMethod();
    Counter counter = method.getAnnotation(Counter.class);

    CounterInfo counterInfo = metrics.get(counter);
    
    Object result = ic.proceed();
    
    counterInfo.inc();
    
    return result;
  }
}

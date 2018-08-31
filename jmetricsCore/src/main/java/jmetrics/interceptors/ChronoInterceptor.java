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
import jmetrics.annotations.Chrono;
import jmetrics.metrics.ChronoInfo;
import jmetrics.metrics.CounterInfo;

@Interceptor
@Chrono("")
@Priority(Interceptor.Priority.APPLICATION)
public class ChronoInterceptor implements Serializable {
  @Inject MetricsRepository metrics;
  
  @AroundInvoke
  public Object aroundInvoke(InvocationContext ic) throws Exception {
    if (ic.getMethod().getDeclaringClass() == MetricsProducer.class) return ic.proceed();
    
    Method method = ic.getMethod();
    Chrono chrono = method.getAnnotation(Chrono.class);

    ChronoInfo  chronoInfo = metrics.get(chrono);
    
    chronoInfo.start();
    
    Object result = ic.proceed();

    chronoInfo.stop();
    return result;
  }
}

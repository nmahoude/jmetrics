package jmetrics;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import jmetrics.annotations.Chrono;
import jmetrics.annotations.Counter;
import jmetrics.annotations.Gauge;
import jmetrics.metrics.ChronoInfo;
import jmetrics.metrics.CounterInfo;
import jmetrics.metrics.GaugeInfo;

@ApplicationScoped
public class MetricsProducer {
  @Inject MetricsRepository metrics;
  
  @Produces
  @Gauge(name="DFLT_gauge")
  public GaugeInfo produceGauge(InjectionPoint ip) {
    Gauge gauge = ip.getAnnotated().getAnnotation(Gauge.class);
    String key = gauge.name();
    if (key.isEmpty()) {
      throw new IllegalArgumentException("Gauge name is missing");
    }

    GaugeInfo gaugeInfo = metrics.get(gauge);
    return gaugeInfo;
  }
  
  @Produces
  @Counter(name="DFLT_counter")
  public CounterInfo produceCounter(InjectionPoint ip) {
    Counter counter = ip.getAnnotated().getAnnotation(Counter.class);
    String key = counter.name();
    if (key.isEmpty()) {
      throw new IllegalArgumentException("Counter name is missing");
    }

    CounterInfo counterMetric = metrics.get(counter);
    return counterMetric;
  }

  @Produces
  @Chrono(name="DFLT_chrono")
  public ChronoInfo produceChrono(InjectionPoint ip) {
    Chrono chronoAnnotation = ip.getAnnotated().getAnnotation(Chrono.class);
    String key = chronoAnnotation.name();
    if (key.isEmpty()) {
      throw new IllegalArgumentException("Chrono name is missing");
    }

    ChronoInfo chrono = metrics.get(chronoAnnotation);
    return chrono;
  }
}

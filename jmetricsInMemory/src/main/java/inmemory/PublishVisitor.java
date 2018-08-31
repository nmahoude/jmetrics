package inmemory;

import java.util.Map;

import jmetrics.metrics.Chrono;
import jmetrics.metrics.ChronoInfo;
import jmetrics.metrics.Counter;
import jmetrics.metrics.CounterInfo;
import jmetrics.metrics.Gauge;
import jmetrics.metrics.GaugeInfo;
import jmetrics.metrics.Info;
import jmetrics.metrics.Metric;
import jmetrics.metrics.MetricInfoPublishVisitor;

public class PublishVisitor implements MetricInfoPublishVisitor {
  
  private Map<String, Metric> metrics;

  public PublishVisitor(Map<String, Metric> metrics) {
    this.metrics = metrics;
  }

  public void publish(CounterInfo counter) {
    Counter c = (Counter)metrics.get(counter.getKey());
    if (c == null) {
      c = new Counter(counter.getKey());
      metrics.put(counter.getKey(), c);
    }
    c.increment(counter.getIncrement());
  }
  
  public void publish(ChronoInfo chrono) {
    Chrono c = (Chrono)metrics.get(chrono.getKey());
    if (c == null) {
      c = new Chrono(chrono.getKey());
      metrics.put(chrono.getKey(), c);
    }
    c.add(chrono.duration());
  }

  public void publish(GaugeInfo info) {
    Gauge g = (Gauge)metrics.get(info.getKey());
    if (g == null) {
      g = new Gauge(info.getKey());
      metrics.put(info.getKey(), g);
    }
    g.setValue(info.getValue());
  }
  
  public void publish(Info info) {
    throw new RuntimeException("Unhandled metric : "+ info);
  }
}

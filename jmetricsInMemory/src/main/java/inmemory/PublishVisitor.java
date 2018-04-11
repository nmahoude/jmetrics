package inmemory;

import java.util.Map;

import jmetrics.metrics.Chrono;
import jmetrics.metrics.ChronoInfo;
import jmetrics.metrics.Counter;
import jmetrics.metrics.CounterInfo;
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
    c.increment();
  }
  
  public void publish(ChronoInfo counter) {
    Chrono c = (Chrono)metrics.get(counter.getKey());
    if (c == null) {
      c = new Chrono(counter.getKey());
      metrics.put(counter.getKey(), c);
    }
    c.add(counter.duration());
  }

  public void publish(Info info) {
    throw new RuntimeException("Unhandled metric : "+ info);
  }
}

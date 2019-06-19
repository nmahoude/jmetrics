package jmetrics.metrics;

import java.util.concurrent.atomic.AtomicLong;

import jmetrics.MetricsRepository;

public class CounterInfo extends Info {
  AtomicLong increment = new AtomicLong(0);
  
  public CounterInfo(MetricsRepository repository, String key) {
    super(repository, key);
  }

  @Override
  public void publish(MetricInfoPublishVisitor visitor) {
    visitor.publish(this);
  }

  public void inc() {
    increment.incrementAndGet();
    this.commit();
  }
  public void inc(int delta) {
    this.increment.getAndAdd(delta);
    this.commit();
  }

  public long getIncrement() {
    return increment.get();
  }

}

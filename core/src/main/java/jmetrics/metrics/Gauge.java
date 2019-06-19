package jmetrics.metrics;

import java.util.concurrent.atomic.AtomicLong;

import jmetrics.MetricOutputVisitor;

public class Gauge extends Metric {
  
  public Gauge(String name) {
    super(name);
    value.set(0);
  }

  AtomicLong value = new AtomicLong(0);
  
  public long getValue() {
    return value.get();
  }
  
  public void setValue(long value) {
    this.value.set(value);
  }

  @Override
  public void visit(MetricOutputVisitor visitor) {
    visitor.output(this);
  }
}

package jmetrics.metrics;

import java.util.concurrent.atomic.AtomicLong;

import jmetrics.MetricOutputVisitor;

public class Counter extends Metric {
  AtomicLong value;

  public Counter(String name) {
    super(name);
    this.value = new AtomicLong(0);
  }

  public void increment() {
    value.incrementAndGet();
  }

  public void increment(long delta) {
    this.value.getAndAdd(delta);
  }

  public long getValue() {
    return value.get();
  }
  
  @Override
  public void visit(MetricOutputVisitor visitor) {
    visitor.output(this);
  }
}

package jmetrics.metrics;

import java.util.concurrent.atomic.AtomicLong;

import javax.json.JsonObjectBuilder;

public class Metric {
  String name;
  AtomicLong value;
  
  public Metric(String name) {
    this.name = name;
    value = new AtomicLong(0);
  }
  public long increment() {
    return value.incrementAndGet();
  }
  public String getName() {
    return name;
  }
  public long getValue() {
    return value.get();
  }
  
  @Override
  public String toString() {
    return "{ \"value\": " + value.get() + "}";
  }
}

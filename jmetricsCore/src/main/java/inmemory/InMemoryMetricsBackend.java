package inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import jmetrics.MetricsBackend;

public class InMemoryMetricsBackend implements MetricsBackend {

  Map<String, AtomicInteger> values= new HashMap<>();
  
  @Override
  public void increment(String key) {
    AtomicInteger value = values.get(key);
    if (value == null) {
      value = new AtomicInteger(0);
      values.put(key, value);
    }
    int counter = value.incrementAndGet();
    System.out.println("Calling Metrics backend for " + key + ", new value is " + counter);
  }
}

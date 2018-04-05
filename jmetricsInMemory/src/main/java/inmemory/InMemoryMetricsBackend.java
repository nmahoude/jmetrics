package inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmetrics.MetricsBackend;
import jmetrics.metrics.Metric;

public class InMemoryMetricsBackend implements MetricsBackend {

  Map<String, Metric> metrics= new HashMap<>();
  
  @Override
  public void call(String key, long duration) {
    Metric metric = metrics.get(key);
    if (metric == null) {
      metric = new Metric(key);
      metrics.put(key, metric);
    }
    long counter = metric.increment();
    System.out.println("Calling Metrics backend for " + key + ", new counter is " + counter + ", duration of call was "+duration+ " ms");
  }
  
  @Override
  public List<Metric> getAll() {
    return new ArrayList<>(metrics.values());
  }
}

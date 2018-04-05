package jmetrics;

import java.util.List;

import jmetrics.metrics.Metric;

public interface MetricsBackend {
  public void call(String key, long duration);
  public List<Metric> getAll();
}

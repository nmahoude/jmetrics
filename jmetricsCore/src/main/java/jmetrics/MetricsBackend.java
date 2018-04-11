package jmetrics;

import java.util.List;

import jmetrics.metrics.Info;
import jmetrics.metrics.Metric;

public interface MetricsBackend {
  public List<Metric> getAll();
  public void  publish(Info info);
}

package jmetrics;

import jmetrics.metrics.Chrono;
import jmetrics.metrics.Counter;
import jmetrics.metrics.Gauge;

public interface MetricOutputVisitor {
  public void output(Counter counter);
  public void output(Chrono chrono);
  public void output(Gauge gauge);
}

package jmetrics;

import jmetrics.metrics.Chrono;
import jmetrics.metrics.Counter;

public interface MetricOutputVisitor {
  public void output(Counter counter);
  public void output(Chrono chrono);
}

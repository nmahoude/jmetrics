package jmetrics.metrics;

import jmetrics.MetricOutputVisitor;

public class Chrono extends Metric {
  long count;
  long totalDuration;
  
  public Chrono(String name) {
    super(name);
  }

  public void addDuration() {
  }

  public long getCount() {
    return count;
  }
  
  public long getMeanDuration() {
    return (long)(1.0 * totalDuration / count);
  }

  public void add(long duration) {
    totalDuration += duration;
    count++;
  }

  public long getTotalDuration() {
    return totalDuration;
  }

  @Override
  public void visit(MetricOutputVisitor visitor) {
    visitor.output(this);
  }

}

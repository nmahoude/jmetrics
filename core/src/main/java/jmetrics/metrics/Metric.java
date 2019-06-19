package jmetrics.metrics;

import jmetrics.MetricOutputVisitor;

public abstract class Metric {
  String name;
  
  public Metric(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public abstract void visit(MetricOutputVisitor visitor);
}

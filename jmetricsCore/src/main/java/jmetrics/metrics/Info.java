package jmetrics.metrics;

import jmetrics.MetricOutputVisitor;

public class Info {
  String key;
  
  public Info(String key) {
    this.key = key;
  }
  public String getKey() {
    return key;
  }
  public void publish(MetricInfoPublishVisitor visitor) {
    throw new RuntimeException("Not implemented");
  }

}

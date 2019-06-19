package jmetrics.metrics;

import jmetrics.MetricsRepository;

public class Info {
  private MetricsRepository repository;
  String key;
  
  public Info(MetricsRepository repository, String key) {
    this.repository = repository;
    this.key = key;
  }
  public String getKey() {
    return key;
  }
  public void publish(MetricInfoPublishVisitor visitor) {
    throw new RuntimeException("Not implemented");
  }

  public void commit() {
    repository.publish(this);
  }
}

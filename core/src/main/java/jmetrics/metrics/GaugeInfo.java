package jmetrics.metrics;

import jmetrics.MetricsRepository;

public class GaugeInfo extends Info {
  long value;
  
  public GaugeInfo(MetricsRepository repository, String key) {
    super(repository, key);
  }

  public long getValue() {
    return value;
  }

  public void setValue(long newValue) {
    this.value = newValue;
    this.commit();
  }

  @Override
  public void publish(MetricInfoPublishVisitor visitor) {
    visitor.publish(this);
  }
}

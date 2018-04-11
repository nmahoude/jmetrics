package jmetrics.metrics;

public class CounterInfo extends Info {

  public CounterInfo(String key) {
    super(key);
  }

  public void before() {
  }

  public void after() {
  }

  @Override
  public void publish(MetricInfoPublishVisitor visitor) {
    visitor.publish(this);
  }

}

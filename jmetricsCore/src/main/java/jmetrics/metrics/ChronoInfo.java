package jmetrics.metrics;

public class ChronoInfo extends Info {
  long start;
  long end;

  public ChronoInfo(String key) {
    super(key);
  }

  public void before() {
    start = System.currentTimeMillis();
  }

  public void after() {
    end = System.currentTimeMillis();
  }

  public long duration() {
    return end-start;
  }
  
  @Override
  public void publish(MetricInfoPublishVisitor visitor) {
    visitor.publish(this);
  }

}

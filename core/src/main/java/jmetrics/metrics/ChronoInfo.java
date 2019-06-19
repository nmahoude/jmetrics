package jmetrics.metrics;

import jmetrics.MetricsRepository;

public class ChronoInfo extends Info {
  long start;
  long end;

  public ChronoInfo(MetricsRepository repository,String key) {
    super(repository, key);
  }

  public void start() {
    start = System.currentTimeMillis();
  }

  public void stop() {
    end = System.currentTimeMillis();
    this.commit();
  }

  public long duration() {
    return end-start;
  }
  
  @Override
  public void publish(MetricInfoPublishVisitor visitor) {
    visitor.publish(this);
  }

}

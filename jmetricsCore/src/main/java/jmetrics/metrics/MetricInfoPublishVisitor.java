package jmetrics.metrics;

public interface MetricInfoPublishVisitor {
  public void publish(CounterInfo info);
  public void publish(ChronoInfo info);
  public void publish(GaugeInfo info);
}

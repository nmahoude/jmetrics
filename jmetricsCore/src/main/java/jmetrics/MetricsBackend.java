package jmetrics;

@FunctionalInterface
public interface MetricsBackend {
  public void increment(String value);
}

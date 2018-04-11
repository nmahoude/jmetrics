package inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmetrics.MetricsBackend;
import jmetrics.metrics.Chrono;
import jmetrics.metrics.ChronoInfo;
import jmetrics.metrics.Counter;
import jmetrics.metrics.CounterInfo;
import jmetrics.metrics.Info;
import jmetrics.metrics.Metric;

public class InMemoryMetricsBackend implements MetricsBackend {

  Map<String, Metric> metrics = new HashMap<>();

  @Override
  public void publish(Info info) {
    info.publish(new PublishVisitor(metrics));
  }
  
  @Override
  public List<Metric> getAll() {
    return new ArrayList<>(metrics.values());
  }
}

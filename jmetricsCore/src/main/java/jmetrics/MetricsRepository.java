package jmetrics;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import jmetrics.annotations.Chrono;
import jmetrics.annotations.Counter;
import jmetrics.metrics.ChronoInfo;
import jmetrics.metrics.CounterInfo;
import jmetrics.metrics.Info;

@Singleton
public class MetricsRepository {
  Logger LOGGER = Logger.getLogger("MetricsRepository");
  @Inject Instance<MetricsBackend> backendsCDI;
  List<MetricsBackend> backends = new ArrayList<>();

  CounterInfo nullCounter = new CounterInfo("NULL");
  ChronoInfo nullChrono = new ChronoInfo("NULL");
  
  @PostConstruct
  public void setup() {
    backendsCDI.forEach(backend -> backends.add(backend));
    if (backends.isEmpty()) {
      LOGGER.log(Level.WARNING, "No JMetrics backend found");
    } else {
      LOGGER.log(Level.INFO, "Found "+backends.size()+" JMetrics implementations");
      backends.forEach(b -> 
                  LOGGER.log(Level.INFO, "   - "+ b.getClass().getSimpleName())
                  );
    }
  }
  
  public MetricsBackend anyBackend() {
    if (backends.isEmpty()) {
      return null;
    } else {
      return backends.get(0);
    }
  }

  public CounterInfo get(Counter counter) {
    if (counter == null) return nullCounter;
    return new CounterInfo(counter.value());
  }

  public ChronoInfo get(Chrono chrono) {
    return new ChronoInfo(chrono.value());
  }
  
  public void publish(Info info) {
    if (info == nullCounter || info == nullChrono) return;
    backends.forEach(backend -> backend.publish(info));
  }
}

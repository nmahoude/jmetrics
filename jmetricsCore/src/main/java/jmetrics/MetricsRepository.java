package jmetrics;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MetricsRepository {
  Logger LOGGER = Logger.getLogger("MetricsRepository");
  @Inject Instance<MetricsBackend> backendsCDI;
  List<MetricsBackend> backends = new ArrayList<>();

  
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
  
  void increment(String key) {
    backends.forEach(backend -> backend.increment(key));
  }

}

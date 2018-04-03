package jmetrics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MetricsRepository {
  @Inject Instance<MetricsBackend> backendsCDI;
  List<MetricsBackend> backends = new ArrayList<>();

  
  @PostConstruct
  public void setup() {
    backendsCDI.forEach(backend -> backends.add(backend));
  }
  
  void increment(String key) {
    backends.forEach(backend -> backend.increment(key));
  }
  

}

package testapp;

import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import jmetrics.annotations.Chrono;
import jmetrics.annotations.Counter;
import jmetrics.annotations.Gauge;
import jmetrics.metrics.ChronoInfo;
import jmetrics.metrics.CounterInfo;
import jmetrics.metrics.GaugeInfo;

@Path("/")
@Stateless
public class EndPoint {

  
  @Inject @Counter(name = "manual_counter") CounterInfo manualCounter;
  @Inject @Gauge(name = "manual_gauge") GaugeInfo manuelGauge;
  @Inject @Chrono(name = "manual_chrono") ChronoInfo manuelChrono;
  
  @GET
  @Counter(name = "auto_counter")
  @Chrono(name = "auto_chrono")
  public String hello() {
    try {
      manuelGauge.setValue(new Random().nextInt(100));
      manualCounter.inc(5);
      
      manuelChrono.start();
      Thread.sleep(100 + new Random().nextInt(100));
      manuelChrono.stop();
    } catch (InterruptedException e) {
      System.out.println("bad error ...");
    }
    return "Hello Word!";
  }
}

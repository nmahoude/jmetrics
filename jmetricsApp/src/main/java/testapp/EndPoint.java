package testapp;

import java.util.Random;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import jmetrics.annotations.Chrono;
import jmetrics.annotations.Counter;

@Path("/")
@Stateless
public class EndPoint {

  @GET
  @Counter("app_metrics_get")
  @Chrono("app_metrics_get_chrono")
  public String hello() {
    try {
      Thread.sleep(100 + new Random().nextInt(100));
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "Hello Word!";
  }
}

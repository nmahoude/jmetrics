package testapp;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import jmetrics.Metrics;

@Path("/")
@Stateless
public class EndPoint {

  @GET
  @Metrics("app_metrics_get")
  public String hello() {
    return "Hello Word!";
  }
}

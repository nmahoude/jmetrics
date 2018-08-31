package restendpoint;

import java.util.List;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jmetrics.MetricsRepository;
import jmetrics.metrics.Metric;


@Path("/metrics")
public class JMetricRestEndpoint{

  @Inject MetricsRepository repository;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public JsonObject getMetrics() {
    JsonOutputVisitor jsonVisitor = new JsonOutputVisitor();
    List<Metric> metrics = repository.anyBackend().getAll();
    metrics.forEach(m -> {
      m.visit(jsonVisitor);
    }); 
    return jsonVisitor.output();
  }
  
  @GET
  @Path("/")
  @Produces("application/prometheus")
  public String getPromotheusMetricsAsAccept() {
    return producedPromotheusOutput();
  }

  @GET
  @Path("/prometheus")
  public String getPromotheusMetrics() {
    return producedPromotheusOutput();
  }

  private String producedPromotheusOutput() {
    PromotheusOutputVisitor promotheusVisitor = new PromotheusOutputVisitor();
    List<Metric> metrics = repository.anyBackend().getAll();
    metrics.forEach(m -> {
      m.visit(promotheusVisitor);
    }); 
    return promotheusVisitor.getOutput();
  }
}

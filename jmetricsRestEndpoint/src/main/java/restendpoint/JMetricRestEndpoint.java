package restendpoint;

import java.io.StringReader;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
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
    JsonObjectBuilder job = Json.createObjectBuilder();
    
    List<Metric> metrics = repository.anyBackend().getAll();
    metrics.forEach(m -> {
      JsonReader jsonReader = Json.createReader(new StringReader(m.toString()));
      JsonObject object = jsonReader.readObject();
      jsonReader.close();
      job.add(m.getName(), object);
    }
        );
    return job.build();
  }
  
  @GET
  @Path("/prometheus")
  public String getPromotheusMetrics() {
    PromotheusOutputVisitor promotheusVisitor = new PromotheusOutputVisitor();
    List<Metric> metrics = repository.anyBackend().getAll();
    metrics.forEach(m -> {
      m.visit(promotheusVisitor);
    }); 
    return promotheusVisitor.getOutput();
  }
}

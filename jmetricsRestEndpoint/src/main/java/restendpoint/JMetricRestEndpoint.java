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

import jmetrics.MetricsRepository;
import jmetrics.metrics.Metric;


@Path("/")
public class JMetricRestEndpoint{

  @Inject MetricsRepository repository;
  
  @GET
  @Path("/metrics")
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
  @Path("/prometheus_metrics")
  public String getPromotheusMetrics() {
    StringBuilder result = new StringBuilder("");
    List<Metric> metrics = repository.anyBackend().getAll();
    metrics.forEach(m -> {
      result.append(m.getName()).append(" ").append(m.getValue());
      
    }); 
    return result.toString();
  }
}

package restendpoint;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import jmetrics.MetricOutputVisitor;
import jmetrics.metrics.Chrono;
import jmetrics.metrics.Counter;
import jmetrics.metrics.Gauge;

public class JsonOutputVisitor implements MetricOutputVisitor {
  JsonArrayBuilder output = Json.createArrayBuilder();

  @Override
  public void output(Counter counter) {
    output.add(Json.createObjectBuilder()
        .add("type", "counter")
        .add("name", counter.getName())
        .add("value", counter.getValue())
        .build());
  }

  @Override
  public void output(Chrono chrono) {
    output.add(Json.createObjectBuilder()
        .add("type", "chrono")
        .add("name", chrono.getName())
        .add("count", chrono.getCount())
        .add("duration", chrono.getTotalDuration())
        .build());
  }

  @Override
  public void output(Gauge gauge) {
    output.add(Json.createObjectBuilder()
        .add("type", "gauge")
        .add("name", gauge.getName())
        .add("value", gauge.getValue())
        .build());
  }

  public JsonObject output() {
    return Json.createObjectBuilder().add("metrics", output.build()).build();
  }
}

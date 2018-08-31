package restendpoint;

import jmetrics.MetricOutputVisitor;
import jmetrics.metrics.Chrono;
import jmetrics.metrics.Counter;
import jmetrics.metrics.Gauge;
import jmetrics.metrics.Metric;

public class PromotheusOutputVisitor implements MetricOutputVisitor {
  private static final String newline = "\n\r";
  StringBuilder output = new StringBuilder();
  
  @Override
  public void output(Counter counter) {
    
    output.append("# HELP ").append(counter.getName()).append(" description ").append(newline);
    output.append("# TYPE ").append(counter.getName()).append(" counter").append(newline);
    output.append(counter.getName()+ " "+counter.getValue()).append(newline);
    output.append(newline);
  }

  @Override
  public void output(Chrono chrono) {
    output.append("# HELP ").append(chrono.getName()).append(" description ").append(newline);
    output.append("# TYPE ").append(chrono.getName()).append(" histogram").append(newline);
    output.append(chrono.getName()).append("_bucket{le=\"+Inf\"} ").append(chrono.getTotalDuration()).append(newline);
    output.append(chrono.getName()).append("_sum ").append(chrono.getTotalDuration()).append(newline);
    output.append(chrono.getName()).append("_count ").append(chrono.getCount()).append(newline);
    output.append(newline);
  }
  
  @Override
  public void output(Gauge gauge) {
    output.append("# HELP ").append(gauge.getName()).append(" description ").append(newline);
    output.append("# TYPE ").append(gauge.getName()).append(" gauge").append(newline);
    output.append(gauge.getName()+ " "+gauge.getValue()).append(newline);
    output.append(newline);
  }
  
  public String getOutput() {
    return output.toString();
  }
}

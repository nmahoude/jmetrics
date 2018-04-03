package testapp;

import javax.ws.rs.ApplicationPath;

import jmetrics.MetricsBackend;

@ApplicationPath("/")
public class Application extends javax.ws.rs.core.Application {

  public void dummy() {
    MetricsBackend backend = new MetricsBackend() {
      
      @Override
      public void increment(String value) {
        value = value + "";
      }
    };
    
  }
}

package com.example.helloworld;
/*
 * This is an *application class*
 */

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
  public static void main(String[] args) throws Exception {
    new HelloWorldApplication().run(args);
  }

  @Override
  public String getName() {
    return "hello-world";
  }

  @Override
  public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    // Nothing to do yet
  }

  @Override
  public void run(HelloWorldConfiguration configuration, Environment environment) {
    final HelloWorldResource resource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getName()
    );
    final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);
    environment.jersey().register(resource);
  }
}

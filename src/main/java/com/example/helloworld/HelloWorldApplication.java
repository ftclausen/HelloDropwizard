package com.example.helloworld;
/*
 * This is an *application class*
 */

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
    // Nothing to do yet
  }
}

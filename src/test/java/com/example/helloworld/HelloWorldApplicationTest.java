package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class HelloWorldApplicationTest {
  private final Environment environment = mock(Environment.class);
  private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
  private final HelloWorldApplication application = new HelloWorldApplication();
  private final HelloWorldConfiguration config = new HelloWorldConfiguration();
 // private final HelloWorldResource resource = new HelloWorldResource("template", "fred");

  @Before
  public void setup() throws Exception {
    when(environment.jersey()).thenReturn(jersey);
  }

  @Test
  public void buildsAHelloWorldResource() throws Exception {
    config.setName("fred");
    application.run(config, environment);

    verify(jersey).register(isA(HelloWorldResource.class));
  }

  /*
  @Test
  public void checkDefaultName() throws Exception {
    application.run(config, environment);

    assertThat(resource.sayHello(Optional.empty()), is("fred"));
  }
  */
}

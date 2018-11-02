package com.example.helloworld;

import com.example.helloworld.api.Saying;
import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class HelloWorldApplicationTest {
  private final Environment environment = mock(Environment.class);
  private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
  private final HelloWorldApplication application = new HelloWorldApplication();
  private final HelloWorldConfiguration config = new HelloWorldConfiguration();
 // private final HelloWorldResource resource = new HelloWorldResource("template", "fred");
  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  @Before
  public void setup() throws Exception {
    when(environment.jersey()).thenReturn(jersey);
  }

  @Test
  public void serializesToJSON() throws Exception {
    // Make a reference saying object
    final Saying saying = new Saying(1, "fred");
    // *Deserialise* reference JSON and then immediately serialise it again to get reference string
    final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/hello-world.json"),
            Saying.class));

    assertThat(MAPPER.writeValueAsString(saying)).isEqualTo(expected);
  }

  @Test
  public void deserializesFromJSON() throws Exception {
    final Saying saying = new Saying(1, "fred");

    // Deserialise the reference JSON into a Saying class and compare directly to our saying class above
    assertThat(MAPPER.readValue(fixture("fixtures/hello-world.json"), Saying.class)).isEqualTo(saying);
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

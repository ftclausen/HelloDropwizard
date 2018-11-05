package com.example.helloworld;

import com.codahale.metrics.MetricRegistry;
import com.example.helloworld.api.Saying;
import com.example.helloworld.resources.HelloWorldResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.mockito.Mockito.*;

public class HelloWorldApplicationTest {
  private final Environment environment = new Environment("template", new ObjectMapper(), null,
                                          new MetricRegistry(), null);
  private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
  private final HelloWorldApplication application = new HelloWorldApplication();
  private final HelloWorldConfiguration config = new HelloWorldConfiguration();
  private final Saying saying = new Saying(1, "fred");

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
  private static final String template = "%s";
  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
          .addResource(new HelloWorldResource(template, "fred"))
          .build();

  @Test
  public void serializesToJSON() throws Exception {
    // Make a reference saying object
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
  public void verifyEndpoints() {
    config.setName("fred");
    application.run(config, environment);
    final Set<String> healthList = environment.healthChecks().getNames();
    final String endpointsInfo = environment.jersey().getResourceConfig().getEndpointsInfo();

    assertThat(healthList).containsExactlyInAnyOrder("deadlocks", "template");
    assertThat(endpointsInfo).contains("/hello-world");
  }
}

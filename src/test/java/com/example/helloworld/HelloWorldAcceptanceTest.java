package com.example.helloworld;

import com.example.helloworld.api.Saying;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(DropwizardExtensionsSupport.class)
public class HelloWorldAcceptanceTest {
    private static DropwizardAppExtension<HelloWorldConfiguration> EXT = new DropwizardAppExtension<>(
            HelloWorldApplication.class,
            ResourceHelpers.resourceFilePath("test-config.yml")
    );

    @Test
    void HelloWorld_startupAndRegister() {
        Client client = EXT.client();
        String name = "BobTest";
        Saying referenceSaying = new Saying(1, String.format("Hello there, %s!", name));

        Response response = client.target(
                String.format("http://localhost:%d/hello-world/?name=%s", EXT.getLocalPort(), name))
                .request()
                .get();

        assertThat(response.getStatus(), is(equalTo(200)));
        Saying receivedSaying = response.readEntity(Saying.class);
        assertThat(receivedSaying, is(equalTo(referenceSaying)));
    }
}

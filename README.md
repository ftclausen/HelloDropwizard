### What's this?

Just my version of the Dropwizard hello world exercise. Typing it out to learn and also using Gradle

### Run Me

```
./gradlew run --args="server example.yml"
```

### Notes

We have several types of classes

- Application: This one includes a main method and does the tying together of everything
- Configuration: This specifies per-deployment options and represents a YAML configuration file as a POJO
- Representation: A class that _represents_ the JSON data on the wire
- Resource: Maps Jersey URIs to the tasks we need to do. Here would do the actual work. Needs to be registered with app
    class.
- Health Check: Make sure everything is OK - registered like a resource

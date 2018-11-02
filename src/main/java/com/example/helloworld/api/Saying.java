package com.example.helloworld.api;
/**
 * This is a *representation class*
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

public class Saying {
  private long id;

  @Length(max = 3)
  private String content;

  public Saying() {
    // Jackson deserialisation. What does that mean?
  }

  public Saying(long id, String content) {
    this.id = id;
    this.content = content;
  }

  @JsonProperty
  public long getId() {
    return id;
  }

  @JsonProperty
  public String getContent() {
    return content;
  }

  // QQ: Why no setters? Because id and content are set at object creation by the constructor

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Saying)) {
      return false;
    }

    Saying that = (Saying)o;
    return this.id == that.id && this.content.equals(that.content);
  }

  public int hashCode() {
    return Objects.hash(id, content);
  }

  public String toString() {
    return "id: " + id + ", content: " + content;
  }

}

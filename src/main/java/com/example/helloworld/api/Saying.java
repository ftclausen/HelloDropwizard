package com.example.helloworld.api;
/*
 * This is a Dropwizard representation class.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Saying {
    private long id;

    private String content;

    public Saying() {
        // For Jackson deserialisation
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

    @Override
    public String toString() {
        return "Saying{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saying saying = (Saying) o;
        return id == saying.id && Objects.equals(content, saying.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}

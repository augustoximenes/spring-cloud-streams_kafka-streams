package dev.augustoximenes.kafka.streams.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Key {
    @JsonProperty
    private String id;
}

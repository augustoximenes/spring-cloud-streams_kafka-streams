package dev.augustoximenes.kafka.streams.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Prospect {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
}

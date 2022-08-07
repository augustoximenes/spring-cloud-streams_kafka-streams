package dev.augustoximenes.kafka.streams.models.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Key {
    @JsonProperty
    private String id1;
    @JsonProperty
    private String id2;
}

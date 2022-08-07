package dev.augustoximenes.kafka.streams.models.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientKey {
    @JsonProperty
    private String id3;
}

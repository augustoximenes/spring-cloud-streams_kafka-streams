package dev.augustoximenes.kafka.streams.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EnrichmentAccountValue {
    @JsonProperty
    private String id;
    @JsonProperty
    private String status;
}

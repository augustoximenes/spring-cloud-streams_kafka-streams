package dev.augustoximenes.kafka.streams.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EnrichmentAccount {
    @JsonProperty
    private String id;
    @JsonProperty
    private String status;
}

package dev.augustoximenes.kafka.streams.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountValue {
    @JsonProperty
    private String id;
    @JsonProperty
    private String accountNumber;
}

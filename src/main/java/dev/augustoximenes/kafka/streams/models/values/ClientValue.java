package dev.augustoximenes.kafka.streams.models.values;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientValue {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String accountNumber;
    @JsonProperty
    private String status;
}

package org.example.telegramnews.api2.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Choice {
    public int index;
    public Message message;


    @JsonProperty("finish_reason")
    public String finishReason;
}






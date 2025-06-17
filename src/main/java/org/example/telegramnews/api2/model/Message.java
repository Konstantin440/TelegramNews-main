package org.example.telegramnews.api2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Message {
    public String role;
    public String content;
    public Object refusal;
}

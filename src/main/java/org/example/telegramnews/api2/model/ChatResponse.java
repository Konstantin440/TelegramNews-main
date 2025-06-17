package org.example.telegramnews.api2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ChatResponse {

    private static final Logger log = LoggerFactory.getLogger(ChatResponse.class);
    public String id;
    public String object;
    public long created;
    public String model;
    public List<Choice> choices = new ArrayList();
    @JsonProperty("system_fingerprint")
    public String systemFingerprint;

    public String getMessageResponse() {
        return choices.get(0).message.content.trim().replace("\"", "");

    }
}






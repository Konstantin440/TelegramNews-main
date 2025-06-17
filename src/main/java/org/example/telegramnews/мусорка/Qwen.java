//package org.example.telegramnews.ai;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class Qwen {
//    private Choice[] choices;
//
//    public String getGeneratedText() {
//        if (choices != null && choices.length > 0 && choices[0].message != null) {
//            return choices[0].message.content;
//        }
//        return null;
//    }
//
//    public static class Choice {
//        private Message message;
//
//        public Message getMessage() {
//            return message;
//        }
//    }
//
//    public static class Message {
//        private String content;
//
//        public String getContent() {
//            return content;
//        }
//    }
//}
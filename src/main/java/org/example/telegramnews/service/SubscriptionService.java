package org.example.telegramnews.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class SubscriptionService {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);
    private final Set<Long> subscribers ;

    public SubscriptionService(Set<Long> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<Long> getSubscribers() {
        return subscribers;
    }

    public void subscribe(Long chatId) {
        log.info("Subscribing to chatId: " + chatId);
        subscribers.add(chatId);
    }


}

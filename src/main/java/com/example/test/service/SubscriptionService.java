package com.example.test.service;

import com.example.test.model.Subscription;
import com.example.test.model.User;
import com.example.test.model.dto.SubscriptionDTO;
import com.example.test.repository.SubscriptionRepository;
import com.example.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public Subscription addSubscription(Long userId, String serviceName) {
        User user = userRepository.findById(userId).orElseThrow();
        Subscription subscription = new Subscription();
        subscription.setServiceName(serviceName);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setUser(user);
        log.info("Adding subscription for user {}", userId);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public List<SubscriptionDTO> getTopSubscriptions() {
        return subscriptionRepository.findTop3Subscriptions().stream()
                .map(result -> new SubscriptionDTO(
                        (String) result[0],
                        (Long) result[1]
                ))
                .collect(Collectors.toList());
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
        log.info("Deleting subscription for user {}", id);
    }
}

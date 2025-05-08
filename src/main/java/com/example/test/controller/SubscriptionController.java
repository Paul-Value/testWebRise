package com.example.test.controller;

import com.example.test.model.Subscription;
import com.example.test.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long userId,
                                                        @RequestBody Map<String, String> request) {
        String serviceName = request.get("serviceName");
        return ResponseEntity.ok(subscriptionService.addSubscription(userId, serviceName));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUserId(userId));
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Subscription> deleteSubscription(@PathVariable Long userId, @PathVariable Long subId) throws AccessDeniedException {
        subscriptionService.deleteSubscription(userId, subId);
        return ResponseEntity.noContent().build();
    }
}

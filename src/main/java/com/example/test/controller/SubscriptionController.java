package com.example.test.controller;

import com.example.test.model.Subscription;
import com.example.test.model.dto.SubscriptionDTO;
import com.example.test.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Subscription> deleteSubscription(@PathVariable Long userId, @PathVariable Long subId) {
        subscriptionService.deleteSubscription(subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionDTO>> getTopSubscriptions(@PathVariable String userId) {
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}

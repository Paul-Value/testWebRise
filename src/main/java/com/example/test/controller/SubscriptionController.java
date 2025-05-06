package com.example.test.controller;

import com.example.test.model.Subscription;
import com.example.test.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long userId, @RequestBody String serviceName) {
        return ResponseEntity.ok(subscriptionService.addSubscription(userId, serviceName));
    }

    @GetMapping("/top")
    public ResponseEntity<List<Object[]>> getTopSubscriptions(@PathVariable String userId) {
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}

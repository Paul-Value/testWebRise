package com.example.test.repository;

import com.example.test.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    Optional<Subscription> findById(Long id);

    @Query("SELECT s.serviceName, COUNT(s) as count FROM Subscription s GROUP BY s.serviceName ORDER BY count DESC LIMIT 3")
    List<Object[]> findTop3Subscriptions();
}

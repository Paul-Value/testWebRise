package com.example.test.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubscriptionDTO {
    private String serviceName;
    private Long count;
}

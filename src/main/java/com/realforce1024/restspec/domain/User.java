package com.realforce1024.restspec.domain;

import lombok.Data;

import java.util.UUID;
import java.util.random.RandomGenerator;

/**
 * @author RealForce1024
 */
@Data
public class User {
    private Long id = RandomGenerator.getDefault().nextLong();
    private String username;
}

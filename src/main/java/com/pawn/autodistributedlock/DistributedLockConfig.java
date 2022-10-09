package com.pawn.autodistributedlock;

import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@EnableAutoConfiguration
public class DistributedLockConfig {

    @Bean
    public RLockAspect rLockAspect(RedissonClient redissonClient) {
        return new RLockAspect(redissonClient);
    }

}

package cc.codedhyan.codeitup.config;

import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {


    private CaffeineCache buildCache(String name, Duration expireAfterWriteDuration) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .recordStats()
                .expireAfterWrite(expireAfterWriteDuration)
                .build());
    }

    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caches = new ArrayList<>();

        caches.add(buildCache("otp_cache", Duration.of(120, ChronoUnit.SECONDS)));
        // add more caches here ....

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);

        return cacheManager;
    }

}

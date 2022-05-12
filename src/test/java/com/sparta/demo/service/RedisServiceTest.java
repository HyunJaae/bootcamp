package com.sparta.demo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED) // 실제 DB 사용하고 싶을때 NONE 사용
@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // redisTemplate 을 주입받은 후에 원하는 Key:Value 타입에 맞게 Operations 를 선언해서 사용할 수 있다.
    @Nested
    @DisplayName("Redis test")
    class BasicRedisTest {

        @Test
        @DisplayName("Redis Operation: String 자료구조")
        void testStrings() {
            // given
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String key = "stringKey";

            // when
            valueOperations.set(key, "hello");

            //then
            String value = valueOperations.get(key);
            assertThat(value).isEqualTo("hello");
        }

        @Test
        @DisplayName("Redis Operation: Set 자료구조")
        void testSet() {
            // given
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            String key = "setKey";

            // when
            setOperations.add(key, "h", "e", "l", "l", "o");

            // then
            Set<String> members = setOperations.members(key);
            Long size = setOperations.size(key);

            assertThat(members).containsOnly("h", "e", "l", "o");
            assertThat(size).isEqualTo(4);
        }

        @Test
        @DisplayName("Redis Operation: Hash 자료구조")
        void testHash() {
            // given
            HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
            String key = "hashKey";

            //when
            hashOperations.put(key, "hello", "world");

            //then
            Object value = hashOperations.get(key, "hello");
            assertThat(value).isEqualTo("world");

            Map<Object, Object> entries = hashOperations.entries(key);
            assertThat(entries.keySet()).containsExactly("hello");
            assertThat(entries.values()).containsExactly("world");

            Long size = hashOperations.size(key);
            assertThat(size).isEqualTo(entries.size());
        }
    }
}

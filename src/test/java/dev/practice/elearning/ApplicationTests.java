package dev.practice.elearning;

import dev.practice.elearning.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}

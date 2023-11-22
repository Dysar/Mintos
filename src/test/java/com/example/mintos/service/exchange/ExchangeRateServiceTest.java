package com.example.mintos.service.exchange;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "api.key=test-api-key",
        "api.url=http://test-api.com/"
})
class ExchangeRateServiceTest {


}
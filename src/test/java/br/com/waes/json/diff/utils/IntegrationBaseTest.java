package br.com.waes.json.diff.utils;

import br.com.waes.json.diff.Application;
import br.com.waes.json.diff.configuration.AppConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(value = {AppConfiguration.class})
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationBaseTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }
}

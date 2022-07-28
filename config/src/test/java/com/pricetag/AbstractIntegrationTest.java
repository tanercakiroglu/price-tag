package com.pricetag;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ContextConfiguration(classes = {H2TestProfileJPAConfig.class, PropertySourcesPlaceholderConfigurer.class},
        initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.properties" })
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public abstract class AbstractIntegrationTest {

}

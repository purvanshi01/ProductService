package com.example.productservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /* when you run the application spring will go through all the special classes
    * like @configuration @service etc.
    * rest template is just one of the http client there are others also
    * spring will create one object of rest template and store it inside bean
    * it will create a bean out of it
    * whenever you need a object of rest template then this will be used
    * you don't need to create an object of rest template anywhere in the code
    * just inject it like a constructor, and you are done
    * see FakeStoreProductService for this*/
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}

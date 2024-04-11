package com.example.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This controller supports https or rest apis
// The requests coming to end point /hello transfer them to this controller
@RestController
@RequestMapping("/hello")
public class SampleController {

    @GetMapping("/{name}/{number}")
    public String sayHello(@PathVariable("name") String givenName, @PathVariable("number") int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            sb.append("Hello " + givenName);
        }
        // return "Hello! " + givenName + " " + givenCity;
        return sb.toString();
    }
}

package com.example.pactissue.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "my", configuration = MyFeignConfiguration.class, primary = false, url = "localhost:7771")
public interface MyClient {
    @RequestMapping(value = "v1/map", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    String map(@RequestBody String query);
}

package com.example.webclient.client;

import com.example.webclient.aspect.LogMethod;
import com.example.webclient.models.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transfer")
public interface AccountApi {
    @LogMethod
    @PostMapping()
    ResponseEntity<?> take(@RequestBody Transaction transaction);

    @LogMethod
    @PostMapping("/rollback")
    void rollback(@RequestBody Transaction transaction);

}

package com.example.springboot.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.api.PingApi;
import io.swagger.model.InlineResponse200;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PingApiController implements PingApi {

    @Override
    public ResponseEntity<InlineResponse200> getPing() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.toString());
        val pong = new InlineResponse200().message("pong");
        return new ResponseEntity<>(pong, HttpStatus.OK);
    }
}

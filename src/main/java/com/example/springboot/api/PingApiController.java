package com.example.springboot.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.InlineResponse200;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PingApiController extends io.swagger.api.PingApiController {

    public PingApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }

    public ResponseEntity<InlineResponse200> getPing() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.toString());
        val pong = new InlineResponse200().message("pong");
        return new ResponseEntity<>(pong, HttpStatus.OK);
    }
}

package com.ygo.server.api.controller;

import com.ygo.server.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //GET
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<Object> ping() {
        log.info(userService.getNow());
        return ResponseEntity.ok().body("pong");
    }
    //POST
    //PATCH
    //DELETE
}

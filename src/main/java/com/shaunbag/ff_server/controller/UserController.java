package com.shaunbag.ff_server.controller;

import com.shaunbag.ff_server.dto.MyUserDto;
import com.shaunbag.ff_server.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    MyUserService myUserService;

    @PostMapping("/user")
    public ResponseEntity<Long> saveUser(@RequestBody MyUserDto myUserDto){
        return
                ResponseEntity.ok(myUserService.save(myUserDto));
    }



}

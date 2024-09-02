package com.example.RESTAPI.controller;

import com.example.RESTAPI.model.User;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Valeev A.R.
 */

@RestController
public class MainController {

    private long setOffsetTime(int minOffsetTime, int maxOffsetTime) throws InterruptedException {
        long start = System.currentTimeMillis();
        int randomTime = (int) ((Math.random() * (maxOffsetTime - minOffsetTime)) + minOffsetTime);
        Thread.sleep(randomTime);
        return System.currentTimeMillis() - start;
    }

    @GetMapping(path="/")
    @ResponseBody
    public ResponseEntity getUser() throws InterruptedException {
        long offsetTime = setOffsetTime(1000,2000);
        /* Set header:
         MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
         map.add("Set-Cookie","jmeter=true"); - cookie
         map.add("jmeter","true"); - header
         new ResponseEntity(data, header, status); }
        */
        return new ResponseEntity(new User("static","Static"), HttpStatus.OK);
    }

    @PostMapping(path="/class")
    @ResponseBody
    public ResponseEntity<?> postUser(@Valid @RequestBody User user) throws InterruptedException {
        long offsetTime = setOffsetTime(1000,2000);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path="/map")
    @ResponseBody
    public ResponseEntity<?> postUsers(@RequestBody Map<String, String> body) throws InterruptedException {
        long offsetTime = setOffsetTime(1000,2000);
        if ( body.get("login") != null && body.get("password") != null && body.size() == 2 && !body.get("login").trim().isEmpty() && !body.get("password").trim().isEmpty() )
            return new ResponseEntity<>(new User(body.get("login"), body.get("password")), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
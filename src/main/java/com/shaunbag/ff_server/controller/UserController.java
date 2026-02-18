package com.shaunbag.ff_server.controller;

import com.shaunbag.ff_server.dto.MyUserDto;
import com.shaunbag.ff_server.model.MyUser;
import com.shaunbag.ff_server.services.MyUserDetailsService;
import com.shaunbag.ff_server.services.MyUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    MyUserService myUserService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @PostMapping(
            path = "/user",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> saveUser(MyUserDto myUserDto, HttpServletRequest httpRequest){
        Map<String, Object> body = new HashMap<>();

        if (myUserService.existsByUserName(myUserDto.username())) {
            body.put("code", "USERNAME_EXISTS");
            body.put("message", "Username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }

        String currentPassword = myUserDto.password();
        String encrypted = passwordEncoder.encode(currentPassword);
        MyUserDto newUser = new MyUserDto(myUserDto.username(), encrypted);

        MyUser user = myUserService.save(newUser);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        body.put("code", "OK");
        body.put("redirectUrl", "/");
        return ResponseEntity.ok(body);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.logout(request,response,authentication);
        return ResponseEntity.ok().build();
    }



}

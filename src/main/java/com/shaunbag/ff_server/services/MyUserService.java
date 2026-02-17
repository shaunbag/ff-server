package com.shaunbag.ff_server.services;

import com.shaunbag.ff_server.dto.MyUserDto;
import com.shaunbag.ff_server.model.MyUser;
import com.shaunbag.ff_server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MyUserService {

    private final UserRepository userRepository;

    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MyUser save(MyUserDto myUserDto){
        MyUser user = new MyUser();
        user.setUsername(myUserDto.username());
        user.setPassword(myUserDto.password());
        user.setCreated(LocalDate.now());
        user.setRole("USER");
        return userRepository.save(user);
    }

    public MyUser getMyUserDetails(UserDetails userDetails){
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}

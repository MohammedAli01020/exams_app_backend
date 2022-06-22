package com.example.exame_backend.security;

import com.example.exame_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsImpl implements UserDetailsService {


    final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.example.exame_backend.model.persisitece.UserDetails user = userRepository.findByUsername(username);

        if (user == null) {
            throw  new UsernameNotFoundException("user not found");
        }

        return new CustomUserDetails(user);
    }
}

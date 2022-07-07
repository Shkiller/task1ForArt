package com.example.task1.security;

import com.example.task1.entity.Person;
import com.example.task1.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Person user = personRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("user" + nickname + " " + "not found"));
        return SecurityUser.fromUser(user);
    }
}

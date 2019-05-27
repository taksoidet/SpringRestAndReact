package com.example.cardatabase.service;

import com.example.cardatabase.domain.Users;
import com.example.cardatabase.domain.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users currentUser=usersRepository.findByUsername(username);
        UserDetails userDetails=new User(username, currentUser.getPassword(),true,true,
                true,true, AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return userDetails;
    }
}

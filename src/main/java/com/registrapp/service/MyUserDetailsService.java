package com.registrapp.service;

import com.registrapp.dao.UserDao;
import com.registrapp.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        com.registrapp.models.User user = userDao.getUserByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("user not found");
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);}

    private User buildUserForAuthentication(com.registrapp.models.User user, List<GrantedAuthority> authorities) {
        return new User(user.getFirstname(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        Role userRole = userRoles;
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

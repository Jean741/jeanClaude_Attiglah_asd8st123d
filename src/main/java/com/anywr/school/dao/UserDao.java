package com.anywr.school.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    private final static List<UserDetails> APPLICATION_USERS = List.of(
            new User("user","$2a$12$18k6QT5pRUaYQ4cj0xNRdusG/BqUv3PqBEGh4idNvIW.sH6t83x.e",//for explanation real Value : user
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))),
            new User("admin","$2a$12$uSqRLu2EAX8M52IE4KsMVei1qUwj/AhrAXo9eNkRyAhsyUIyTxWAq",//for explanation real Value : admin
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            //For testing encoded passwords are formed with "passwd+{username}"
            new User("abigail","passwdsarah",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_TEACHER"))),
            new User("jhon","$2a$12$AYxtBWlJvqnnwPeTehH/IeJDX3PAt2DzARCtknpHH11CsjP..Yd9C",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("anderson","$2a$12$sbeYVR94S/aAyJ58nLlIA.jB1089d2TvmHPxGBQDDSeVI6J2bR9cW",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"))),
            new User("sarah","$2a$12$F29xCqTRTlX1gBm4A.uK.eKg7LCwWPstNZSteoO4VbfeHEdK/ytP.",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT")))
    );
    public UserDetails findUserByUsername(String username){
        {
            return APPLICATION_USERS.stream()
                    .filter(user->user.getUsername().equals(username))
                    .findFirst()
                    .orElseThrow(()-> new UsernameNotFoundException( String.format("User with username: %s was not found ",username)));
        }
    }

}

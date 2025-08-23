package com.system.education.auth.auth.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class GrantedAuthorities {
    public static List<GrantedAuthority> get(List<String> roleNames) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String roleName : roleNames) {
            grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
        }
        return grantedAuthorities;
    }
}

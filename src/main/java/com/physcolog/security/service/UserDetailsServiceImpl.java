package com.physcolog.security.service;

import com.physcolog.entities.Role;
import com.physcolog.entities.User;
import com.physcolog.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Data
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Adı " + username + " olan kullanıcı bulunamadı..! "));

        List<SimpleGrantedAuthority> authorities = buildGrantedAuthority(user.getRoles());

        System.out.println("Kullanıcı: " + user.getUserName() + " Rolleri: " + authorities); // 🔍 Debug için

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), authorities);
    }

    private List<SimpleGrantedAuthority> buildGrantedAuthority(Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            //authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getType().name()));
            authorities.add(new SimpleGrantedAuthority(role.getType().name()));
        }
        return authorities;
    }


}

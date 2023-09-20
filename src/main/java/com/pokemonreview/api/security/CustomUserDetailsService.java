package com.pokemonreview.api.security;

import com.pokemonreview.api.data.model.Role;
import com.pokemonreview.api.data.model.UserEntity;
import com.pokemonreview.api.data.repository.UserQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserQRepository userQRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userQRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user nt found"));
        return new User(user.getUsername(), user.getPassword(), mapToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapToAuthorities(List<Role> roles){
        return  roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}

package com.pokemonreview.api.security;

import com.pokemonreview.api.data.model.UserEntity;
import com.pokemonreview.api.data.repository.UserQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserQRepository userQRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userQRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user nt found"));
        return null;
    }
}

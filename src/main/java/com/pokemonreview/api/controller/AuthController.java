package com.pokemonreview.api.controller;

import com.pokemonreview.api.data.model.Role;
import com.pokemonreview.api.data.model.UserEntity;
import com.pokemonreview.api.data.repository.RoleRepository;
import com.pokemonreview.api.data.repository.UserQRepository;
import com.pokemonreview.api.dto.request.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
@Service
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserQRepository userQRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        if (userQRepository.existsByUsername(registerRequest.getUsername())){
            return new ResponseEntity<>("user nae is  taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));
        userQRepository.save(user);
        return new ResponseEntity<>("user register successfully",HttpStatus.CREATED);
    }
    @PostMapping("/create/role")
    public String createRole(String name){
        Role role = new  Role();
        role.setName(name);
        roleRepository.save(role);
        return "role register successfully";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("sign in completed successfully",HttpStatus.ACCEPTED);
    }

}

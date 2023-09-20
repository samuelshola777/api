package com.pokemonreview.api.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthControllerTest {
@Autowired
    private  AuthController controller;


    @Test
    void createRole() {
      assertEquals("role register successfully",  controller.createRole("ADMIN"));
    }


}
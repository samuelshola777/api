package com.pokemonreview.api.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String start;
    
@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="pokemon_id")
    private Pokemon pokemon;
}

package com.pokemonreview.api.data.repository;

import com.pokemonreview.api.data.model.UserEntity;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUsername(String userName);
    boolean existsByUsername(String userName);
}

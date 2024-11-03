package com.upskillers.upskillers.repository;

import com.upskillers.upskillers.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Classe findByName(String name);
}

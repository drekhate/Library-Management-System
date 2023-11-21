package com.acciojob.library.management.system.repositorys;

import com.acciojob.library.management.system.entitys.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<LibraryCard, Integer> {
}

package com.acciojob.library.management.system.repositorys;

import com.acciojob.library.management.system.entitys.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}

package com.acciojob.library.management.system.repositorys;

import com.acciojob.library.management.system.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}

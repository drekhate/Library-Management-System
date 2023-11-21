package com.acciojob.library.management.system.repositorys;

import com.acciojob.library.management.system.entitys.Book;
import com.acciojob.library.management.system.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksListByGenre(Genre genre);
}

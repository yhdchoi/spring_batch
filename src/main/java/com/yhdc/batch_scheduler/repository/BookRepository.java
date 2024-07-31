package com.yhdc.batch_scheduler.repository;

import com.yhdc.batch_scheduler.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

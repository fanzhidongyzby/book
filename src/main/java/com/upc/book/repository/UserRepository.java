package com.upc.book.repository;

import com.upc.book.entity.User;
import com.upc.book.exception.BookException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

  User findByUserName(String userName) throws BookException;
}
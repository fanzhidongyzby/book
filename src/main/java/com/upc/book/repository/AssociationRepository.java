package com.upc.book.repository;

import com.upc.book.entity.Association;
import com.upc.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AssociationRepository extends JpaRepository<Association, String>, JpaSpecificationExecutor<Association> {

  // select y from association where x = ? order by percent desc limit 3
  @Query("select asso.y from Association asso where asso.x = :x order by asso.percent desc")
  public List<String> getYFromX(@Param("x")String x);
}
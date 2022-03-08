package com.Dennis.Boxinator.Repository;

import com.Dennis.Boxinator.Model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//From the way I understood the task I had to set up and
//create my own sql-connection
//and execute the queries
@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

    @Query(value = "SELECT * FROM box", nativeQuery = true)
    List<Box> listboxes();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO box(container_colour, country, box_name,shipping_cost, weight_in_kilo_grams ) VALUES(?, ?, ?, ?, ?)",
            nativeQuery = true)
    void add(@Param("container_colour") String container_colour
            , @Param("country") String country
            , @Param("box_name") String box_name
            , @Param("shipping_cost") double shipping_cost
            , @Param("weight_in_kilo_grams") double weight_in_kilo_grams);
}

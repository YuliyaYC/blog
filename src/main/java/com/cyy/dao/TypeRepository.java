package com.cyy.dao;

import com.cyy.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);
}

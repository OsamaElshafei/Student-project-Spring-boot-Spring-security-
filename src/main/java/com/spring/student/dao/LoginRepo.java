package com.spring.student.dao;


import com.spring.student.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepo extends JpaRepository<Login,Long> {
    public List<Login> findByEmail(String name);

}

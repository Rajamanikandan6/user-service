package com.maveric.userservice.repository;

import com.maveric.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT * FROM user ORDER BY id Offset ?1 Limit ?2")
    List<User> findAllUserWithPagination(int page,int page_size);
}

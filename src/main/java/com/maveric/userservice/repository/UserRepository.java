package com.maveric.userservice.repository;

import com.maveric.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String emailId);

//    @Query("select * from user where id != :user_id and email=:email_id;")
//    Optional<User> findByEmailExcludingId(@Param("user_id") String userId,@Param("email_id") String emailId);
}

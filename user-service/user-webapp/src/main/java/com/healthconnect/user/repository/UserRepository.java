package com.healthconnect.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.UserType;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Nullable
    User findByEmailAndUserType(String email, UserType serviceType);
    
    @Nullable
    User findByMobileNumberAndUserType(String phone, UserType userType);
    
    @Nullable
    User findByUserId(String userId);
    
    @Nullable
    Optional<User> findById(Long id);

}


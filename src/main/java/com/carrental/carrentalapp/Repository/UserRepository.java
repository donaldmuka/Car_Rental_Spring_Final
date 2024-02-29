package com.carrental.carrentalapp.Repository;

import com.carrental.carrentalapp.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {

    AppUser findUserByEmail(String email);

    AppUser findByUsername(String username);

}


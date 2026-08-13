package com.kodong.ounwan.user.repository;

import com.kodong.ounwan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}

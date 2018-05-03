package com.biz.lesson.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.user.User;


@Repository
public interface UserRepository extends CommonJpaRepository<User, String>, UserDao{

    User findByUserIdAndPasswordAndStatus(String userName, String password, Boolean status);

    List<User> findByStatus(boolean enabled);

    @Modifying
    @Query("UPDATE User u SET u.status = false WHERE u.userId = :userId")
    void disableUser(@Param("userId") String userId);

    List<User> findByUserIdIn(Iterable<String> ids);

    List<User> findByStatusAndUserTypeOrderByNameAsc(boolean b, String utBroker);
}

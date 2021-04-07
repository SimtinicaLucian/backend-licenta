package com.apsoft.conta.role.repository;

import com.apsoft.conta.role.model.ERole;
import com.apsoft.conta.role.persistence.Role;
import com.apsoft.conta.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    Optional<Role> findAllById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_roles u WHERE u.user_id = ?1 AND u.role_id = ?2", nativeQuery=true)
    void deleteRoleBytrews(long user_id, long role_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_roles VALUES (:user_id, :role_id)", nativeQuery=true)
    void addRoleToUser(@Param("user_id")long user_id,@Param("role_id") long role_id);

    @Transactional
    @Query(value = "SELECT username, name\n" +
            "FROM users u JOIN  user_roles ur \n" +
            "ON ur.user_id = u.id JOIN roles r ON ur.role_id = r.id", nativeQuery=true)
    List<Collection> showAllRole();


    @Modifying
    @Transactional
    @Query(value = "UPDATE user_roles SET role_id =?  where user_id = ?" , nativeQuery=true)
    void updateRoleToUser(@Param("role_id")long role_id,@Param("user_id") long user_id);

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO cheltuieli VALUES (:detalii)", nativeQuery=true)
//    void addPlati(@Param("user_id")long user_id,@Param("role_id") long role_id);






}

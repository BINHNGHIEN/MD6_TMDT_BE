package com.example.md6_final_project_be.repository;

import com.example.md6_final_project_be.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String name); //Tim kiem User co ton tai trong DB khong?
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua

    @Query(value = "SELECT * FROM app_user a JOIN user_role u ON id = user_id WHERE role_id = 1", nativeQuery = true)
    Iterable<AppUser> findAllCustomer();
}

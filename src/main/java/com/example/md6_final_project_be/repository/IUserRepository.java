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

    @Query(value = "SELECT * FROM app_user JOIN user_role ON id = user_id WHERE role_id = 1 AND phonenb LIKE ?1", nativeQuery = true)
    Iterable<AppUser> findCustomerByPhoneNB(String phoneNB);

    @Query(value = "SELECT * FROM app_user JOIN user_role ON id = user_id WHERE role_id = 1 ORDER BY name", nativeQuery = true)
    Iterable<AppUser> findAllCustomerOrderByName();

    @Query(value = "SELECT * FROM app_user JOIN user_role ON id = user_id WHERE role_id = 1 ORDER BY name DESC", nativeQuery = true)
    Iterable<AppUser> findAllCustomerOrderByNameDesc();

    @Query(value = "SELECT * FROM app_user JOIN user_role ON id = user_id WHERE role_id = 1 ORDER BY create_date", nativeQuery = true)
    Iterable<AppUser> findAllCustomerOrderByCreateDate();

    @Query(value = "SELECT * FROM app_user JOIN user_role ON id = user_id WHERE role_id = 1 ORDER BY create_date DESC", nativeQuery = true)
    Iterable<AppUser> findAllCustomerOrderByCreateDateDesc();

}


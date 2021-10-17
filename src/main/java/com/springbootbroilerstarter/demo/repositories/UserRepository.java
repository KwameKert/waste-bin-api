package com.springbootbroilerstarter.demo.repositories;

import com.springbootbroilerstarter.demo.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

    @Query(value = "SELECT coalesce(SUM(amount),0) as amount FROM app_invoices WHERE status =0; ", nativeQuery = true)
    float sumPendingTransactions();

    @Query(value = "SELECT coalesce(SUM(amount),0) as amount FROM app_invoices WHERE status =1; ", nativeQuery = true)
    float sumCompletedTransactions();

}

package com.example.First_website.Repository;

import com.example.First_website.DB_Entity.OrderEntity;
import com.example.First_website.DB_Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
    // Find all orders by user
    List<OrderEntity> findByUser(UserEntity user);
    
    // Find all orders by user ID
    List<OrderEntity> findByUserId(Long userId);
    
    // Find all orders with a specific status
    List<OrderEntity> findByStatus(String status);
    
    // Find all orders by user and status
    List<OrderEntity> findByUserAndStatus(UserEntity user, String status);
    
    // Find all orders with a specific status for a user ID
    @Query("SELECT o FROM OrderEntity o WHERE o.user.id = :userId AND o.status = :status")
    List<OrderEntity> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status);
    
    // Check if an order exists for a user with a specific status
    boolean existsByUserAndStatus(UserEntity user, String status);
    
    // Count orders by status
    long countByStatus(String status);
    
    // Find all orders sorted by creation date (newest first)
    List<OrderEntity> findAllByOrderByCreatedAtDesc();
}

package com.unbosque.edu.co.forest.model.entity;

import jakarta.persistence.*;

import com.unbosque.edu.co.forest.model.enums.AccountStatus;
import com.unbosque.edu.co.forest.model.enums.Role;
import com.unbosque.edu.co.forest.model.enums.OrderType;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_user", nullable = false)
    private Role role;

    @Column(name = "phone", length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;


    // Role-specific attributes
    @Column(name = "commission_rate", precision = 5, scale = 2)
    private Double commissionRate;

    @Column(name = "has_subscription")
    private Boolean hasSubscription = false;

    @Column(name = "alpaca_status", length = 30)
    private String alpacaStatus;

    @Column(name = "alpaca_account_id", length = 100)
    private String alpacaAccountId;

    @Column(name = "daily_order_limit")
    private Integer dailyOrderLimit = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "default_order_type")
    private OrderType defaultOrderType;

    // Constructors
    public User() {
    }

    public User(Integer id, String name, String email, String passwordHash, Role role, String phone, LocalDateTime createdAt, AccountStatus accountStatus, LocalDateTime lastAccess, Double commissionRate, Boolean hasSubscription, String alpacaStatus, String alpacaAccountId, Integer dailyOrderLimit, OrderType defaultOrderType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.phone = phone;
        this.createdAt = createdAt;
        this.accountStatus = accountStatus;
        this.lastAccess = lastAccess;
        this.commissionRate = commissionRate;
        this.hasSubscription = hasSubscription;
        this.alpacaStatus = alpacaStatus;
        this.alpacaAccountId = alpacaAccountId;
        this.dailyOrderLimit = dailyOrderLimit;
        this.defaultOrderType = defaultOrderType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Boolean getHasSubscription() {
        return hasSubscription;
    }

    public void setHasSubscription(Boolean hasSubscription) {
        this.hasSubscription = hasSubscription;
    }

    public String getAlpacaStatus() {
        return alpacaStatus;
    }

    public void setAlpacaStatus(String alpacaStatus) {
        this.alpacaStatus = alpacaStatus;
    }

    public String getAlpacaAccountId() {
        return alpacaAccountId;
    }

    public void setAlpacaAccountId(String alpacaAccountId) {
        this.alpacaAccountId = alpacaAccountId;
    }

    public Integer getDailyOrderLimit() {
        return dailyOrderLimit;
    }

    public void setDailyOrderLimit(Integer dailyOrderLimit) {
        this.dailyOrderLimit = dailyOrderLimit;
    }

    public OrderType getDefaultOrderType() {
        return defaultOrderType;
    }

    public void setDefaultOrderType(OrderType defaultOrderType) {
        this.defaultOrderType = defaultOrderType;
    }
}
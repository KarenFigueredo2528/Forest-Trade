package com.unbosque.edu.co.forest.model.entity;

import jakarta.persistence.*;

import com.unbosque.edu.co.forest.model.enums.AccountStatus;
import com.unbosque.edu.co.forest.model.enums.Role;
import com.unbosque.edu.co.forest.model.enums.OrderType;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AccountStatus accountStatus;

    // Role-specific attributes
    private Double commissionPercentage;

    private Boolean hasSubscription = false;

    private String alpacaStatus;

    private String alpacaAccountId;

    private Integer dailyOperationLimit = 0;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private OrderType predefinedOrderType;

    // Constructors
    public User() {}

    public User(Long userId, String name, String email, String passwordHash, Role role, String phoneNumber, Double commissionPercentage, Boolean hasSubscription, String alpacaStatus, String alpacaAccountId, Integer dailyOperationLimit, OrderType predefinedOrderType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.commissionPercentage = commissionPercentage;
        this.hasSubscription = hasSubscription;
        this.alpacaStatus = alpacaStatus;
        this.alpacaAccountId = alpacaAccountId;
        this.dailyOperationLimit = dailyOperationLimit;
        this.predefinedOrderType = predefinedOrderType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
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

    public Integer getDailyOperationLimit() {
        return dailyOperationLimit;
    }

    public void setDailyOperationLimit(Integer dailyOperationLimit) {
        this.dailyOperationLimit = dailyOperationLimit;
    }

    public OrderType getPredefinedOrderType() {
        return predefinedOrderType;
    }

    public void setPredefinedOrderType(OrderType predefinedOrderType) {
        this.predefinedOrderType = predefinedOrderType;
    }
}

package com.unbosque.edu.co.forest.model.dto;

import com.unbosque.edu.co.forest.model.enums.AccountStatus;
import com.unbosque.edu.co.forest.model.enums.OrderType;
import com.unbosque.edu.co.forest.model.enums.Role;

import java.time.LocalDateTime;

public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private Role role;
    private String phone;
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccess;
    private Double commissionRate;
    private Boolean hasSubscription;
    private String alpacaStatus;
    private String alpacaAccountId;
    private Integer dailyOrderLimit;
    private OrderType defaultOrderType;
    private Float balance;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email, Role role, String phone,
                   AccountStatus accountStatus, LocalDateTime createdAt, LocalDateTime lastAccess,
                   Double commissionRate, Boolean hasSubscription, String alpacaStatus,
                   String alpacaAccountId, Integer dailyOrderLimit, OrderType defaultOrderType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.lastAccess = lastAccess;
        this.commissionRate = commissionRate;
        this.hasSubscription = hasSubscription;
        this.alpacaStatus = alpacaStatus;
        this.alpacaAccountId = alpacaAccountId;
        this.dailyOrderLimit = dailyOrderLimit;
        this.defaultOrderType = defaultOrderType;
    }

    // Getters and setters...


    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

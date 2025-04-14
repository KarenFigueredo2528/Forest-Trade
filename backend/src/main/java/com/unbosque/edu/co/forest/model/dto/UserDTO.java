package com.unbosque.edu.co.forest.model.dto;

import com.unbosque.edu.co.forest.model.enums.AccountStatus;
import com.unbosque.edu.co.forest.model.enums.Role;
import com.unbosque.edu.co.forest.model.enums.OrderType;

import java.time.LocalDateTime;

public class UserDTO {

    private Long userId;
    private String name;
    private String email;
    private Role role;
    private String phoneNumber;
    private AccountStatus accountStatus;
    private LocalDateTime creationDate;
    private LocalDateTime lastLogin;

    // Role-specific fields
    private Double commissionPercentage;
    private Boolean hasSubscription;
    private String alpacaStatus;
    private String alpacaAccountId;
    private Integer dailyOperationLimit;
    private OrderType predefinedOrderType;

    // Constructor vacío
    public UserDTO() {}

    // Getters y Setters

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

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
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

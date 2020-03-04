package com.persist.jpa.entity;


public class UserAccess {

    private String username;
    private String accountId;
    private String permissions;

    public UserAccess(String username, String accountId, String permissions) {
        this.username = username;
        this.accountId = accountId;
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

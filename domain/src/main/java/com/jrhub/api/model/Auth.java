package com.jrhub.api.model;

import java.util.Objects;
import java.util.Set;

public class Auth {
    private String username;
    private String password;
    private Set<String> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Auth() {
    }

    public Auth(String username, String password, Set<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return Objects.equals(getUsername(), auth.getUsername()) && Objects.equals(getPassword(), auth.getPassword()) && Objects.equals(getAuthorities(), auth.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getAuthorities());
    }

    @Override
    public String toString() {
        return "Auth{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}

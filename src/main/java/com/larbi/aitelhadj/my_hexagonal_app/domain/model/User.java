package com.larbi.aitelhadj.my_hexagonal_app.domain.model;

import java.util.regex.Pattern;

/**
 * @larbi.aitelhadj
 *
 */
public class User {

    private Long id;
    private String name;
    private String email;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void updateEmail(String newEmail) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        } else if (!pattern.matcher(newEmail).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = newEmail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}

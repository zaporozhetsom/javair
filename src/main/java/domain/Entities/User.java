package domain.Entities;

import domain.Entity;
import domain.util.UserRole;

/**
 * Created by zom on 14.09.2017.
 */
public class User implements Entity {
    private Integer id;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String login;
    private String password;

    public static class Builder {
        private Integer id;
        private String firstName;
        private String lastName;
        private UserRole role;
        private String login;
        private String password;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build () {
            return new User(this);
        }
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.role = builder.role;
        this.login = builder.login;
        this.password = builder.password;

    }

    @Override
    public Integer getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public UserRole getRole() {
        return role;
    }


    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

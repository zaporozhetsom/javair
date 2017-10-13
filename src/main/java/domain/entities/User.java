package domain.entities;

import domain.Entity;
import domain.util.UserRole;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zom on 14.09.2017.
 */
public class User implements Entity {
    private Long id;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String login;
    private String password;
    private boolean approved;
    private static final String SALT = "ClouD7";

    public static class Builder {

        private Long id;
        private String firstName;
        private String lastName;
        private UserRole role;
        private String login;
        private String password;
        private boolean approved;
        private static final Logger LOGGER = Logger.getLogger(Builder.class.getClass());

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            LOGGER.debug("id = " + id);
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            LOGGER.debug("fname = " + firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            LOGGER.debug("lname = " + lastName);
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            LOGGER.debug("role = " + role);
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            LOGGER.debug("login = " + login);
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            LOGGER.debug("password = " + password);
            return this;
        }

        public Builder approved(boolean approved) {
            this.approved = approved;
            LOGGER.debug("password = " + approved);
            return this;
        }

        public User build() {
            LOGGER.debug("build User");
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
        this.approved = builder.approved;

    }

    @Override
    public Long getId() {
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


    public boolean isApproved() {
        return approved;
    }

    @Override
    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
//        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + login.hashCode();
        return result;
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
                ", approved='" + approved + '\'' +
                '}';
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        String st = password + SALT;
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(st.getBytes(StandardCharsets.UTF_8));
            digest = messageDigest.digest();
        } finally {
            //empty
        }
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

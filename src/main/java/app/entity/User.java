package app.entity;

import app.web.service.interfacas.Observer;
import app.enums.UserLanguage;
import app.enums.UserRole;
import app.enums.UserStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class that represents User entity in system.
 */
public class User implements Serializable, Observer {

    private int id;
    private String login;
    private String password;
    private String email;
    private String message;
    private BigDecimal account;
    private UserStatus status;
    private UserRole userRole;
    private UserLanguage language;


    /**
     * Constructor used to create absolute new system User without parameters
     */
    public User() {
        this.message = null;
        this.account = new BigDecimal(0);
        this.status = UserStatus.ACTIVE;
        this.userRole = UserRole.READER;
        this.language = UserLanguage.UKR;
    }

    /**
     * Another constructor used to create User received from database.
     *
     * @param id        user ID
     * @param login     user login
     * @param password  user password
     * @param email     user e-mail
     * @param message   user message
     * @param account   user account
     * @param status    {@see app.enums.UserStatus }
     * @param userRole  {@see app.enums.UserRole}
     * @param language  {@see app.enums.UserLanguage}
     */
    public User(int id, String login, String password, String email, String message, BigDecimal account, UserStatus status, UserRole userRole, UserLanguage language) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.message = message;
        this.account = account;
        this.status = status;
        this.userRole = userRole;
        this.language = language;
    }

    public int getId() {return id;}

    public void setId(int id) { this.id = id;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public BigDecimal getAccount() {return account;}

    public void setAccount(BigDecimal account) {this.account = account;}

    public UserStatus getStatus() {return status;}

    public void setStatus(UserStatus status) {this.status = status;}

    public boolean isBlocked() {
        return status == UserStatus.BLOCKED;
    }

    public void block() {
        this.setStatus(UserStatus.BLOCKED);
    }

    public void unblock() {
        this.setStatus(UserStatus.ACTIVE);
    }

    public UserRole getUserRole() {return userRole;}

    public void setUserRole(UserRole userRole) {this.userRole = userRole;}

    public UserLanguage getLanguage() {return language;}

    public void setLanguage(UserLanguage language) {this.language = language;}

    /**
     * The method is used to add sum to user account
     *
     * @param sum sum that being added to account
     */
    public void replenish(BigDecimal sum) {
        account = account.add(sum);
    }

    /**
     * The method is used to withdraw sum from account
     *
     * @param withdrawSum the amount to be deducted from the account
     */
    public void withdraw(BigDecimal withdrawSum) {
        account = account.subtract(withdrawSum);
    }

    @Override
    public void update(Event event) {
        String massage = (language == UserLanguage.UKR)?event.getAlertUkr():event.getAlertEng();
        setMessage(massage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(message, user.message) && Objects.equals(account, user.account) && status == user.status && userRole == user.userRole && language == user.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, message, account, status, userRole, language);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", account=" + account +
                ", status=" + status +
                ", userRole=" + userRole +
                ", language=" + language +
                '}';
    }
}

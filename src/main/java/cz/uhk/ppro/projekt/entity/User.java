package cz.uhk.ppro.projekt.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Entity
@Table(name = "users", schema = "cukrarna", catalog = "")
public class User {
    private int userId;
    private String username;
    private String password;
    private byte administrator;
    private Timestamp registrationTimestamp;

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 50)
    @Pattern(regexp = "[a-zA-Z0-9]{4,}")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 255)
    @Pattern(regexp = "\\$33\\$16\\$.{43}")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "ADMINISTRATOR", nullable = false)
    public byte getAdministrator() {
        return administrator;
    }

    public void setAdministrator(byte administrator) {
        this.administrator = administrator;
    }

    @Basic
    @Column(name = "REGISTRATION_TIMESTAMP", insertable = false, updatable = false, nullable = false)
    public Timestamp getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp(Timestamp registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (administrator != user.administrator) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (registrationTimestamp != null ? !registrationTimestamp.equals(user.registrationTimestamp) : user.registrationTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) administrator;
        result = 31 * result + (registrationTimestamp != null ? registrationTimestamp.hashCode() : 0);
        return result;
    }
}
package www.OliveOnline.com.OliveShop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET is_deleted = true,is_active=false WHERE user_id = ?")
@Where(clause = "is_deleted = false")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userID ;
    @NotEmpty
    @Column(name = "first_name",nullable = false)
    private String firstName ;
    @Column(name = "last_name")
    private String lastName ;
    @NotEmpty
    @Column(name = "email",nullable = false,unique = true)
    private String email ;
    @NotEmpty
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber ;
    @NotEmpty
    @Column(name = "username",nullable = false,unique = true)
    private String userName ;
    @NotEmpty
    @Column(name = "passwords",length = 255)
    private String password ;
    @Column(name = "created_at" , nullable = false,updatable = false )
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(name = "last_modified",nullable = false)
    @UpdateTimestamp
    private Timestamp lastModified;
    @Column(name = "is_active",nullable = false)
    private Boolean isActive =true;
    @Column(name = "last_loggedin")
    private Date lastLoggedin;
    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted =false;
    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Roles role;
    public  Users(   ){

    }
    public Users(Users users){

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getLastLoggedin() {
        return lastLoggedin;
    }

    public void setLastLoggedin(Date lastLoggedin) {
        this.lastLoggedin = lastLoggedin;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

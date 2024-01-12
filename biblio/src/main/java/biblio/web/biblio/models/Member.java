package biblio.web.biblio.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private String tel;
    private String address;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "email_verified_at")
    private Date emailVerifiedAt;

    @Column(name = "remember_token")
    private String rememberToken;

    // Getters and setters

    public Member() {
    }

    public Member(Long memberId, Role role, String firstName, String lastName, String email, String tel, String address, String password, Date emailVerifiedAt, String rememberToken) {
        this.memberId = memberId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.password = password;
        this.emailVerifiedAt = emailVerifiedAt;
        this.rememberToken = rememberToken;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEmailVerifiedAt() {
        return this.emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Date emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getRememberToken() {
        return this.rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public Member memberId(Long memberId) {
        setMemberId(memberId);
        return this;
    }

    public Member role(Role role) {
        setRole(role);
        return this;
    }

    public Member firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public Member lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Member email(String email) {
        setEmail(email);
        return this;
    }

    public Member tel(String tel) {
        setTel(tel);
        return this;
    }

    public Member address(String address) {
        setAddress(address);
        return this;
    }

    public Member password(String password) {
        setPassword(password);
        return this;
    }

    public Member emailVerifiedAt(Date emailVerifiedAt) {
        setEmailVerifiedAt(emailVerifiedAt);
        return this;
    }

    public Member rememberToken(String rememberToken) {
        setRememberToken(rememberToken);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Member)) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId) && Objects.equals(role, member.role) && Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName) && Objects.equals(email, member.email) && Objects.equals(tel, member.tel) && Objects.equals(address, member.address) && Objects.equals(password, member.password) && Objects.equals(emailVerifiedAt, member.emailVerifiedAt) && Objects.equals(rememberToken, member.rememberToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, role, firstName, lastName, email, tel, address, password, emailVerifiedAt, rememberToken);
    }

    @Override
    public String toString() {
        return "{" +
            " memberId='" + getMemberId() + "'" +
            ", role='" + getRole() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", address='" + getAddress() + "'" +
            ", password='" + getPassword() + "'" +
            ", emailVerifiedAt='" + getEmailVerifiedAt() + "'" +
            ", rememberToken='" + getRememberToken() + "'" +
            "}";
    }
    
}

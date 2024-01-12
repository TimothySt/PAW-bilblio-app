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
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Temporal(TemporalType.DATE)
    @Column(name = "reservation_date")
    private Date reservationDate;

    private boolean active;

    // Getters and setters

    public Reservation() {
    }

    public Reservation(Long reservationId, Book book, Member member, Date reservationDate, boolean active) {
        this.reservationId = reservationId;
        this.book = book;
        this.member = member;
        this.reservationDate = reservationDate;
        this.active = active;
    }

    public Long getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Reservation reservationId(Long reservationId) {
        setReservationId(reservationId);
        return this;
    }

    public Reservation book(Book book) {
        setBook(book);
        return this;
    }

    public Reservation member(Member member) {
        setMember(member);
        return this;
    }

    public Reservation reservationDate(Date reservationDate) {
        setReservationDate(reservationDate);
        return this;
    }

    public Reservation active(boolean active) {
        setActive(active);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reservation)) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return Objects.equals(reservationId, reservation.reservationId) && Objects.equals(book, reservation.book) && Objects.equals(member, reservation.member) && Objects.equals(reservationDate, reservation.reservationDate) && active == reservation.active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, book, member, reservationDate, active);
    }

    @Override
    public String toString() {
        return "{" +
            " reservationId='" + getReservationId() + "'" +
            ", book='" + getBook() + "'" +
            ", member='" + getMember() + "'" +
            ", reservationDate='" + getReservationDate() + "'" +
            ", active='" + isActive() + "'" +
            "}";
    }
    
}

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
@Table(name = "Loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Temporal(TemporalType.DATE)
    @Column(name = "loan_date")
    private Date loanDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "loan_status_id")
    private LoanStatus loanStatus;

    // Getters and setters

    public Loan() {
    }

    public Loan(Long loanId, Copy copy, Member member, Date loanDate, Date dueDate, LoanStatus loanStatus) {
        this.loanId = loanId;
        this.copy = copy;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.loanStatus = loanStatus;
    }

    public Long getLoanId() {
        return this.loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Copy getCopy() {
        return this.copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getLoanDate() {
        return this.loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public LoanStatus getLoanStatus() {
        return this.loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Loan loanId(Long loanId) {
        setLoanId(loanId);
        return this;
    }

    public Loan copy(Copy copy) {
        setCopy(copy);
        return this;
    }

    public Loan member(Member member) {
        setMember(member);
        return this;
    }

    public Loan loanDate(Date loanDate) {
        setLoanDate(loanDate);
        return this;
    }

    public Loan dueDate(Date dueDate) {
        setDueDate(dueDate);
        return this;
    }

    public Loan loanStatus(LoanStatus loanStatus) {
        setLoanStatus(loanStatus);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Loan)) {
            return false;
        }
        Loan loan = (Loan) o;
        return Objects.equals(loanId, loan.loanId) && Objects.equals(copy, loan.copy) && Objects.equals(member, loan.member) && Objects.equals(loanDate, loan.loanDate) && Objects.equals(dueDate, loan.dueDate) && Objects.equals(loanStatus, loan.loanStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, copy, member, loanDate, dueDate, loanStatus);
    }

    @Override
    public String toString() {
        return "{" +
            " loanId='" + getLoanId() + "'" +
            ", copy='" + getCopy() + "'" +
            ", member='" + getMember() + "'" +
            ", loanDate='" + getLoanDate() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", loanStatus='" + getLoanStatus() + "'" +
            "}";
    }
    
}

package biblio.web.biblio.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "LoansStatus")
public class LoanStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_status_id")
    private Long loanStatusId;

    private String status;

    // Getters and setters

    public LoanStatus() {
    }

    public LoanStatus(Long loanStatusId, String status) {
        this.loanStatusId = loanStatusId;
        this.status = status;
    }

    public Long getLoanStatusId() {
        return this.loanStatusId;
    }

    public void setLoanStatusId(Long loanStatusId) {
        this.loanStatusId = loanStatusId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoanStatus loanStatusId(Long loanStatusId) {
        setLoanStatusId(loanStatusId);
        return this;
    }

    public LoanStatus status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LoanStatus)) {
            return false;
        }
        LoanStatus loanStatus = (LoanStatus) o;
        return Objects.equals(loanStatusId, loanStatus.loanStatusId) && Objects.equals(status, loanStatus.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanStatusId, status);
    }

    @Override
    public String toString() {
        return "{" +
            " loanStatusId='" + getLoanStatusId() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
    
}

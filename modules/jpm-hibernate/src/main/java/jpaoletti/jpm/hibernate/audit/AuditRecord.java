package jpaoletti.jpm.hibernate.audit;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author jpaoletti
 */
@Entity
@Table(name = "audit_records")
public class AuditRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(nullable = true)
    private String username;
    @Column(nullable = true)
    private String entity;
    @Column(nullable = true)
    private String operation;
    @Column(nullable = true)
    private String item;
    @Column(nullable = true)
    private String observations;

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

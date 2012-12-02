package com.lsy.vehicle.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NamedQueries({
	@NamedQuery(name="ApplicationLog.LOAD_ALL"
			,   query="SELECT log FROM ApplicationLog log")
})
public class ApplicationLog {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	private String message;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public ApplicationLog() {}

	public ApplicationLog(String message) {
		this.message = message;
	}

	@PrePersist
	public void prePersist() {
		timestamp = new Date();
	}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}

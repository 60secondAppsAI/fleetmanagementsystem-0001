package com.fleetmanagementsystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="verifiers")
@Getter @Setter @NoArgsConstructor
public class Verifier {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="verifier_id")
	private Integer verifierId;
    
  	@Column(name="task_id")
	private Task taskId;
    
  	@Column(name="verifier_user_id")
	private User verifierUserId;
    
  	@Column(name="verification_date")
	private Date verificationDate;
    

}

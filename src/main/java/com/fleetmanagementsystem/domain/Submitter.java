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
@Table(name="submitters")
@Getter @Setter @NoArgsConstructor
public class Submitter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="submitter_id")
	private Integer submitterId;
    
  	@Column(name="task_id")
	private Task taskId;
    
  	@Column(name="user_id")
	private User userId;
    
  	@Column(name="submission_date")
	private Date submissionDate;
    

}

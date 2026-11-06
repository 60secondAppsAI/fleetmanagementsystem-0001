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
@Table(name="feedbacks")
@Getter @Setter @NoArgsConstructor
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="feedback_id")
	private Integer feedbackId;
    
  	@Column(name="task_id")
	private Task taskId;
    
  	@Column(name="reviewer_id")
	private User reviewerId;
    
  	@Column(name="review_content")
	private String reviewContent;
    
  	@Column(name="feedback_date")
	private Date feedbackDate;
    

}

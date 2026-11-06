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
@Table(name="journeys")
@Getter @Setter @NoArgsConstructor
public class Journey {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="journey_id")
	private Integer journeyId;
    
  	@Column(name="start_point")
	private String startPoint;
    
  	@Column(name="end_point")
	private String endPoint;
    
  	@Column(name="distance_traveled")
	private double distanceTraveled;
    
  	@Column(name="fare")
	private double fare;
    

}

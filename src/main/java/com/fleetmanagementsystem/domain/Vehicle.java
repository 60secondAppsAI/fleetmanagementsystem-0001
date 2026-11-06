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
@Table(name="vehicles")
@Getter @Setter @NoArgsConstructor
public class Vehicle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="vehicle_id")
	private Integer vehicleId;
    
  	@Column(name="registration_number")
	private String registrationNumber;
    
  	@Column(name="model_type")
	private String modelType;
    
  	@Column(name="capacity")
	private int capacity;
    

}

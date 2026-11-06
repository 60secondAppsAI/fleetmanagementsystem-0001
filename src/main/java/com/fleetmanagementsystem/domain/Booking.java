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
@Table(name="bookings")
@Getter @Setter @NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="booking_id")
	private Integer bookingId;
    
  	@Column(name="number_of_passengers")
	private int numberOfPassengers;
    
  	@Column(name="contact_person_name")
	private String contactPersonName;
    
  	@Column(name="contact_person_email")
	private String contactPersonEmail;
    

}

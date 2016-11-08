package com.san;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@SpringBootApplication
public class ReservationServiceApplication {
	
	@Bean
	CommandLineRunner runner(ReservationRepository rr){
		return args -> {
			
			Arrays.asList("MyName1,MyName2,MyName3,MyName4,MyName5".split(","))
			.forEach(x->rr.save(new Reservation(x)));
			rr.findAll().forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
}


@RepositoryRestResource 
interface ReservationRepository extends JpaRepository<Reservation, Long>{
	@RestResource (path="by-name")
	Collection<Reservation> findByReservationName (@Param("rn") String rn);
}


@Entity
class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + ", getId()=" + getId()
				+ ", getReservationName()=" + getReservationName() + "]";
	}
	public Reservation() {
		
	}
	public Reservation(String reservationName) {
		super();
		this.reservationName = reservationName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
}
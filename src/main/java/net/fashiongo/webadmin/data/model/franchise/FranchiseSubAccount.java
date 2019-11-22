package net.fashiongo.webadmin.data.model.franchise;

import lombok.Getter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class FranchiseSubAccount {

	private String city;
	private String companyName;
	private String country;
	private String email;
	private String firstName;
	private String lastName;
	private Integer retailerId;
	private LocalDateTime startingDate;
	private String state;

	public FranchiseSubAccount(String city, String companyName, String country, String email, String firstName, String lastName, Integer retailerId, Timestamp startingDate, String state) {
		this.city = city;
		this.companyName = companyName;
		this.country = country;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.retailerId = retailerId;
		this.startingDate = Optional.ofNullable(startingDate).map(Timestamp::toLocalDateTime).orElse(null);
		this.state = state;
	}
}

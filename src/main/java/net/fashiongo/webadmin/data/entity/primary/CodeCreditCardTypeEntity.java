package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "Code_CreditCardType")
public class CodeCreditCardTypeEntity {

	@Id
	@Column(name = "CreditCardTypeID")
	private Integer creditCardTypeID;

	@Column(name = "CreditCardType")
	private String creditCardType;
}

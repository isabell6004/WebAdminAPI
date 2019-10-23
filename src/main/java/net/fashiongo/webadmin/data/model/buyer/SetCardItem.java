package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Optional;

@Getter
public class SetCardItem {

	@JsonProperty("CartID")
	private Integer CartID;

	@JsonProperty("BQ1")
	private Integer BQ1;

	@JsonProperty("BQ2")
	private Integer BQ2;

	@JsonProperty("BQ3")
	private Integer BQ3;

	@JsonProperty("BQ4")
	private Integer BQ4;

	@JsonProperty("BQ5")
	private Integer BQ5;

	@JsonProperty("BQ6")
	private Integer BQ6;

	@JsonProperty("BQ7")
	private Integer BQ7;

	@JsonProperty("BQ8")
	private Integer BQ8;

	@JsonProperty("BQ9")
	private Integer BQ9;

	@JsonProperty("BQ10")
	private Integer BQ10;

	@JsonProperty("BQ11")
	private Integer BQ11;

	@JsonProperty("NoOfPack")
	private Integer NoOfPack;

	@JsonProperty("TotalQty")
	private Integer TotalQty;

	public Optional<Integer> getBQ1() {
		return Optional.ofNullable(BQ1);
	}

	public Optional<Integer> getBQ2() {
		return Optional.ofNullable(BQ2);
	}

	public Optional<Integer> getBQ3() {
		return Optional.ofNullable(BQ3);
	}

	public Optional<Integer> getBQ4() {
		return Optional.ofNullable(BQ4);
	}

	public Optional<Integer> getBQ5() {
		return Optional.ofNullable(BQ5);
	}

	public Optional<Integer> getBQ6() {
		return Optional.ofNullable(BQ6);
	}

	public Optional<Integer> getBQ7() {
		return Optional.ofNullable(BQ7);
	}

	public Optional<Integer> getBQ8() {
		return Optional.ofNullable(BQ8);
	}

	public Optional<Integer> getBQ9() {
		return Optional.ofNullable(BQ9);
	}

	public Optional<Integer> getBQ10() {
		return Optional.ofNullable(BQ10);
	}

	public Optional<Integer> getBQ11() {
		return Optional.ofNullable(BQ11);
	}

	public Optional<Integer> getNoOfPack() {
		return Optional.ofNullable(NoOfPack);
	}

	public Optional<Integer> getTotalQty() {
		return Optional.ofNullable(TotalQty);
	}
}

package com.parentclass.inventory.models;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "stocks")
@Table(name = "stocks")
@NoArgsConstructor
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "inventory_id")
	@JsonProperty("inventory_id")
	private long inventoryId;
	@Column(name = "amount")
	@JsonProperty("amount")
	private int amount;
}

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
@Entity(name = "inventory")
@Table(name = "inventory")
@NoArgsConstructor
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	@Column(name = "code")
	@JsonProperty("code")
	private String code;
	@Column(name = "name")
	@JsonProperty("name")
	private String name;
	@Column(name = "description")
	@JsonProperty("description")
	private String description;
}

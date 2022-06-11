package com.parentclass.inventory.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "stockhistories")
@Table(name = "stockhistories")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StockHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "inventory_id")
	@JsonProperty("inventory_id")
	private long inventoryId;	
	@Column(name = "stock_id")
	@JsonProperty("stock_id")
	private long stockId;	
	@Column(name = "old_amount")
	@JsonProperty("old_amount")
	private int oldAmount;
	@Column(name = "new_amount")
	@JsonProperty("new_amount")
	private int newAmount;
	@CreatedDate
	@Column(name = "created_at")
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
}

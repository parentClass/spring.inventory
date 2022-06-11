package com.parentclass.inventory.services;

import com.parentclass.inventory.models.Inventory;
import org.springframework.data.domain.Page;

public interface InventoryService {
	/**
	 * Persist an inventory record
	 * @param inventory - inventory object to be persisted
	 * @return Inventory
	 */
	public Inventory create(Inventory inventory);
	
	/**
	 * Inventory to modify its existing record
	 * @param id - id of the inventory
	 * @param inventory - inventory object to be used as the basis of the updated record
	 * @return Inventory
	 */
	public Inventory update(long id, Inventory inventory);
	
	/**
	 * Retrieve all inventory
	 * @param page - current index of pagination
	 * @param maxResult - max records per page to return
	 * @return list of persisted inventory
	 */
	public Page<Inventory> getAll(int page, int maxResult);
}

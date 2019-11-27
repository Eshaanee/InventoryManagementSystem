package com.sidgs.inventorysystem.dao;

import com.sidgs.inventorysystem.model.Inventory;
import com.sidgs.inventorysystem.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class InventoryDaoImplTest {

    @InjectMocks
    private InventoryDaoImpl inventoryDao;


    @Test
    public void create() {
        Item item = new Item("Book", 2.3, 4.4, 10);
        inventoryDao.create(item);

        assertEquals("Book", Inventory.getInventoryInstance().getCurrentItemMap().get("Book").getItemName());
    }

    @Test
    public void getItem() {
        Item item = new Item("Table", 2.3, 4.4, 10);
        inventoryDao.create(item);

        assertEquals("Table", inventoryDao.getItem("Table").getItemName());
    }

    @Test
    public void checkIfItemExists_ReturnTrueIfTheItemDoesExists() {
        Item item = new Item("Bottle", 2.3, 4.4, 10);
        inventoryDao.create(item);

        assertEquals(true, inventoryDao.isItemExits("Bottle"));
    }

    @Test
    public void checkIfItemExists_ReturnFalseIfTheItemDoesNotExists() {
        assertEquals(false, inventoryDao.isItemExits("Item_DoesNot_Exists"));
    }


    @Test
    public void updateItem() {
        Item item = new Item("Lamp", 2.3, 4.4, 10);
        inventoryDao.create(item);

        Item itemUpdated = new Item("Lamp", 2.3, 4.4, 12);
        inventoryDao.updateItem(itemUpdated);

        assertEquals(12, inventoryDao.getItem("Lamp").getQuantity());
    }

    @Test
    public void delete() {
        Item item = new Item("Glass", 2.3, 4.4, 10);
        inventoryDao.create(item);

        inventoryDao.delete(item);

        assertEquals(false, inventoryDao.isItemExits("Glass"));
    }
}
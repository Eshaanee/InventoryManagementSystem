package com.sidgs.inventorysystem.service;

import com.sidgs.inventorysystem.dao.InventoryDao;
import com.sidgs.inventorysystem.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceImplTest {

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryDao inventoryDao;

    @Test(expected = Exception.class)
    public void create_IfTheItemAlreadyExistsThenThrowException() throws Exception {
        when(inventoryDao.getItem(any())).thenReturn(new Item("Book", 23.3, 45, 100));

        inventoryService.create("Book", 23.3, 45);
    }

    @Test
    public void create_IfTheItemDoesNotExistsThenCreateTheItem() throws Exception {
        when(inventoryDao.getItem(any())).thenReturn(null);
        doNothing().when(inventoryDao).create(any());
        inventoryService.create("Book", 23.3, 45);

        verify(inventoryDao, times(1)).create(any());
    }

    @Test(expected = Exception.class)
    public void updateBuy_IfTheItemDoesNotExistsThenThrowException() throws Exception {
        when(inventoryDao.getItem(any())).thenReturn(null);

        inventoryService.updateBuy("Book", 23);
    }

    @Test
    public void updateBuy_IfTheItemDoesExistsThenUpdateTheQuantity() throws Exception {
        Item item = new Item("Book", 2.3, 4.4, 10);
        Item itemSpy = spy(item);
        when(inventoryDao.getItem(any())).thenReturn(itemSpy);
        ArgumentCaptor acInteger = ArgumentCaptor.forClass(Integer.class);
        inventoryService.updateBuy("Book", 23);
        verify(itemSpy).setQuantity((Integer) acInteger.capture());
        assertEquals(33, acInteger.getValue());
    }

    @Test(expected = Exception.class)
    public void delete_IfTheItemDoesNotExistsThenThrowException() throws Exception {
        when(inventoryDao.getItem(any())).thenReturn(null);

        inventoryService.delete("Book");
    }

    @Test(expected = Exception.class)
    public void updateSell_IfTheItemDoesNotExistsThenThrowException() throws Exception {
        when(inventoryDao.getItem(any())).thenReturn(null);

        inventoryService.updateSell("Book", 9);
    }

    @Test(expected = Exception.class)
    public void updateSell_IfAvailableQuantityIsLessThanSellQuantityThenThrowException() throws Exception {
        Item item = new Item("Book", 2.3, 4.4, 10);
        when(inventoryDao.getItem(any())).thenReturn(item);

        inventoryService.updateSell("Book", 11);
    }

    @Test
    public void updateSell() throws Exception {
        Item item = new Item("Book", 2.3, 4.4, 13);
        Item itemSpy = spy(item);
        when(inventoryDao.getItem(any())).thenReturn(itemSpy);
        ArgumentCaptor acInteger = ArgumentCaptor.forClass(Integer.class);
        inventoryService.updateSell("Book", 11);
        verify(itemSpy).setQuantity((Integer) acInteger.capture());
        assertEquals(2, acInteger.getValue());
    }

    @Test(expected = Exception.class)
    public void updateSellPrice_IfTheItemDoesNotExistsThenThrowException() throws Exception {
        when(inventoryDao.getItem(any())).thenReturn(null);

        inventoryService.updateSellPrice("Book", 5.5);
    }

    @Test
    public void updateSellPrice() throws Exception {
        Item item = new Item("Book", 2.3, 4.4, 13);
        Item itemSpy = spy(item);
        when(inventoryDao.getItem(any())).thenReturn(itemSpy);
        ArgumentCaptor acDouble = ArgumentCaptor.forClass(Double.class);
        inventoryService.updateSellPrice("Book", 11);
        verify(itemSpy).setSellingPrice((Double) acDouble.capture());
        assertEquals(11.0, acDouble.getValue());
    }
}
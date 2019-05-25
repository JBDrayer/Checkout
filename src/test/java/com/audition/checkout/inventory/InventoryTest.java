package com.audition.checkout.inventory;

import com.audition.checkout.special.ItemSpecial;
import com.audition.checkout.utils.BigDecimalFormatter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class InventoryTest {

    @Mock private ItemSpecial itemSpecial;
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
    private String itemName = RandomStringUtils.randomAlphanumeric(10);
    private BigDecimal itemPrice = new BigDecimal(RandomUtils.nextInt(1,10));
    private BigDecimal markDown = new BigDecimal(RandomUtils.nextInt(1,10));
    private InventoryItem inventoryItem = new InventoryItem(itemName, itemPrice);
    private Inventory inventory;

    @BeforeEach
    void configureInventory(){
        inventoryItems.add(inventoryItem);
        inventory = new Inventory(inventoryItems);
    }
    @Test
    void getInventoryItemFromInventory() {
        InventoryItem foundItem = inventory.getItem(itemName);

        assertThat(foundItem).isEqualTo(inventoryItem);
    }

    @Test
    void throwsExceptionWhenItemNotFoundInInventory() {
        assertThrows(InventoryItemNotFoundException.class, () -> inventory.getItem(""));
    }

    @Test
    void setMarkDownPriceForInventoryItem() {
        inventory.markDownItem(itemName, markDown);

        assertThat(inventoryItem.getPrice()).isEqualTo(BigDecimalFormatter.formatForMoney(itemPrice.subtract(markDown)));
    }

    @Test
    void addsSpecialToInventoryItem() {
        inventory.addSpecialToInventoryItem(itemSpecial, itemName);

        assertThat(inventoryItem.getItemSpecial()).isEqualTo(itemSpecial);
    }
}
package com.designingtypes.shoppingcart;

import java.util.List;
import java.util.Objects;

// Requirements
//     -> You can only pay for a cart once.
//     -> Once a cart is paid for, you cannot change the items in it.
//     -> Empty carts cannot be paid for.

public class ShoppingCart {
    private List<Item> items;
    private boolean isPaid = false;

    // pay for the cart only if it not already paid for
    public boolean pay() {
        if (!this.isPaid()) {
            this.setPaid(true);
            return true;
        } else {
            return false;
        }
    }

    // add item only if cart is not paid for
    public boolean addItem(Item item) {
        if (!this.isPaid()) {
            items.add(item);
            return true;
        } else {
            return false;
        }
    }

    // remove item only if cart is not paid for
    public boolean removeItem(Item item) {
        if (!this.isPaid()) {
            items.removeIf(it -> Objects.equals(item.getSku(), it.getSku()));
            return true;
        } else {
            return false;
        }
    }

    private List<Item> getItems() {
        return items;
    }

    private void setItems(List<Item> items) {
        this.items = items;
    }

    private boolean isPaid() {
        return isPaid;
    }

    private void setPaid(boolean paid) {
        isPaid = paid;
    }
}

// PROBLEMS
// -> allows you to "pay" for an empty cart (one of the requirements above!!)
// -> allows you to "remove" from an empty cart
// -> what happens if a thread is "pay"-ing for the cart, and one more thread updates the cart
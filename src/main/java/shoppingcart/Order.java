package com.designingtypes.shoppingcart;

import java.util.Date;
import java.util.Optional;

// Here the order is trying to capture every state's data in a single place

public class Order {
    private String orderId;
    private Date orderDate;
    private Optional<Date> orderPaidDate;
    private Optional<Float> orderPaidAmount;
    private Optional<Date> shippedDate;
    private Optional<String> shippingMethod;
    private Optional<Date> deliveredDate;
    private Optional<String> deliveredNotes;
    private Optional<Date> returnedDate;
    private Optional<String> returnReason;
}

package com.designingtypes.shoppingcart;

import java.util.Date;

public class Package {
    private PackageStatus packageStatus;
    private String packageId;
    private Date deliveryDate;
    private String deliveryComments; // left at door, handed to partner / roommate etc


    // event handlers
    public void putOnTruck() {
        // what happens if package is already delivered
    }

    public void deliver(String comment) {
        // what happens if package is not put on truck yet
        //
        // "Guideline: Event handling functions should always accept and return the entire state machine"
    }

    // deliveryDate and deliveryComments make sense when package has hit the "delivered" state


}

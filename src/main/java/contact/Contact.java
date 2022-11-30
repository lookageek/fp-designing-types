package com.designingtypes.contact;

// Credits:
// Scott Wlaschin - The "Designing With Types" series - https://fsharpforfunandprofit.com/series/designing-with-types/

public class Contact {
    private String firstName;
    private String middleName;
    private String lastName;

    private String emailAddress;
    private boolean isEmailVerified = false;

    private String address;
    private String city;
    private String postcode;
    private boolean isAddressValid = false;

    /*
    public void updateName(String firstName, String middleName, String lastName) {
        // update name
    }

    public void updateEmail(String emailAddress) {
        // update emailAddress & validate with a service
    }

    public void updateAddress(String address, String city, String postcode) {
        // update address & validate with a service
    }*/
}

// questions to ask
// -> what parts of this record are updated together
// -> surely you would not update just the postcode without updating address and/or city
// -> emailAddress, firstName, postcode etc are all interchangeable since everything is a string?? https://wiki.c2.com/?PrimitiveObsession
// -> what validation can we add for emailAddress, and if it is invalid, what then? (refined types?)

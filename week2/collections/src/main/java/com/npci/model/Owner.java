package com.npci.model;

import java.util.Objects;

public class Owner implements Comparable<Owner> {
    private String name;
    private String address;
    private String contactNumber;

    @Override
    public int compareTo(Owner o) {
        // both name I& contactNumber
        int nameComparison = this.name.compareToIgnoreCase(o.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.contactNumber.compareTo(o.contactNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Owner owner)) return false;
        return Objects.equals(name.toLowerCase(), owner.name.toLowerCase()) && Objects.equals(contactNumber, owner.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), contactNumber);
    }

    public Owner(String name, String address, String contactNumber) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

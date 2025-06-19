package com.npci.creational.builder;

class Employee {
    private String firstName; // Required
    private String lastName; // Required
    private String email; // Optional
    private String phoneNumber; // Optional
    private String address; // Optional
    private String department; // Optional

    public Employee(String firstName, String lastName, String email, String phoneNumber, String address, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }
}

class EmployeeBuilder {
    private String firstName; // Required
    private String lastName; // Required
    private String email; // Optional
    private String phoneNumber; // Optional
    private String address; // Optional
    private String department; // Optional

    public EmployeeBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EmployeeBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public EmployeeBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public EmployeeBuilder withDepartment(String department) {
        this.department = department;
        return this;
    }

    public Employee build() {
        return new Employee(firstName, lastName, email, phoneNumber, address, department);
    }

}

public class Application {
    public static void main(String[] args) {

        // firstName and lastName
        Employee e1 = new EmployeeBuilder("John", "Doe").build();

        // firstName and lastName + email
        Employee e2 = new EmployeeBuilder("Jane", "Doe")
                .withEmail("jd@mail.com")
                .build();

        // firstName and lastName + phoneNumber
        Employee e3 = new EmployeeBuilder("Alice", "Smith")
                .withPhoneNumber("123-456-7890")
                .build();

        // firstName and lastName + email + phoneNumber + address
        Employee e4 = new EmployeeBuilder("Bob", "Johnson")
                .withEmail("bj@mail.com")
                .withPhoneNumber("987-654-3210")
                .withAddress("123 Main St, City, Country")
                .build();

        // firstName and lastName + email + address
        Employee e5 = new EmployeeBuilder("Charlie", "Brown")
                .withAddress("456 Elm St, City, Country")
                .withEmail("cb@mail.com")
                .build();

    }

}



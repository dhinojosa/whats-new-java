package com.xyzcorp.records;

import java.util.List;

record UserAccountR(String firstName, String lastName, String password){

    record UserAccountHistory(List<String> historyItems){}

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UserAccountHistory ops() {
        return new UserAccountHistory(List.of("Foo", "Bar"));
    }

}

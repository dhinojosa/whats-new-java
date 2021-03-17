package com.xyzcorp.records;

import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    void testWhatRecordsCanDo() {
        UserAccountR userAccount = new UserAccountR("Lara", "Gonzalez", "34949dhhd=");
        UserAccountR userAccount2 = new UserAccountR("Lara", "Gonzalez", "34949dhhd=");
        System.out.println(userAccount);
        System.out.println(userAccount.equals(userAccount2));
        System.out.println(userAccount.hashCode() == userAccount2.hashCode());
        userAccount.firstName();
        userAccount.lastName();
    }
}

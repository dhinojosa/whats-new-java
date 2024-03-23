package com.xyzcorp.unnamedvariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public record Order(String state, int amount) {

    static List<Order> createTwentyOrders() {
        String[] usStateAbbreviations = {
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
            "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
            "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
            "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
        };

        Random rng = new Random();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String state = usStateAbbreviations[rng.nextInt(usStateAbbreviations.length)];
            int amount = rng.nextInt((200 - 10) + 1) + 10;
            orders.add(new Order(state, amount));
        }
        return orders;
    }
}

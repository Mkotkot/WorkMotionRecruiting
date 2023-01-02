package com.workmotion.employee.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Status {

    ADDED, IN_CHECK, APPROVED, ACTIVE;


    @JsonCreator
    public static Status fromValue(String text) {

        return Arrays.stream(values()).filter(s -> ifValueEqualToText(s, text)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("Incosrrect Status value '" + text + "'");
        });

    }

    private static boolean ifValueEqualToText(Status status, String text) {
        return status.name().equalsIgnoreCase(text);
    }
}

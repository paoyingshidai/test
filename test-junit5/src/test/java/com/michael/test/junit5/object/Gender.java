package com.michael.test.junit5.object;

/**
 * @author Michael
 */
public enum Gender {

    M("M"),
    F("F");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}

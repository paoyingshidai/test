package com.michael.test.junit5.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Michael
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate dateOfBirth;

}

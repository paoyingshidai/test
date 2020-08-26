package com.michael.test.junit5.extention;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Michael
 */
@Data
public class User implements Serializable {

    private String username;

    private String password;

}

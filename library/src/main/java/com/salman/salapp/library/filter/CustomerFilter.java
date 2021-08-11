package com.salman.salapp.library.filter;

import lombok.Data;

@Data
public class CustomerFilter {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

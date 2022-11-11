package com.graphql.mugi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mugi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInput {
    String firstName;
    String lastName;
    int houseNumber;
    String street;
    String city;
    String zipcode;
    String country;
}

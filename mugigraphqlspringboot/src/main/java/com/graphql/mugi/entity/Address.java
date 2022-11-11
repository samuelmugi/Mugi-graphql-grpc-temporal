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
public class Address {
    int addressId;
    int userId;
    int houseNumber;
    String street;
    String city;
    String zipcode;
    String country;
}

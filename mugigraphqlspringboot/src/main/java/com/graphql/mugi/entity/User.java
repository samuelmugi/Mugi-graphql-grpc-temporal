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
public class User {
    int userId;
    String firstName;
    String lastName;
}

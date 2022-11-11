package com.graphql.mugi.controller;

import com.graphql.mugi.entity.*;
import com.graphql.mugi.service.GraphqlService;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mugi
 */
@Controller
public class MugiGraphQlController {

    private final GraphqlService graphqlService;

    public MugiGraphQlController(GraphqlService graphqlService) {
        this.graphqlService = graphqlService;
    }

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }

    @QueryMapping
    public Collection<User> getAllUsers() {
        return graphqlService.getAllUsers();
    }

    @QueryMapping
    public User getUserById(@Argument int userId) {
        return graphqlService.getUserById(userId);
    }

    @BatchMapping(field = "address")
    public Map<User, Address> getAddressByUserIds(List<User> userList) {
        System.out.println("=============Address============");
        Set<Integer> userIdList = userList.stream().map(a -> a.getUserId()).collect(Collectors.toSet());
        return graphqlService.getAddressByUserIdList(userIdList);
    }

    @MutationMapping
    public User addNewUser(@Argument UserInput user) {
        return graphqlService.addNewUser(user);
    }

    @SubscriptionMapping
    public Publisher<String> greetNewUsers() {
        return graphqlService.greetNewUser();
    }
}

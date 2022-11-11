package com.graphql.mugi.service;

import com.graphql.mugi.entity.Address;
import com.graphql.mugi.entity.User;
import com.graphql.mugi.entity.UserInput;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Mugi
 */
@Service
public class GraphqlService {
    private static Map<Integer, User> userData = new ConcurrentHashMap<>();
    private static Map<Integer, Address> userAddress = new ConcurrentHashMap<>();

    @Autowired
    private Sinks.Many<User> userSink;

    @Autowired
    private Flux<User> userFlux;

    public User getUserById(int userId) {
        return userData.containsKey(userId) ?
                userData.get(userId) : new User();
    }

    public Address getAddressById(int addressId) {
        return userAddress.containsKey(addressId) ?
                userAddress.get(addressId) : new Address();
    }

    public Address getAddressByUserId(int userId) {
        return userAddress.values()
                .stream()
                .filter(a -> a.getUserId() == userId)
                .findAny().orElse(new Address());
    }

    public Collection<User> getAllUsers() {
        return userData.values();
    }

    public Map<User, Address> getAddressByUserIdList(Set<Integer> userIdList) {
        return userAddress.values()
                .stream()
                .filter(a -> userIdList.contains(a.getUserId()))
                .collect(Collectors.toMap(a -> getUserById(a.getUserId()), a -> a));
    }

    public User addNewUser(UserInput userIn) {
        User user = new User(userData.size() + 1, userIn.getFirstName(), userIn.getLastName());
        userData.put(userData.size() + 1, user);
        Address add = new Address(userAddress.size() + 1, user.getUserId(),
                userIn.getHouseNumber(),
                userIn.getStreet(), userIn.getCity(), userIn.getZipcode(), userIn.getCountry());
        userAddress.put(userAddress.size() + 1, add);
        userSink.tryEmitNext(user);
        return user;
    }

    public Publisher<String> greetNewUser() {
        return userFlux.map(u -> greet(Optional.of(u.getFirstName() + " " + u.getLastName())));
    }

    public String greet(Optional<String> name) {
        System.out.println("-----greeting-----");
        return "Hello " + name.orElse("to you") + "!";
    }
}

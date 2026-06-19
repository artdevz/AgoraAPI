package com.agora.collectors;

import java.util.ArrayList;
import java.util.List;

import com.agora.interfaces.UserIterator;
import com.agora.iterators.ActiveUserIterator;
import com.agora.models.User;

public class UserCollection {

    private final List<User> users = new ArrayList<>();

    public void Add(User user) {
        users.add(user);
    }

    public UserIterator ActiveUsersIterator() {
        return new ActiveUserIterator(users);
    }
    
}

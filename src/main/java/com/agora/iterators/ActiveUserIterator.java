package com.agora.iterators;

import java.util.List;

import com.agora.enums.UserStatus;
import com.agora.interfaces.UserIterator;
import com.agora.models.User;

public class ActiveUserIterator implements UserIterator {

    private final List<User> users;
    private int index = 0;

    public ActiveUserIterator(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean HasNext() {
        while (index < users.size()) {
            if (users.get(index).GetStatus() == UserStatus.ACTIVE) return true;
            index++;
        }

        return false;
    }

    @Override
    public User Next() {
        return users.get(index++);
    }
    
}

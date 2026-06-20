package com.agora.interfaces;

import com.agora.models.User;

public interface UserIterator {
    boolean HasNext();
    User Next();
}

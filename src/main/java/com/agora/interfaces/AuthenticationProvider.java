package com.agora.interfaces;

import com.agora.models.User;

public interface AuthenticationProvider {
    User Authenticate(Object request);
}

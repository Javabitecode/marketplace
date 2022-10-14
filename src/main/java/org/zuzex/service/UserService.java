package org.zuzex.service;

import org.zuzex.model.User;

public interface UserService {
    User findById(Long id);
}

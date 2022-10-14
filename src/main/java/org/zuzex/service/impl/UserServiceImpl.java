package org.zuzex.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.zuzex.model.User;
import org.zuzex.repository.UserRepository;
import org.zuzex.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepository;

    @Override
    public User findById(Long id) {
        User entity = userRepository.findById(id);
        log.info(entity.getField());
        return entity;
    }
}

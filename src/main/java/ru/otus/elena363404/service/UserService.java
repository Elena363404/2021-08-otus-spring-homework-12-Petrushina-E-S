package ru.otus.elena363404.service;

import ru.otus.elena363404.domain.User;

import java.util.Optional;

public interface UserService {

  Optional<User> findUserByUsername(String username);
}

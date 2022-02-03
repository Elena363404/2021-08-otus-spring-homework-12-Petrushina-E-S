package ru.otus.elena363404.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.domain.User;
import ru.otus.elena363404.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Optional<User> findUserByUsername(String username)
    throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByUsername(username);
    user.orElseThrow(() -> new UsernameNotFoundException("User not present"));
    return user;
  }
}

package app.core.security;

import app.data.mappers.UserMapper;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

@Service
public class MyUserDetails implements UserDetailsService {
  @Autowired
  private UserMapper userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final User user = userRepository.getByEmail(email);
    return UserPrincipal.create(user);
  }
  @Transactional
  public UserDetails loadUserById(Long id) {
      User user = userRepository.getUser(id);
      return UserPrincipal.create(user);
  }
 
}

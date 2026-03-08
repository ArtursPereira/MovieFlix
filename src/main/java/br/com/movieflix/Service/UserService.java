package br.com.movieflix.Service;

import br.com.movieflix.Entity.User;
import br.com.movieflix.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}

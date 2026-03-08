package br.com.movieflix.Controller;

import br.com.movieflix.Controller.request.UserRequest;
import br.com.movieflix.Controller.response.UserResponse;
import br.com.movieflix.Entity.User;
import br.com.movieflix.Service.UserService;
import br.com.movieflix.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest resquest) {
        User savedUser = userService.save(UserMapper.toUser(resquest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));

    }
}

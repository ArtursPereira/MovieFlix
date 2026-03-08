package br.com.movieflix.mapper;

import br.com.movieflix.Controller.request.UserRequest;
import br.com.movieflix.Controller.response.UserResponse;
import br.com.movieflix.Entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest request){
        return User.builder()
                .name(request.name())
                .password(request.password())
                .email(request.email())
                .build();

    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}

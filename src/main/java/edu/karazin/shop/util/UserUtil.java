package edu.karazin.shop.util;

import edu.karazin.shop.dto.UserDto;
import edu.karazin.shop.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtil {

    public UserUtil() {
    }

    public List<UserDto> convertUsersToUserDtos(List<User> users) {
        if (users == null) {
            return null;
        }
        List<UserDto> userDtos = new ArrayList<>();
        UserDto userDto;
        for (User user : users) {
            userDto = new UserDto();
            userDto.setLogin(user.getLogin());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}

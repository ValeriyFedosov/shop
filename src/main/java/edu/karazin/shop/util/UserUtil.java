package edu.karazin.shop.util;

import edu.karazin.shop.dto.UserDto;
import edu.karazin.shop.model.User;
import edu.karazin.shop.web.UserForm;
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

    public static boolean validate(UserForm form) {
        return !org.h2.util.StringUtils.isNumber(form.getLogin()) && form.getLogin().length() <= 10
                && !form.getLogin().isEmpty() && form.getPassword().length() <= 12 && !form.getPassword().isEmpty();
    }
}

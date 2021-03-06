package LukaszSz90.simpleapp.converter;

import LukaszSz90.simpleapp.data.user.UserSummary;
import LukaszSz90.simpleapp.domain.model.User;
import LukaszSz90.simpleapp.domain.model.UserDetails;
import LukaszSz90.simpleapp.web.command.EditUserCommand;
import LukaszSz90.simpleapp.web.command.RegisterUserCommand;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User from(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .username(registerUserCommand.getUsername())
                .password(registerUserCommand.getPassword())
                .build();
    }

    public UserSummary toUserSummary(User user) {
        UserDetails details = user.getDetails();
        return UserSummary.builder()
                .username(user.getUsername())
                .firstName(details.getFirstName())
                .lastName(details.getLastName())
                .birthDate(details.getBirthDate())
                .build();
    }

    public User from(EditUserCommand editUserCommand, User user) {
        UserDetails details = user.getDetails();
        details.setFirstName(editUserCommand.getFirstName());
        details.setLastName(editUserCommand.getLastName());
        details.setBirthDate(editUserCommand.getBirthDate());
        return user;
    }
}

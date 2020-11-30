package LukaszSz90.simpleapp.service;

import LukaszSz90.simpleapp.converter.UserConverter;
import LukaszSz90.simpleapp.domain.model.User;
import LukaszSz90.simpleapp.domain.repository.UserRepository;
import LukaszSz90.simpleapp.exception.UserAlreadyExistException;
import LukaszSz90.simpleapp.web.command.RegisterUserCommand;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@Slf4j @RequiredArgsConstructor
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public Long create(RegisterUserCommand registerUserCommand) {
        log.debug("Dane użytkownika do zapisania: {}", registerUserCommand);

        User userToCreate = userConverter.from(registerUserCommand);
        if(userRepository.existByUsername(userToCreate.getUsername())) {
            throw new UserAlreadyExistException(String.format("Użytkownik %s już istnieje", userToCreate.getUsername()));
        }
    }
}

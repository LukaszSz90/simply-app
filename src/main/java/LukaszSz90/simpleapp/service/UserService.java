package LukaszSz90.simpleapp.service;

import LukaszSz90.simpleapp.converter.UserConverter;
import LukaszSz90.simpleapp.domain.model.User;
import LukaszSz90.simpleapp.domain.repository.UserRepository;
import LukaszSz90.simpleapp.exception.UserAlreadyExistException;
import LukaszSz90.simpleapp.web.command.RegisterUserCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service @Transactional
@Slf4j @RequiredArgsConstructor
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long create(RegisterUserCommand registerUserCommand) {
        log.debug("Dane użytkownika do zapisania: {}", registerUserCommand);

        User userToCreate = userConverter.from(registerUserCommand);
        log.debug("uzyskany obiekt użytkownika do zapisu: {}", userToCreate);
        if(userRepository.existsByUsername(userToCreate.getUsername())) {
            log.debug("Próba rejestracji na istniejącego uzytkownika");
            throw new UserAlreadyExistException(String.format("Użytkownik %s już istnieje", userToCreate.getUsername()));
        }

        userToCreate.setActive(Boolean.TRUE);
        userToCreate.setRoles(Set.of("ROLE_USER"));
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        userRepository.save(userToCreate);
        log.debug("Zapisany użytkownik: {}", userToCreate);

        return userToCreate.getId();
    }


}

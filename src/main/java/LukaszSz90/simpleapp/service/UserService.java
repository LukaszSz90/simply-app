package LukaszSz90.simpleapp.service;

import LukaszSz90.simpleapp.converter.UserConverter;
import LukaszSz90.simpleapp.data.user.UserSummary;
import LukaszSz90.simpleapp.domain.model.User;
import LukaszSz90.simpleapp.domain.model.UserDetails;
import LukaszSz90.simpleapp.domain.repository.UserRepository;
import LukaszSz90.simpleapp.exception.UserAlreadyExistException;
import LukaszSz90.simpleapp.web.command.EditUserCommand;
import LukaszSz90.simpleapp.web.command.RegisterUserCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long create(RegisterUserCommand registerUserCommand) {
        log.debug("Dane użytkownika do zapisania: {}", registerUserCommand);

        User userToCreate = userConverter.from(registerUserCommand);
        log.debug("uzyskany obiekt użytkownika do zapisu: {}", userToCreate);
        if (userRepository.existsByUsername(userToCreate.getUsername())) {
            log.debug("Próba rejestracji na istniejącego uzytkownika");
            throw new UserAlreadyExistException(String.format("Użytkownik %s już istnieje", userToCreate.getUsername()));
        }

        setDefaultActive(userToCreate);
        setDefaultData(userToCreate);
        userRepository.save(userToCreate);
        log.debug("Zapisany użytkownik: {}", userToCreate);

        return userToCreate.getId();
    }

    private void setDefaultData(User userToCreate) {
        setDefaultRole(userToCreate);
        setEncodePassword(userToCreate);
        setDefaultDetails(userToCreate);
    }

    private void setDefaultDetails(User userToCreate) {
        userToCreate.setDetails(UserDetails.builder()
                .user(userToCreate)
                .build());
    }

    private void setEncodePassword(User userToCreate) {
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
    }

    private void setDefaultRole(User userToCreate) {
        userToCreate.setRoles(Set.of("ROLE_USER"));
    }

    private void setDefaultActive(User userToCreate) {
        userToCreate.setActive(Boolean.TRUE);
    }

    @Transactional
    public UserSummary getCurrentUserSummary() {
        log.debug("Pobieranie danych użytkownika aktualnie zalogowanego");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.getAuthenticatedUser(username);
        UserSummary summary = userConverter.toUserSummary(user);

        log.debug("Podsumowanie danych użytkownika: {}", summary);

        return summary;
    }

    @Transactional
    public boolean edit(EditUserCommand editUserCommand) {
        log.debug("Dane użytkownika do edycji: {}", editUserCommand);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getAuthenticatedUser(username);

        user = userConverter.from(editUserCommand, user);
        log.debug("Zmodyfikowane dane użytkownika: {}", user.getDetails());
        return true;
    }
}

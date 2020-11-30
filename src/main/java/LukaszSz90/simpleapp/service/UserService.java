package LukaszSz90.simpleapp.service;

import LukaszSz90.simpleapp.web.command.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@Slf4j @RequiredArgsConstructor
public class UserService {

    public Long create(RegisterUserCommand registerUserCommand) {
        return null;
    }
}

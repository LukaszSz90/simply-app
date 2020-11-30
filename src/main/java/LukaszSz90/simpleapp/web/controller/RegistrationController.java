package LukaszSz90.simpleapp.web.controller;

import LukaszSz90.simpleapp.exception.UserAlreadyExistException;
import LukaszSz90.simpleapp.service.UserService;
import LukaszSz90.simpleapp.web.command.RegisterUserCommand;
import antlr.ASTFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegister(Model model) {
        model.addAttribute(new RegisterUserCommand());
        return "register/form";
    }

    @PostMapping
    public String processRegister(@Valid RegisterUserCommand registerUserCommand, BindingResult bindingResult) {

        log.debug("Dane do utworzenia użytkownika: {}", registerUserCommand);
        if (bindingResult.hasErrors()) {
            log.debug("Błedne dane: {}", bindingResult.getAllErrors());
            return "register/form";
        }

        try {
            Long id = userService.create(registerUserCommand);
            log.debug("Utworzono użytkownika o id = {}", id);

            return "redirect:/login";
        }
        catch (UserAlreadyExistException uaee){
            bindingResult.rejectValue("username", null, "Użytkownik o podanej nazwie już istnieje");
            return "register/form";
        }
        catch (RuntimeException re) {
            bindingResult.rejectValue(null, null, "Wystąpił błąd");
            return "register/form";
        }
    }


}

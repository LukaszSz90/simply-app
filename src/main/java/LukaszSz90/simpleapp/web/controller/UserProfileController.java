package LukaszSz90.simpleapp.web.controller;

import LukaszSz90.simpleapp.data.user.UserSummary;
import LukaszSz90.simpleapp.service.UserService;
import LukaszSz90.simpleapp.web.command.EditUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
@Slf4j @RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping
    public String getProfilePage(Model model) {
        UserSummary summary = userService.getCurrentUserSummary();
        EditUserCommand editUserCommand = createEditCommander(summary);
        model.addAttribute(summary);
        model.addAttribute(editUserCommand);
        return "user/profile";
    }

    @ModelAttribute("userSummary")
    public UserSummary userSummary() {
        return userService.getCurrentUserSummary();
    }

    private EditUserCommand createEditCommander(UserSummary summary) {
        return EditUserCommand.builder()
                .firstName(summary.getFirstName())
                .lastName(summary.getLastName())
                .birthDate(summary.getBirthDate())
                .build();
    }

    @PostMapping("/edit")
    public String editUserProfile(@Valid EditUserCommand editUserCommand, BindingResult bindings) {
        log.debug("Dane do edycji użytkownika: {}", editUserCommand);
        if(bindings.hasErrors()) {
            log.debug("Błedne dane: {}", bindings.getAllErrors());
            return "/user/profile";
        }

        try {
            boolean succes = userService.edit(editUserCommand);
            log.debug("udana edycja danych: {}", succes);
            return "redirect:/profile";
        }
        catch (RuntimeException re) {
            log.warn(re.getLocalizedMessage());
            log.debug("Bład przy edycji danych",re);
            bindings.rejectValue(null, null, "Wystąpił bład");
        }

        return "redirect:/profile";
    }
}

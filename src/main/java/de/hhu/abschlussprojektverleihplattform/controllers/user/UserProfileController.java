package de.hhu.abschlussprojektverleihplattform.controllers.user;

import de.hhu.abschlussprojektverleihplattform.model.UserEntity;
import de.hhu.abschlussprojektverleihplattform.service.propay.ProPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserProfileController {

    @Autowired
    public ProPayService proPayService;

    @GetMapping("/profile")
    public String getProfile(Model model, Authentication auth) throws Exception{
        UserEntity user = (UserEntity) auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("user_balance",proPayService.getBalance(user.getUsername()));

        return "profile";
    }

    @PostMapping("/profile/deposit")
    public String depositAmountIntoPropay(
        Authentication auth
    ) throws Exception{

        UserEntity user = (UserEntity) auth.getPrincipal();
        proPayService.changeUserBalanceBy(user.getUsername(),100);
        return "redirect:/profile";
    }
}
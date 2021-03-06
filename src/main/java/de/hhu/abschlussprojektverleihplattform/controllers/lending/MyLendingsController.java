package de.hhu.abschlussprojektverleihplattform.controllers.lending;

import de.hhu.abschlussprojektverleihplattform.model.LendingEntity;
import de.hhu.abschlussprojektverleihplattform.model.UserEntity;
import de.hhu.abschlussprojektverleihplattform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyLendingsController {

    static final String url="/mylendings";

    //this can be extended to also show past lendings.
    //this is benefit for people who want to lend
    //things multiple times so they can find those products

    private final ILendingService lendingService;

    @Autowired
    public MyLendingsController(ILendingService lendingService) {
        this.lendingService = lendingService;
    }

    @GetMapping("/mylendings")
    public String getMyCurrentLendings(Model model, Authentication auth){
        UserEntity user = (UserEntity) auth.getPrincipal();
        model.addAttribute("user",user);
        List<LendingEntity> lendings = lendingService.getAllLendingsForUser(user);
        List<LendingEntity> requestedLendings = lendingService.getAllRequestedLendings(lendings);
        List<LendingEntity> confirmedLendings = lendingService.getAllConfirmedLendings(lendings);
        List<LendingEntity> returnedLendings = lendingService.getAllReturnedLendings(lendings);
        List<LendingEntity> conflictedLendings = lendingService.getAllConflictedLendings(lendings);
        List<LendingEntity> completedLendings = lendingService.getAllCompletedLendings(lendings);
        model.addAttribute("requestedLendings", requestedLendings);
        model.addAttribute("confirmedLendings", confirmedLendings);
        model.addAttribute("returnedLendings", returnedLendings);
        model.addAttribute("conflictedLendings", conflictedLendings);
        model.addAttribute("completedLendings", completedLendings);
        return "mycurrentlendings";
    }

    @PostMapping("/mylendings/return")
    public String handleReturn(@RequestParam Long id) throws Exception{
        LendingEntity requestedLending = lendingService.getLendingById(id);
        lendingService.returnProduct(requestedLending);
        return "redirect:/mylendings";
    }
}

package mg.unidev.app.controllers;

import mg.unidev.app.entities.Account;
import mg.unidev.app.entities.Operation;
import mg.unidev.app.services.BankService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(path = "/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping(path = "/")
    public ModelAndView account() {
        return new ModelAndView("pages/account").addObject("name","Solo Soaniaina");
    }

    @GetMapping(path = "/get-account")
    public ModelAndView searchAccount(String codeAcc, @RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "4") int size){

        Map<String, Object> variables = new HashMap<>(Map.of("codeAccount",codeAcc));

        try {
            Account account = bankService.getAccount(codeAcc);
            Page<Operation> operations = bankService.listOperation(codeAcc, size, page);
            // tableau qui contient le nombre total des pages
            int[] totalPages = new int[operations.getTotalPages()];
            variables.put("accountF", account);
            variables.put("currentPage", page);
            variables.put("pages", totalPages);
            variables.put("operations", operations.getContent());
        } catch (Exception exception) {
            variables.put("error",exception);
        }
        return new ModelAndView("pages/account").addAllObjects(variables);
    }

    @PostMapping(value = "/save-operation")
    public String saveOperation(String accountCode, String operationType, String accountCodeTwo, double amount) {
        try {
            switch (operationType) {
                case "versement":
                    bankService.verser(accountCode, amount);
                    break;
                case "retrait":
                    bankService.retirer(accountCode, amount);
                    break;
                default:
                    bankService.virement(accountCode, accountCodeTwo, amount);
                    break;
            }
        } catch (Exception e) {
            return String.format("redirect:/bank/get-account?codeAcc=%s&error=%s",accountCode,e.getMessage());
        }
        return String.format("redirect:/bank/get-account?codeAcc=%s",accountCode);
    }

}

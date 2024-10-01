package com.mindgate.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mindgate.main.domain.Cheque;
import com.mindgate.main.domain.Login;
import com.mindgate.main.service.AccountService;
import com.mindgate.main.service.ChequeService;
import com.mindgate.main.service.CustomerService;
import com.mindgate.main.service.LoginService;
import com.mindgate.main.service.MoneyTransferService;
import com.mindgate.main.service.TransactionService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("app")
public class LoginController extends HttpServlet {
    private final AccountService accountService;
    private final LoginService loginService;
    private final CustomerService customerService;
    private final ChequeService chequeService;
    private final TransactionService transactionService;
    private final MoneyTransferService moneyTransferService;


    private int count = 0;

    public LoginController(LoginService loginService, CustomerService customerService, AccountService accountService, ChequeService chequeService, TransactionService transactionService, MoneyTransferService moneyTransferService) {
        this.loginService = loginService;
        this.customerService = customerService;
        this.accountService = accountService;
        this.chequeService = chequeService;
        this.transactionService = transactionService;
        this.moneyTransferService = moneyTransferService;
    }


    @RequestMapping("/")
    public String loginPage() {
        return "frontpage";
    }

    @RequestMapping("/prelogin")
    public String prelogin(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

   @RequestMapping("/validation")
    public String loginValidation(
    @Valid Login login, 
    BindingResult bindingResult, 
    Model model, 
    jakarta.servlet.http.HttpServletRequest request) {
    
    if (bindingResult.hasErrors()) {
        System.out.println("Error");
        return "/";
    } else {
        Login login1 = loginService.login(login);
        if (login1 == null) {
            return "ErrorLogin";
        } else if (login1.getStatus().equals("ACTIVE") && login.getLoginCount() == login1.getLoginCount()) {
            model.addAttribute("username", login1.getUsername());
            
            // Get the session from the request object and set the attribute
            HttpSession session = request.getSession();
            session.setAttribute("username", login1.getUsername());

            return "homepage";
        } else if (login1.getStatus().equals("BLOCKED")) {
            return "blocked";
        } else {
            return "ErrorLogin";
        }
    }
}


    @RequestMapping("/unapprovedlist")
    public ModelAndView getALLUnapprovedCustomer() {
        ModelAndView modelAndView = new ModelAndView("adminunapprovedcustomers");
        modelAndView.addObject("unapprovedCustomers", customerService.getAllUnapprovedCustomer());
        return modelAndView;
    }

    @RequestMapping("/homepage")
    public String homepage1() {
        return "homepage";
    }

    @RequestMapping("/blockedlist")
    public ModelAndView getALLBlockedCustomer() {
        ModelAndView modelAndView = new ModelAndView("adminblockedcustomers");
        modelAndView.addObject("blockedCustomers", customerService.getAllBlockedCustomer());
        return modelAndView;
    }

    @RequestMapping("/allcustomerlist")
    public ModelAndView getALLCustomer() {
        ModelAndView modelAndView = new ModelAndView("adminallcustomers");
        modelAndView.addObject("allCustomers", customerService.getAllCustomer());
        return modelAndView;
    }

    @RequestMapping("/approveCustomer/{customerId}")
    public ModelAndView approveCustomer(@PathVariable int customerId) {
        System.out.println("Customer ID: " + customerId);
        customerService.approveCustomer(customerId);
        ModelAndView modelAndView = new ModelAndView("adminunapprovedcustomers");
        modelAndView.addObject("unapprovedCustomers", customerService.getAllUnapprovedCustomer());
        return modelAndView;
    }

    @RequestMapping("/rejectCustomer/{customerId}")
    public ModelAndView rejectCustomer(@PathVariable int customerId) {
        System.out.println("Customer ID: " + customerId);
        customerService.deleteCustomer(customerId);
        ModelAndView modelAndView = new ModelAndView("adminunapprovedcustomers");
        modelAndView.addObject("unapprovedCustomers", customerService.getAllUnapprovedCustomer());
        return modelAndView;
    }

    @RequestMapping("/unblockCustomer/{customerId}")
    public ModelAndView unblockCustomer(@PathVariable int customerId) {
        System.out.println("Customer ID: " + customerId);
        customerService.unblockCustomer(customerId);
        ModelAndView modelAndView = new ModelAndView("adminblockedcustomers");
        modelAndView.addObject("blockedCustomers", customerService.getAllBlockedCustomer());
        return modelAndView;
    }


    @RequestMapping("/newaccountapprovals")
    public ModelAndView getNewAccountApprovals() {
        ModelAndView modelAndView = new ModelAndView("adminnewaccountapprovals");
        modelAndView.addObject("newAccounts", accountService.getAllNewAccounts());
        return modelAndView;
    }

    @RequestMapping("/approveAccount/{accountNumber}")
    public ModelAndView approveAccount(@PathVariable int accountNumber) {
        System.out.println("Account Number: " + accountNumber);
        accountService.approveAccount(accountNumber);
        ModelAndView modelAndView = new ModelAndView("adminnewaccountapprovals");
        modelAndView.addObject("newAccounts", accountService.getAllNewAccounts());
        return modelAndView;
    }

    @RequestMapping("/rejectAccount/{accountNumber}")
    public ModelAndView rejectAccount(@PathVariable int accountNumber) {
        System.out.println("Account Number: " + accountNumber);
        accountService.rejectAccount(accountNumber);
        ModelAndView modelAndView = new ModelAndView("adminnewaccountapprovals");
        modelAndView.addObject("newAccounts", accountService.getAllNewAccounts());
        return modelAndView;
    }


    @RequestMapping("/newcheques")
    public String newCheques(jakarta.servlet.http.HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Cheque> cheques = chequeService.getAllCheckToClear();
        session.setAttribute("issuedadmincheques", cheques);
        return "adminclearcheques";
    }
    
    @RequestMapping("/issueCheque/{chequeNumber}")
    public String issueCheque(@PathVariable int chequeNumber) {
        System.out.println("Cheque Number: " + chequeNumber);
        Cheque cheque = chequeService.getChequeByChequeNumber(chequeNumber);
        int toAccount = cheque.getToAccountNumber();
        int fromAccount = cheque.getFromAccountNumber();
        double amount = cheque.getAmount();
        if (moneyTransferService.transferMoneyByCheque(fromAccount, toAccount, amount)) {
            chequeService.clearCheque(chequeNumber);
        }
        else{
            chequeService.bounceCheque(chequeNumber);
        }
        return "redirect:/app/newcheques";
    }

    @RequestMapping("/logout")
    public String logout(jakarta.servlet.http.HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "frontpage";
    }
   

}

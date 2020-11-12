package com.dinesh.springbootjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.springbootjpa.dao.BankAccountDAO;
import com.dinesh.springbootjpa.entity.BankAccount;
import com.dinesh.springbootjpa.exception.BankTransactionException;
import com.dinesh.springbootjpa.form.CreateAccountForm;
import com.dinesh.springbootjpa.form.SendMoneyForm;
import com.dinesh.springbootjpa.model.BankAccountInfo;

@Controller
public class AccountController {

	@Autowired
	private BankAccountDAO bankAccountDAO;

	@GetMapping("/findAccount/{id}")
	public BankAccount findById(@PathVariable long id) {
		return bankAccountDAO.findById(id);
	}

	@GetMapping("/findAllAccount")
	public List<BankAccountInfo> findAllAccount() {
		return bankAccountDAO.listBankAccountInfo();
	}


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBankAccounts(Model model) {
        List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
 
        model.addAttribute("accountInfos", list);
 
        return "accountsPage";
    }
 
    @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
    public String viewSendMoneyPage(Model model) {
 
        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700d);
 
        model.addAttribute("sendMoneyForm", form);
        List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
        model.addAttribute("accountInfos", list);
        return "sendMoneyPage";
    }
 
  
    @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
    public String processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
 
        System.out.println("Send Money: " + sendMoneyForm.getAmount());
 
        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/sendMoneyPage";
        }
        return "redirect:/";
    }
    
    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String viewcreateAccountPage(Model model) {
    	System.out.println(" View Account Create page called");
        CreateAccountForm form = new CreateAccountForm();
        model.addAttribute("createAccountForm", form);
        return "createAccountPage";
    }
    
    
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String processCreateAccount(Model model, CreateAccountForm createAccountForm) {
 
        System.out.println("Money Deposited: " + createAccountForm.getAmount());
        System.out.println("Accoun Name: " + createAccountForm.getAccountName());

        BankAccount account=new BankAccount();
        account.setBalance(createAccountForm.getAmount());
        account.setFullName(createAccountForm.getAccountName());
        try {
            bankAccountDAO.createAccount(account);
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "/createAccount";
        }
        return "redirect:/";
    }
	
}

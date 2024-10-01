package com.mindgate.main.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Cheque;
import com.mindgate.main.domain.Current;
import com.mindgate.main.domain.Customer;
import com.mindgate.main.domain.DTO;
import com.mindgate.main.domain.File;
import com.mindgate.main.domain.FixedDeposit;
import com.mindgate.main.domain.MoneyTransfer;
import com.mindgate.main.domain.Saving;
import com.mindgate.main.domain.Transaction;
import com.mindgate.main.service.AccountService;
import com.mindgate.main.service.ChequeService;
import com.mindgate.main.service.CurrentAccountService;
import com.mindgate.main.service.CustomerService;
import com.mindgate.main.service.FileService;
import com.mindgate.main.service.FixedDepositService;
import com.mindgate.main.service.MoneyTransferService;
import com.mindgate.main.service.SavingAccountService;
import com.mindgate.main.service.TransactionService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("app")
public class CustomerController extends HttpServlet {
	private final CustomerService customerService;
	private final FileService fileService; 
	private final SavingAccountService savingAccountService;
	private final CurrentAccountService currentAccountService;
	private final FixedDepositService fixedDepositService;
	private final TransactionService transactionService;
	private final AccountService accountService;
	private final MoneyTransferService moneyTransferService;
	private final ChequeService chequeService;
	
	public CustomerController(CustomerService customerService , FileService fileService,
	 SavingAccountService savingAccountService, CurrentAccountService currentAccountService
	 , FixedDepositService fixedDepositService, TransactionService transactionService, AccountService accountService,
	  MoneyTransferService moneyTransferService, ChequeService chequeService) {
		this.customerService = customerService;
		this.fileService = fileService;
		this.savingAccountService = savingAccountService;
		this.currentAccountService = currentAccountService;
		this.fixedDepositService = fixedDepositService;
		this.transactionService = transactionService;
		this.accountService = accountService;
		this.moneyTransferService = moneyTransferService;
		this.chequeService = chequeService;
	}

	@RequestMapping("/customerhomepage")
    public String homepage1() {
        return "customerhomepage";
    }

	@RequestMapping("/precustomerlogin")
    public String prelogin(Model model) {
		model.addAttribute("customerlogin", new Customer());
        return "customerlogin";
    }

	@RequestMapping("/customervalidation")
	public String loginValidation(@Valid Customer customer, BindingResult bindingResult, Model model,jakarta.servlet.http.HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			System.out.println("Error");
			return "/";
		}
		else{
			Customer customer1 = customerService.loginCustomer(customer);
			//		System.out.println(customer1.getCustomerAccountStatus() + "Account status is here");

					if (customer1 == null) {
						return "CustomerErrorLogin";
					}
					else
					if ( customer1 != null) {
						if (customer1.getCustomerAccountStatus().equals("ACTIVE") && customer.getCustomerPassword().equals(customer1.getCustomerPassword())) {
							File file = fileService.getFileById(customer1.getFileId());
							String photoBase64 = Base64.getEncoder().encodeToString(file.getCustomerPhoto());
							// model.addAttribute("customername", customer1.getCustomerFirstName());
							model.addAttribute("customerlname", customer1.getCustomerLastName());
							HttpSession session = request.getSession();
							List<Map<String, String>> transactionList = new ArrayList<>();
							List<Account> accountList ;
							System.out.println("Customer ID: " + customer1);
							accountList = accountService.getAllAccounts(customer1);
							System.out.println(accountList);
							for (int i = 1; i <= 20; i++) {
								Map<String, String> transaction = new HashMap<>();
								transaction.put("id", "TID" + i);
								transaction.put("date", "2024-09-0" + (i % 10 + 1));
								transaction.put("type", "Deposit");
								transaction.put("amount", "$" + (i * 10));
								transaction.put("balance", "$" + (100 + (i * 10)));
								transaction.put("status", "Completed");
								transactionList.add(transaction);
							}
							request.setAttribute("transactionList", transactionList);
           					session.setAttribute("firstname", customer1.getCustomerFirstName());
							session.setAttribute("lastname", customer1.getCustomerLastName());
							session.setAttribute("customerid", customer1.getCustomerId());
							session.setAttribute("customeremail", customer1.getCustomerEmail());
							session.setAttribute("customermobile", customer1.getCustomerMobile());
							session.setAttribute("customeraddress", customer1.getCustomerAddress());
							session.setAttribute("customerdob", customer1.getCustomerDob());
							session.setAttribute("customeraccountcreationdate", customer1.getCustomerAccountCreationDate());
							session.setAttribute("customerphoto", photoBase64);	
							session.setAttribute("accountlist", accountList);
							session.setAttribute("currentcustomer", customer1);
							

							return "customerhomepage";
						} else if (customer1.getCustomerAccountStatus().equals("UNAPPROVED")) {
							return "customernotapproved";
						}
						else if (customer1.getCustomerAccountStatus().equals("BLOCKED")) {
							return "blocked";
						}
					}
		}
		return "CustomerErrorLogin";

	}

	@RequestMapping("/precustomeradd")
	public String preadd(Model model) {
		double annualInterestRate1year = 4.5;
		double annualInterestRate2year = 5.0;
		double annualInterestRate3year = 5.5;
		model.addAttribute("annualInterestRate1year", annualInterestRate1year);
		model.addAttribute("annualInterestRate2year", annualInterestRate2year);
		model.addAttribute("annualInterestRate3year", annualInterestRate3year);
		return "customerregistration";
	}

	@ModelAttribute("customer")
    public Customer createCustomerModel() {
        return new Customer();
    }

    @ModelAttribute("file")
    public File createFileModel() {
        return new File();
    }

	@ModelAttribute("account")
	public Account createAccountModel() {
		return new Account();
	}
	@ModelAttribute("savingsAccount")
	public Saving createSavingsAccountModel() {
		return new Saving();
	}

	@ModelAttribute("currentAccount")
	public Current createCurrentAccountModel() {
		return new Current();
	}

	@ModelAttribute("fixedDepositAccount")
	public FixedDeposit createFixedDepositAccountModel() {
		return new FixedDeposit();
	}


	// FIXED DEPOSIT 

	// this.accountNumber = accountNumber;
	// this.maturityPeriod = maturityPeriod;
	// this.interestRate = interestRate;
	// this.maturityAmount = maturityAmount;
	// this.starDate = starDate;
	// this.endDate = endDate;

	//SAVING ACCOUNT

	// private int accountNumber;
	// private double interestRate;
	// private double minimumBalance;
	
    @RequestMapping(value = "/uploadCustomer", method = RequestMethod.POST)
    public String uploadCustomer(
		@ModelAttribute("customer") Customer customer,
		@RequestParam("customerFiles[0].fileData") MultipartFile aadhaarFile,
		@RequestParam("customerFiles[1].fileData") MultipartFile panCardFile,
		@RequestParam("customerFiles[2].fileData") MultipartFile photoFile,
		@RequestParam("customerFiles[3].fileData") MultipartFile signatureFile,
		@RequestParam("accountType") String accountType,
        @RequestParam(value = "balance", required = false) Double balance,
        @RequestParam(value = "currentBalance", required = false) Double currentBalance,
        @RequestParam(value = "fdMaturityPeriod", required = false) Integer maturityPeriod,
        @RequestParam(value = "fdBalance", required = false) Double fdBalance,
        @RequestParam(value = "fdStartDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fdStartDate,
        @RequestParam(value = "fdEndDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fdEndDate,
		@RequestParam(value = "fdMaturityAmount", required = false) Double maturityAmount,
        RedirectAttributes attributes) {

        // Save customer details
        // Convert MultipartFile to byte[] and set it in the File object
        File file = new File();
        try {
            file.setCustomerAadharCard(aadhaarFile.getBytes());
            file.setCustomerPanCard(panCardFile.getBytes());
			file.setCustomerPhoto(photoFile.getBytes());
			file.setCustomerSignature(signatureFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

		int fileId = fileService.generateNextFileId();
		// Setting the file id in the customer and file objects
		customer.setFileId(fileId);
		file.setFileId(fileId);
		customer.setCustomerAccountCreationDate(java.time.LocalDate.now());		

		//adding customer to database
		Customer customer1 = customerService.addCustomer(customer, file);

		// Save account details
		Account account = new Account();
		account.setCustomer(customer1);
		
		System.out.println("Account Type: " + accountType);
		// account.setAccountType("SAVINGS");
		account.setAccountCreationDate(java.time.LocalDate.now());

		System.out.println("Account Type: " + accountType);
		if (accountType.equals("savings")) {
				account.setAccountType("SAVINGS");
				account.setAccountBalance(balance);
				System.out.println("Inside saving account switch case");
				Saving saving = new Saving();
				saving.setAccountNumber(account.getAccountNumber());
				System.out.println("Account Number: " + account.getAccountNumber());
				savingAccountService.addAccount(account, saving);
		}
		else if (accountType.equals("current")) {
			account.setAccountType("CURRENT");
			account.setAccountBalance(currentBalance);
			Current current = new Current();
			current.setAccountNumber(account.getAccountNumber());
			current.setOverDraftBalance(current.getAccountOverdraftLimit());
			currentAccountService.addAccount(account, current);

		}
		else if (accountType.equals("fixed_deposit")) {
			System.out.println("Inside fixed deposit switch case");
			account.setAccountType("FIXEDDEPOSIT");
			account.setAccountBalance(fdBalance);
			FixedDeposit fixedDeposit = new FixedDeposit();
			fixedDeposit.setAccountNumber(account.getAccountNumber());
			fixedDeposit.setMaturityPeriod(maturityPeriod);
			fixedDeposit.setMaturityAmount(maturityAmount);
			fixedDeposit.setStarDate(fdStartDate);
			fixedDeposit.setEndDate(fdEndDate);
			fixedDepositService.addAccount(account, fixedDeposit);
		}
  
		attributes.addFlashAttribute("message", "Success");
        return "redirect:/app/";
    }

	
	@RequestMapping( "/customerdepositmoneycontroller")
    public String depositMoney() {
        return "customermoneydeposit";
    }

	@RequestMapping("/customerwithdrawmoneycontroller")
	public String withdrawMoney(Model model) {		
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("account", new Account());
		return "customermoneywithdraw";
	}

	@RequestMapping("/customertransfermoneycontroller")
	public String transferMoney() {
		// model.addAttribute("account", new Account());
		return "customermoneytransfer";
	}

	@RequestMapping("/customerchequecontroller")
	public String cheque() {
		// model.addAttribute("account", new Account());
		return "customercheque";
	}

	@RequestMapping("/logoutcontroller")
    public String logout(jakarta.servlet.http.HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "frontpage";
    }
	
	@RequestMapping("/customerbackhomepage")
	public String backhomepage() {
		return "customerhomepage";
	}

	@RequestMapping("/validatingdeposit")
	public String validateDeposit(@RequestParam("transactionAccountNumber") int transactionAccountNumber, @RequestParam("transactionAmount") double transactionAmount, RedirectAttributes redirectAttributes) {
			System.out.println("Account Number: " + transactionAccountNumber);
			System.out.println("Amount: " + transactionAmount);
			Account account1 = accountService.getAccountByAccountNumber(transactionAccountNumber);
			
			if (account1 != null) {
				transactionService.deposit(account1, transactionAmount);
				// Add success message to RedirectAttributes
				redirectAttributes.addFlashAttribute("successMessage", "Deposit successful!");
				return "redirect:/app/customerhomepage";
			}
			return "redirect:/app/customerhomepage";
	}

	@RequestMapping("/validatingwithdraw")
	public String validateWithdraw(@RequestParam("transactionAccountNumber") int transactionAccountNumber, @RequestParam("transactionAmount") double transactionAmount, RedirectAttributes redirectAttributes) {
			System.out.println("Account Number: " + transactionAccountNumber);
			System.out.println("Amount: " + transactionAmount);
			Account account1 = accountService.getAccountByAccountNumber(transactionAccountNumber);
			
			if (account1 != null) {
				boolean result =transactionService.withdraw(account1, transactionAmount);

				if (result == false) {
					redirectAttributes.addFlashAttribute("errorMessage", "Withdraw failed! Insufficient balance");
					return "redirect:/app/customerhomepage";
				}
				// Add success message to RedirectAttributes
				redirectAttributes.addFlashAttribute("successMessage", "Withdraw successful!");
				return "redirect:/app/customerhomepage";
			}
			return "redirect:/app/customerhomepage";
	}

	@RequestMapping("/getLast5")
	public String getLast5Transactions(@RequestParam("last5Transaction") int accountNumber, jakarta.servlet.http.HttpServletRequest request) {
		HttpSession session = request.getSession();
		Account account = accountService.getAccountByAccountNumber(accountNumber);
		List<Transaction> transactionList = transactionService.getTransactionByAccountNumber(account);
		List<MoneyTransfer> moneyTransferList = moneyTransferService.getTransactionByAccountNumber(accountNumber);
		List<Cheque> chequeList = chequeService.getAllCheques(Integer.toString(accountNumber), "from_account_number", "('CLEARED','BOUNCED')");
		List<DTO> dtoList = new ArrayList<>();
		for (Transaction transaction : transactionList) {
			DTO dto = new DTO();
			dto.setTransactionId(transaction.getTransactionId());
			dto.setFromAccountNumber(accountNumber);
			dto.setToAccountNumber(-1);
			dto.setTransactionDate(transaction.getTransactionDate());
			dto.setTransactionType(transaction.getTransactionType());
			dto.setAmount(transaction.getAmount());
			dtoList.add(dto);
		}
		for (MoneyTransfer moneyTransfer : moneyTransferList) {
			DTO dto = new DTO();
			dto.setTransactionId(moneyTransfer.getTransaction().getTransactionId());
			dto.setFromAccountNumber(moneyTransfer.getFromAccount().getAccountNumber());
			dto.setToAccountNumber(moneyTransfer.getToAccount().getAccountNumber());
			dto.setTransactionDate(moneyTransfer.getTransactionDate());
			dto.setTransactionType("TRANSFER");
			dto.setAmount(moneyTransfer.getAmount());
			dtoList.add(dto);
		}
		for (Cheque cheque : chequeList) {
			DTO dto = new DTO();
			dto.setTransactionId(cheque.getChequeNumber());
			dto.setFromAccountNumber(cheque.getFromAccountNumber());
			dto.setToAccountNumber(cheque.getToAccountNumber());
			dto.setTransactionDate(cheque.getChequeClearanceDate());
			if (cheque.getChequeStatus().equals("BOUNCED")){ 
				dto.setTransactionType("BOUNCED");
			}
			else {
				dto.setTransactionType("CHEQUETRANSFER");
			}
			dto.setAmount(cheque.getAmount());
			dtoList.add(dto);
		}
		//sort according to date latest to oldest 
		dtoList.sort((DTO d1, DTO d2) -> {
			// First, compare by transaction date (earliest to latest)
			int dateComparison = 0 ;
			System.out.println(d1.getTransactionDate());
			System.out.println(d2.getTransactionDate());
			if (d1.getTransactionDate() != null && d2.getTransactionDate() != null){
				dateComparison = d2.getTransactionDate().compareTo(d1.getTransactionDate());
			}

			// If dates are equal, compare by transaction ID (higher to lower)
			if (dateComparison == 0) {
				return Integer.compare(d2.getTransactionId(), d1.getTransactionId());
			}
			
			return dateComparison;
		});

		session.setAttribute("dtolist", dtoList);

		return "customerhomepage";
	}

	@RequestMapping("/getbalance")
	public String getBalance(@RequestParam("accountNumber1") int accountNumber, jakarta.servlet.http.HttpServletRequest request) {
		HttpSession session = request.getSession();
		Account account = accountService.getAccountByAccountNumber(accountNumber);
		session.setAttribute("accountbalance1", account.getAccountBalance());
		if ( account.getAccountType().equals("CURRENT")) {
			Current current = currentAccountService.getCurrentByAccountNumber(accountNumber);
			session.setAttribute("overdraftbalance", current.getOverDraftBalance());
		}
		return "customerhomepage";
	}

	@RequestMapping("/validatingtransfer")
	public String validateTransfer(@RequestParam("fromAccount") int fromAccountNumber, @RequestParam("toAccount") int toAccountNumber, @RequestParam("transferAmount") double transactionAmount, RedirectAttributes redirectAttributes) {
			System.out.println("From Account Number: " + fromAccountNumber);
			System.out.println("To Account Number: " + toAccountNumber);
			System.out.println("Amount: " + transactionAmount);
			Account toAccount = null;
			Account fromAccount = accountService.getAccountByAccountNumber(fromAccountNumber);
			try {
				 toAccount = accountService.getAccountByAccountNumber(toAccountNumber);
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage", "Transfer failed! Account does not exist");
				return "redirect:/app/customerhomepage";
			}			
			if (fromAccount != null && toAccount != null) {
				boolean result = moneyTransferService.transferMoney(fromAccountNumber, toAccountNumber, transactionAmount);
				if (result == false) {
					redirectAttributes.addFlashAttribute("errorMessage", "Transfer failed! Insufficient balance");
					return "redirect:/app/customerhomepage";
				}
				// Add success message to RedirectAttributes
				redirectAttributes.addFlashAttribute("successMessage", "Transfer successful!");
				return "redirect:/app/customerhomepage";
			}
			return "redirect:/app/customerhomepage";
	}


	@RequestMapping("/pendingchequescontroller")
	public String pendingCheques(jakarta.servlet.http.HttpServletRequest request) {
		HttpSession session = request.getSession();
		//retriting accounlist 
		List<Account> accountList = (List<Account>) session.getAttribute("accountlist");
		System.out.println("Account List: " + accountList);
		//Converting to string separated with commas
		String accountNumbers = "";
		for (Account account : accountList) {
			accountNumbers += "'"+ Integer.toString(account.getAccountNumber()) + "',";
		}
		accountNumbers = accountNumbers.substring(0, accountNumbers.length() - 1);
		System.out.println("Account Numbers: " + accountNumbers);
		List<Cheque> chequeList = chequeService.getAllCheques(accountNumbers, "to_account_number", "('NOTISSUED')");
		session.setAttribute("pendingcheques", chequeList);
		return "customerpendingcheques";
	}

	@RequestMapping("/validatingcheque")
	public String validateCheque(@RequestParam("chequeFromAccountNumber") int chequeFromAccountNumber, @RequestParam("chequeToAccountNumber") int chequeToAccountNumber, @RequestParam("chequeAmount") double chequeAmount, RedirectAttributes redirectAttributes) {
			System.out.println("From Account Number: " + chequeFromAccountNumber);
			System.out.println("To Account Number: " + chequeToAccountNumber);
			System.out.println("Amount: " + chequeAmount);
			Account toAccount = null;
			Account fromAccount = accountService.getAccountByAccountNumber(chequeFromAccountNumber);
			try {
				 toAccount = accountService.getAccountByAccountNumber(chequeToAccountNumber);
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage", "Cheque failed! Account does not exist");
				return "redirect:/app/customerhomepage";
			}			
			if (fromAccount != null && toAccount != null) {
				int chequeNumber = chequeService.getNextChequeNumber();

				boolean result = chequeService.addCheque(chequeNumber, chequeFromAccountNumber, chequeToAccountNumber, chequeAmount);
				if (result == false) {
					redirectAttributes.addFlashAttribute("errorMessage", "Cheque failed! Insufficient balance");
					return "redirect:/app/customerhomepage";
				}
				// Add success message to RedirectAttributes
				redirectAttributes.addFlashAttribute("successMessage", "Cheque successful!");
				return "redirect:/app/customerhomepage";

			}
			return "redirect:/app/customerhomepage";

		}

	@RequestMapping("/approveCheque/{chequeNumber}")
	public String approveCheque(@PathVariable int chequeNumber, RedirectAttributes redirectAttributes) {
		System.out.println("Cheque Number: " + chequeNumber);
		boolean result = chequeService.acceptCheque(chequeNumber);
		if (result == false) {
			redirectAttributes.addFlashAttribute("errorMessage", "Cheque approval failed!");
			return "redirect:/app/customerhomepage";
		}
		// Add success message to RedirectAttributes
		redirectAttributes.addFlashAttribute("successMessage", "Cheque approved!");
		return "redirect:/app/pendingchequescontroller";
	}

	@RequestMapping("/rejectCheque/{chequeNumber}")	
	public String rejectCheque(@PathVariable int chequeNumber, RedirectAttributes redirectAttributes) {
		System.out.println("Cheque Number: " + chequeNumber);
		boolean result = chequeService.rejectCheque(chequeNumber);
		if (result == false) {
			redirectAttributes.addFlashAttribute("errorMessage", "Cheque rejection failed!");
			return "redirect:/app/customerhomepage";
		}
		// Add success message to RedirectAttributes
		redirectAttributes.addFlashAttribute("successMessage", "Cheque rejected!");
		return "redirect:/app/pendingchequescontroller";
	}

	@RequestMapping("/issuedchequescontroller")
	public String issuedCheques(jakarta.servlet.http.HttpServletRequest request) {
		HttpSession session = request.getSession();
		//retriting accounlist 
		List<Account> accountList = (List<Account>) session.getAttribute("accountlist");
		System.out.println("Account List: " + accountList);
		//Converting to string separated with commas
		String accountNumbers = "";
		for (Account account : accountList) {
			accountNumbers += "'"+ Integer.toString(account.getAccountNumber()) + "',";
		}
		accountNumbers = accountNumbers.substring(0, accountNumbers.length() - 1);
		System.out.println("Account Numbers: " + accountNumbers);
		List<Cheque> chequeList = chequeService.getAllCheques(accountNumbers, "from_account_number", "('NOTISSUED', 'ISSUED', 'CLEARED', 'BOUNCED')");
		session.setAttribute("yourcheques", chequeList);
		return "customerissuedcheques";
	}


	@RequestMapping("/createnewaccount")
	public String createNewAccount() {
        // return "customerlogin";
		return "customernewaccount";
	}

	@RequestMapping("/validatingnewaccount")
	public String validateNewAccount(@RequestParam("accountType") String accountType,
	@RequestParam(value = "balance", required = false) Double balance,
	@RequestParam(value = "currentBalance", required = false) Double currentBalance,
	@RequestParam(value = "fdMaturityPeriod", required = false) Integer maturityPeriod,
	@RequestParam(value = "fdBalance", required = false) Double fdBalance,
	@RequestParam(value = "fdStartDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fdStartDate,
	@RequestParam(value = "fdEndDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fdEndDate,
	@RequestParam(value = "fdMaturityAmount", required = false) Double maturityAmount,
	jakarta.servlet.http.HttpServletRequest request,
	RedirectAttributes redirectAttributes) {
		System.out.println("Account Type: " + accountType);	
		Account account = new Account();
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("currentcustomer");
		account.setCustomer(customer);
		account.setAccountCreationDate(java.time.LocalDate.now());
		if (accountType.equals("savings")) {
			account.setAccountType("SAVINGS");
			account.setAccountBalance(balance);
			Saving saving = new Saving();
			saving.setAccountNumber(account.getAccountNumber());
			savingAccountService.addAccount(account, saving);
		}
		else if (accountType.equals("current")) {
			account.setAccountType("CURRENT");
			account.setAccountBalance(currentBalance);
			Current current = new Current();
			current.setAccountNumber(account.getAccountNumber());
			current.setOverDraftBalance(current.getAccountOverdraftLimit());
			currentAccountService.addAccount(account, current);
		}
		else if (accountType.equals("fixed_deposit")) {
			account.setAccountType("FIXEDDEPOSIT");
			account.setAccountBalance(fdBalance);
			FixedDeposit fixedDeposit = new FixedDeposit();
			fixedDeposit.setAccountNumber(account.getAccountNumber());
			fixedDeposit.setMaturityPeriod(maturityPeriod);
			fixedDeposit.setMaturityAmount(maturityAmount);
			fixedDeposit.setStarDate(fdStartDate);
			fixedDeposit.setEndDate(fdEndDate);
			fixedDepositService.addAccount(account, fixedDeposit);
		}
		redirectAttributes.addFlashAttribute("successMessage", "Account created successfully!");
		return "redirect:/app/customerhomepage";
	}
}

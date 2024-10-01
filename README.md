# iNB - Net Bank Online Banking Application

## Overview

iNB (Net Bank) is an online banking application developed for a fictitious bank called Net Bank (NB). This project is a simulation of a real-world software development scenario, allowing trainees to practice the full Software Development Life Cycle (SDLC). The system enables customers to perform common banking operations such as account management, cheque deposits, bill payments, and money transfers, while maintaining ease of use, consistent design, and security.

This project follows Oracle coding standards and best practices, with a final deliverable that includes an installation package for automated deployment.

---

## Features

- **Customer Registration**:  
  Customers can register to open Savings or Current accounts. Once approved by the bank, customers will be notified and gain access to the system.

- **Home Page**:  
  Displays customer account information and balance after login.

- **Account Management**:  
  - **Savings Account**: Earns interest, has configurable withdrawal limits, and minimum balance requirements.
  - **Current Account**: No interest, offers an overdraft facility with daily calculated charges.
  - **Fixed Deposit**: Maturity periods with fixed rates of interest. No withdrawals or deposits allowed.

- **Cheque Deposits**:  
  Customers submit online bank slips, track cheque status (Received, Sent for Clearance, Cleared, Bounced), and manage bounced cheques with automatic fine deductions.

- **Bill Payments**:  
  Pay utility bills (electricity, phone, etc.) from any account, with the option to schedule future payments.

- **Money Transfer**:  
  Transfer funds between iNB user accounts.

- **Security**:  
  Accounts are locked after three consecutive invalid login attempts and can only be reactivated manually.

---

## Installation

The application is delivered as an installation package that automates the deployment process. Ensure that all necessary pre-requisites, including the environment and database setup, are met before installation.

---

## Requirements

- **Backend**: Oracle Database
- **Frontend**: HTML, CSS, JavaScript
- **Backend Framework**: Java Spring MVC
- **Reports**: Jasper Reports
- **Security**: Best practices for secure coding, especially for handling customer data

---

## Directory Structure

```
BankingAppilcation
├─ .metadata
│  ├─ .lock
│  ├─ .log
│  ├─ .mylyn
│  │  ├─ contexts
│  │  └─ repositories.xml.zip
│  ├─ .plugins
│  │  ├─ org.eclipse.buildship.core
│  │  ├─ org.eclipse.core.resources
│  │  │  
├─ BankingApplication
│  ├─ .classpath
│  ├─ .mvn
│  │  └─ wrapper
│  │     └─ maven-wrapper.properties
│  ├─ .project
│  ├─ .settings
│  │  ├─ org.eclipse.core.resources.prefs
│  │  ├─ org.eclipse.jdt.apt.core.prefs
│  │  ├─ org.eclipse.jdt.core.prefs
│  │  └─ org.eclipse.m2e.core.prefs
│  ├─ bin
│  │  ├─ .mvn
│  │  │  └─ wrapper
│  │  │     └─ maven-wrapper.properties
│  │  ├─ .project
│  │  ├─ .settings
│  │  │  └─ org.eclipse.core.resources.prefs
│  │  ├─ HELP.md
│  │  ├─ mvnw
│  │  ├─ mvnw.cmd
│  │  ├─ pom.xml
│  │  ├─ src
│  │  │  ├─ main
│  │  │  │  ├─ java
│  │  │  │  │  └─ com
│  │  │  │  │     └─ mindgate
│  │  │  │  │        └─ main
│  │  │  │  │           └─ BankingApplication.class
│  │  │  │  └─ resources
│  │  │  │     ├─ application.properties
│  │  │  │     └─ templates
│  │  │  └─ test
│  │  │     └─ java
│  │  │        └─ com
│  │  │           └─ mindgate
│  │  │              └─ main
│  │  │                 └─ BankingApplicationTests.class
│  │  └─ target
│  │     └─ classes
│  │        └─ META-INF
│  │           └─ MANIFEST.MF
│  ├─ HELP.md
│  ├─ mvnw
│  ├─ mvnw.cmd
│  ├─ pom.xml
│  ├─ Project_description.txt
│  ├─ src
│  │  ├─ main
│  │  │  ├─ java
│  │  │  │  └─ com
│  │  │  │     └─ mindgate
│  │  │  │        └─ main
│  │  │  │           ├─ BankingApplication.java
│  │  │  │           ├─ configuration
│  │  │  │           │  └─ LoginConfiguration.java
│  │  │  │           ├─ controller
│  │  │  │           │  ├─ CustomerController.java
│  │  │  │           │  └─ LoginController.java
│  │  │  │           ├─ domain
│  │  │  │           │  ├─ Account.java
│  │  │  │           │  ├─ Cheque.java
│  │  │  │           │  ├─ Constants.java
│  │  │  │           │  ├─ Current.java
│  │  │  │           │  ├─ Customer.java
│  │  │  │           │  ├─ DTO.java
│  │  │  │           │  ├─ File.java
│  │  │  │           │  ├─ FixedDeposit.java
│  │  │  │           │  ├─ Login.java
│  │  │  │           │  ├─ MoneyTransfer.java
│  │  │  │           │  ├─ Saving.java
│  │  │  │           │  └─ Transaction.java
│  │  │  │           ├─ exception
│  │  │  │           │  └─ InvalidLoginException.java
│  │  │  │           ├─ repository
│  │  │  │           │  ├─ AccountRepository.java
│  │  │  │           │  ├─ ChequeRepository.java
│  │  │  │           │  ├─ CurrentAccountRepository.java
│  │  │  │           │  ├─ CustomerRepository.java
│  │  │  │           │  ├─ FileRepository.java
│  │  │  │           │  ├─ FixedDepositRepository.java
│  │  │  │           │  ├─ LoginRepository.java
│  │  │  │           │  ├─ MoneyTransferRepository.java
│  │  │  │           │  ├─ SavingAccountRepository.java
│  │  │  │           │  └─ TransactionRepository.java
│  │  │  │           ├─ repositoryimpl
│  │  │  │           │  ├─ AccountRepositoryImpl.java
│  │  │  │           │  ├─ ChequeRepositoryImpl.java
│  │  │  │           │  ├─ CurrentAccountRepositoryImpl.java
│  │  │  │           │  ├─ CustomerRepositoryImpl.java
│  │  │  │           │  ├─ FileRepositoryImpl.java
│  │  │  │           │  ├─ FixedDepositRepositoryImpl.java
│  │  │  │           │  ├─ LoginRepositoryImpl.java
│  │  │  │           │  ├─ moneyTransferRepository.java
│  │  │  │           │  ├─ MoneyTransferRepositoryImpl.java
│  │  │  │           │  ├─ SavingAccountRepositoryImpl.java
│  │  │  │           │  └─ TransactionRepositoryImpl.java
│  │  │  │           ├─ rowmapper
│  │  │  │           │  ├─ AccountRowMapper.java
│  │  │  │           │  ├─ AccountRowMapperr.java
│  │  │  │           │  ├─ ChequeRowMapper.java
│  │  │  │           │  ├─ CurrentAccount.java
│  │  │  │           │  ├─ CurrentAccountRowMapper.java
│  │  │  │           │  ├─ FileRowMapper.java
│  │  │  │           │  ├─ LoginCustomerRowMapper.java
│  │  │  │           │  ├─ LoginRowMapper.java
│  │  │  │           │  ├─ MoneyTransferRowMapper.java
│  │  │  │           │  └─ TransactionRowMapper.java
│  │  │  │           ├─ service
│  │  │  │           │  ├─ AccountService.java
│  │  │  │           │  ├─ ChequeService.java
│  │  │  │           │  ├─ CurrentAccountService.java
│  │  │  │           │  ├─ CustomerService.java
│  │  │  │           │  ├─ FileService.java
│  │  │  │           │  ├─ FixedDepositService.java
│  │  │  │           │  ├─ LoginService.java
│  │  │  │           │  ├─ MoneyTransferService.java
│  │  │  │           │  ├─ SavingAccountService.java
│  │  │  │           │  └─ TransactionService.java
│  │  │  │           ├─ serviceimpl
│  │  │  │           │  ├─ AccountServiceImpl.java
│  │  │  │           │  ├─ ChequeServiceImpl.java
│  │  │  │           │  ├─ CurrentAccountServiceImpl.java
│  │  │  │           │  ├─ CustomerServiceImpl.java
│  │  │  │           │  ├─ FileServiceImpl.java
│  │  │  │           │  ├─ FixedDepositServiceImpl.java
│  │  │  │           │  ├─ LoginServiceImpl.java
│  │  │  │           │  ├─ MoneyTransferServiceImpl.java
│  │  │  │           │  ├─ SavingsAccountServiceImpl.java
│  │  │  │           │  └─ TransactionServiceImpl.java
│  │  │  │           └─ someChnages.zip
│  │  │  ├─ resources
│  │  │  │  ├─ application.properties
│  │  │  │  ├─ static
│  │  │  │  │  ├─ images
│  │  │  │  │  │  ├─ FirstSlide.mp4
│  │  │  │  │  │  ├─ passport-photo.jpg
│  │  │  │  │  │  ├─ SecondSlide.mp4
│  │  │  │  │  │  └─ ThirdSlide.mp4
│  │  │  │  │  └─ Navy Minimalist Business Landing Page Desktop Prototype.png
│  │  │  │  └─ templates
│  │  │  └─ webapp
│  │  │     └─ WEB-INF
│  │  │        └─ views
│  │  │           ├─ adminallcustomers.jsp
│  │  │           ├─ adminblockedcustomers.jsp
│  │  │           ├─ adminclearcheques.jsp
│  │  │           ├─ adminnewaccountapprovals.jsp
│  │  │           ├─ adminunapprovedcustomers.jsp
│  │  │           ├─ backgroud.jpg
│  │  │           ├─ Bank_Logo.jpg
│  │  │           ├─ blocked.jsp
│  │  │           ├─ customercheque.jsp
│  │  │           ├─ CustomerErrorLogin.jsp
│  │  │           ├─ customerhomepage.jsp
│  │  │           ├─ customerissuedcheques.jsp
│  │  │           ├─ customerlogin.jsp
│  │  │           ├─ customermoneydeposit.jsp
│  │  │           ├─ customermoneytransfer.jsp
│  │  │           ├─ customermoneywithdraw.jsp
│  │  │           ├─ customernewaccount.jsp
│  │  │           ├─ customernotapproved.jsp
│  │  │           ├─ customerpendingcheques.jsp
│  │  │           ├─ customerregistration.jsp
│  │  │           ├─ digitalcheque.jsp
│  │  │           ├─ ErrorLogin.jsp
│  │  │           ├─ frontpage.jsp
│  │  │           ├─ homepage.jsp
│  │  │           ├─ images
│  │  │           │  └─ FirstSlide.png
│  │  │           ├─ login.jsp
│  │  │           └─ test.jsp
│  │  └─ test
│  │     └─ java
│  │        └─ com
│  │           └─ mindgate
│  │              └─ main
│  │                 └─ BankingApplicationTests.java
│  └─ target
│     ├─ Banking_Application-0.0.1-SNAPSHOT.jar
│     ├─ Banking_Application-0.0.1-SNAPSHOT.jar.original
│     ├─ classes
│     │  ├─ application.properties
│     │  ├─ com
│     │  │  └─ mindgate
│     │  │     └─ main
│     │  │        ├─ BankingApplication.class
│     │  │        ├─ configuration
│     │  │        │  └─ LoginConfiguration.class
│     │  │        ├─ controller
│     │  │        │  ├─ CustomerController.class
│     │  │        │  └─ LoginController.class
│     │  │        ├─ domain
│     │  │        │  ├─ Account.class
│     │  │        │  ├─ Cheque.class
│     │  │        │  ├─ Constants.class
│     │  │        │  ├─ Current.class
│     │  │        │  ├─ Customer.class
│     │  │        │  ├─ DTO.class
│     │  │        │  ├─ File.class
│     │  │        │  ├─ FixedDeposit.class
│     │  │        │  ├─ Login.class
│     │  │        │  ├─ MoneyTransfer.class
│     │  │        │  ├─ Saving.class
│     │  │        │  └─ Transaction.class
│     │  │        ├─ exception
│     │  │        │  └─ InvalidLoginException.class
│     │  │        ├─ repository
│     │  │        │  ├─ AccountRepository.class
│     │  │        │  ├─ ChequeRepository.class
│     │  │        │  ├─ CurrentAccountRepository.class
│     │  │        │  ├─ CustomerRepository.class
│     │  │        │  ├─ FileRepository.class
│     │  │        │  ├─ FixedDepositRepository.class
│     │  │        │  ├─ LoginRepository.class
│     │  │        │  ├─ MoneyTransferRepository.class
│     │  │        │  ├─ SavingAccountRepository.class
│     │  │        │  └─ TransactionRepository.class
│     │  │        ├─ repositoryimpl
│     │  │        │  ├─ AccountRepositoryImpl.class
│     │  │        │  ├─ ChequeRepositoryImpl.class
│     │  │        │  ├─ CurrentAccountRepositoryImpl.class
│     │  │        │  ├─ CustomerRepositoryImpl.class
│     │  │        │  ├─ FileRepositoryImpl.class
│     │  │        │  ├─ FixedDepositRepositoryImpl.class
│     │  │        │  ├─ LoginRepositoryImpl.class
│     │  │        │  ├─ moneyTransferRepository.class
│     │  │        │  ├─ MoneyTransferRepositoryImpl.class
│     │  │        │  ├─ SavingAccountRepositoryImpl.class
│     │  │        │  └─ TransactionRepositoryImpl.class
│     │  │        ├─ rowmapper
│     │  │        │  ├─ AccountRowMapper.class
│     │  │        │  ├─ AccountRowMapperr.class
│     │  │        │  ├─ ChequeRowMapper.class
│     │  │        │  ├─ CurrentAccount.class
│     │  │        │  ├─ CurrentAccountRowMapper.class
│     │  │        │  ├─ FileRowMapper.class
│     │  │        │  ├─ LoginCustomerRowMapper.class
│     │  │        │  ├─ LoginRowMapper.class
│     │  │        │  ├─ MoneyTransferRowMapper.class
│     │  │        │  └─ TransactionRowMapper.class
│     │  │        ├─ service
│     │  │        │  ├─ AccountService.class
│     │  │        │  ├─ ChequeService.class
│     │  │        │  ├─ CurrentAccountService.class
│     │  │        │  ├─ CustomerService.class
│     │  │        │  ├─ FileService.class
│     │  │        │  ├─ FixedDepositService.class
│     │  │        │  ├─ LoginService.class
│     │  │        │  ├─ MoneyTransferService.class
│     │  │        │  ├─ SavingAccountService.class
│     │  │        │  └─ TransactionService.class
│     │  │        ├─ serviceimpl
│     │  │        │  ├─ AccountServiceImpl.class
│     │  │        │  ├─ ChequeServiceImpl.class
│     │  │        │  ├─ CurrentAccountServiceImpl.class
│     │  │        │  ├─ CustomerServiceImpl.class
│     │  │        │  ├─ FileServiceImpl.class
│     │  │        │  ├─ FixedDepositServiceImpl.class
│     │  │        │  ├─ LoginServiceImpl.class
│     │  │        │  ├─ MoneyTransferServiceImpl.class
│     │  │        │  ├─ SavingsAccountServiceImpl.class
│     │  │        │  └─ TransactionServiceImpl.class
│     │  │        └─ someChnages.zip
│     │  ├─ META-INF
│     │  │  ├─ MANIFEST.MF
│     │  │  └─ maven
│     │  │     └─ com.mindgate.main
│     │  │        └─ Banking_Application
│     │  │           ├─ pom.properties
│     │  │           └─ pom.xml
│     │  └─ static
│     │     ├─ images
│     │     │  ├─ FirstSlide.mp4
│     │     │  ├─ passport-photo.jpg
│     │     │  ├─ SecondSlide.mp4
│     │     │  └─ ThirdSlide.mp4
│     │     └─ Navy Minimalist Business Landing Page Desktop Prototype.png
│     ├─ generated-sources
│     │  └─ annotations
│     ├─ generated-test-sources
│     │  └─ test-annotations
│     ├─ maven-archiver
│     │  └─ pom.properties
│     ├─ maven-status
│     │  └─ maven-compiler-plugin
│     │     ├─ compile
│     │     │  └─ default-compile
│     │     │     ├─ createdFiles.lst
│     │     │     └─ inputFiles.lst
│     │     └─ testCompile
│     │        └─ default-testCompile
│     │           ├─ createdFiles.lst
│     │           └─ inputFiles.lst
│     ├─ surefire-reports
│     │  ├─ com.mindgate.main.BankingApplicationTests.txt
│     │  └─ TEST-com.mindgate.main.BankingApplicationTests.xml
│     └─ test-classes
│        └─ com
│           └─ mindgate
│              └─ main
│                 └─ BankingApplicationTests.class
├─ Banking_Application
│  ├─ .classpath
│  ├─ .mvn
│  │  └─ wrapper
│  │     └─ maven-wrapper.properties
│  ├─ .project
│  ├─ .settings
│  │  ├─ org.eclipse.core.resources.prefs
│  │  ├─ org.eclipse.jdt.apt.core.prefs
│  │  ├─ org.eclipse.jdt.core.prefs
│  │  └─ org.eclipse.m2e.core.prefs
│  ├─ bin
│  │  ├─ .mvn
│  │  │  └─ wrapper
│  │  │     └─ maven-wrapper.properties
│  │  ├─ .project
│  │  ├─ .settings
│  │  │  └─ org.eclipse.core.resources.prefs
│  │  ├─ HELP.md
│  │  ├─ mvnw
│  │  ├─ mvnw.cmd
│  │  ├─ pom.xml
│  │  ├─ src
│  │  │  ├─ main
│  │  │  │  ├─ java
│  │  │  │  │  └─ com
│  │  │  │  │     └─ mindgate
│  │  │  │  │        └─ main
│  │  │  │  │           └─ BankingApplication.class
│  │  │  │  └─ resources
│  │  │  │     ├─ application.properties
│  │  │  │     └─ templates
│  │  │  └─ test
│  │  │     └─ java
│  │  │        └─ com
│  │  │           └─ mindgate
│  │  │              └─ main
│  │  │                 └─ BankingApplicationTests.class
│  │  └─ target
│  │     └─ classes
│  │        └─ META-INF
│  │           └─ MANIFEST.MF
│  ├─ HELP.md
│  ├─ mvnw
│  ├─ mvnw.cmd
│  ├─ pom.xml
│  ├─ src
│  │  ├─ main
│  │  │  ├─ java
│  │  │  │  └─ com
│  │  │  │     └─ mindgate
│  │  │  │        └─ main
│  │  │  │           ├─ BankingApplication.java
│  │  │  │           ├─ configuration
│  │  │  │           │  └─ LoginConfiguration.java
│  │  │  │           ├─ controller
│  │  │  │           │  ├─ CustomerController.java
│  │  │  │           │  └─ LoginController.java
│  │  │  │           ├─ domain
│  │  │  │           │  ├─ Account.java
│  │  │  │           │  ├─ Cheque.java
│  │  │  │           │  ├─ Constants.java
│  │  │  │           │  ├─ Current.java
│  │  │  │           │  ├─ Customer.java
│  │  │  │           │  ├─ DTO.java
│  │  │  │           │  ├─ File.java
│  │  │  │           │  ├─ FixedDeposit.java
│  │  │  │           │  ├─ Login.java
│  │  │  │           │  ├─ MoneyTransfer.java
│  │  │  │           │  ├─ Saving.java
│  │  │  │           │  └─ Transaction.java
│  │  │  │           ├─ exception
│  │  │  │           │  └─ InvalidLoginException.java
│  │  │  │           ├─ repository
│  │  │  │           │  ├─ AccountRepository.java
│  │  │  │           │  ├─ ChequeRepository.java
│  │  │  │           │  ├─ CurrentAccountRepository.java
│  │  │  │           │  ├─ CustomerRepository.java
│  │  │  │           │  ├─ FileRepository.java
│  │  │  │           │  ├─ FixedDepositRepository.java
│  │  │  │           │  ├─ LoginRepository.java
│  │  │  │           │  ├─ MoneyTransferRepository.java
│  │  │  │           │  ├─ SavingAccountRepository.java
│  │  │  │           │  └─ TransactionRepository.java
│  │  │  │           ├─ repositoryimpl
│  │  │  │           │  ├─ AccountRepositoryImpl.java
│  │  │  │           │  ├─ ChequeRepositoryImpl.java
│  │  │  │           │  ├─ CurrentAccountRepositoryImpl.java
│  │  │  │           │  ├─ CustomerRepositoryImpl.java
│  │  │  │           │  ├─ FileRepositoryImpl.java
│  │  │  │           │  ├─ FixedDepositRepositoryImpl.java
│  │  │  │           │  ├─ LoginRepositoryImpl.java
│  │  │  │           │  ├─ moneyTransferRepository.java
│  │  │  │           │  ├─ MoneyTransferRepositoryImpl.java
│  │  │  │           │  ├─ SavingAccountRepositoryImpl.java
│  │  │  │           │  └─ TransactionRepositoryImpl.java
│  │  │  │           ├─ rowmapper
│  │  │  │           │  ├─ AccountRowMapper.java
│  │  │  │           │  ├─ AccountRowMapperr.java
│  │  │  │           │  ├─ ChequeRowMapper.java
│  │  │  │           │  ├─ CurrentAccount.java
│  │  │  │           │  ├─ CurrentAccountRowMapper.java
│  │  │  │           │  ├─ FileRowMapper.java
│  │  │  │           │  ├─ LoginCustomerRowMapper.java
│  │  │  │           │  ├─ LoginRowMapper.java
│  │  │  │           │  ├─ MoneyTransferRowMapper.java
│  │  │  │           │  └─ TransactionRowMapper.java
│  │  │  │           ├─ service
│  │  │  │           │  ├─ AccountService.java
│  │  │  │           │  ├─ ChequeService.java
│  │  │  │           │  ├─ CurrentAccountService.java
│  │  │  │           │  ├─ CustomerService.java
│  │  │  │           │  ├─ FileService.java
│  │  │  │           │  ├─ FixedDepositService.java
│  │  │  │           │  ├─ LoginService.java
│  │  │  │           │  ├─ MoneyTransferService.java
│  │  │  │           │  ├─ SavingAccountService.java
│  │  │  │           │  └─ TransactionService.java
│  │  │  │           ├─ serviceimpl
│  │  │  │           │  ├─ AccountServiceImpl.java
│  │  │  │           │  ├─ ChequeServiceImpl.java
│  │  │  │           │  ├─ CurrentAccountServiceImpl.java
│  │  │  │           │  ├─ CustomerServiceImpl.java
│  │  │  │           │  ├─ FileServiceImpl.java
│  │  │  │           │  ├─ FixedDepositServiceImpl.java
│  │  │  │           │  ├─ LoginServiceImpl.java
│  │  │  │           │  ├─ MoneyTransferServiceImpl.java
│  │  │  │           │  ├─ SavingsAccountServiceImpl.java
│  │  │  │           │  └─ TransactionServiceImpl.java
│  │  │  │           └─ someChnages.zip
│  │  │  ├─ resources
│  │  │  │  ├─ application.properties
│  │  │  │  ├─ static
│  │  │  │  │  ├─ images
│  │  │  │  │  │  ├─ FirstSlide.mp4
│  │  │  │  │  │  ├─ passport-photo.jpg
│  │  │  │  │  │  ├─ SecondSlide.mp4
│  │  │  │  │  │  └─ ThirdSlide.mp4
│  │  │  │  │  └─ Navy Minimalist Business Landing Page Desktop Prototype.png
│  │  │  │  └─ templates
│  │  │  └─ webapp
│  │  │     └─ WEB-INF
│  │  │        └─ views
│  │  │           ├─ adminallcustomers.jsp
│  │  │           ├─ adminblockedcustomers.jsp
│  │  │           ├─ adminclearcheques.jsp
│  │  │           ├─ adminnewaccountapprovals.jsp
│  │  │           ├─ adminunapprovedcustomers.jsp
│  │  │           ├─ backgroud.jpg
│  │  │           ├─ Bank_Logo.jpg
│  │  │           ├─ blocked.jsp
│  │  │           ├─ customercheque.jsp
│  │  │           ├─ CustomerErrorLogin.jsp
│  │  │           ├─ customerhomepage.jsp
│  │  │           ├─ customerissuedcheques.jsp
│  │  │           ├─ customerlogin.jsp
│  │  │           ├─ customermoneydeposit.jsp
│  │  │           ├─ customermoneytransfer.jsp
│  │  │           ├─ customermoneywithdraw.jsp
│  │  │           ├─ customernewaccount.jsp
│  │  │           ├─ customernotapproved.jsp
│  │  │           ├─ customerpendingcheques.jsp
│  │  │           ├─ customerregistration.jsp
│  │  │           ├─ digitalcheque.jsp
│  │  │           ├─ ErrorLogin.jsp
│  │  │           ├─ frontpage.jsp
│  │  │           ├─ homepage.jsp
│  │  │           ├─ images
│  │  │           │  └─ FirstSlide.png
│  │  │           ├─ login.jsp
│  │  │           └─ test.jsp
│  │  └─ test
│  │     └─ java
│  │        └─ com
│  │           └─ mindgate
│  │              └─ main
│  │                 └─ BankingApplicationTests.java
│  └─ target
│     ├─ Banking_Application-0.0.1-SNAPSHOT.jar
│     ├─ Banking_Application-0.0.1-SNAPSHOT.jar.original
│     ├─ classes
│     │  ├─ application.properties
│     │  ├─ com
│     │  │  └─ mindgate
│     │  │     └─ main
│     │  │        ├─ BankingApplication.class
│     │  │        ├─ configuration
│     │  │        │  └─ LoginConfiguration.class
│     │  │        ├─ controller
│     │  │        │  ├─ CustomerController.class
│     │  │        │  └─ LoginController.class
│     │  │        ├─ domain
│     │  │        │  ├─ Account.class
│     │  │        │  ├─ Cheque.class
│     │  │        │  ├─ Constants.class
│     │  │        │  ├─ Current.class
│     │  │        │  ├─ Customer.class
│     │  │        │  ├─ DTO.class
│     │  │        │  ├─ File.class
│     │  │        │  ├─ FixedDeposit.class
│     │  │        │  ├─ Login.class
│     │  │        │  ├─ MoneyTransfer.class
│     │  │        │  ├─ Saving.class
│     │  │        │  └─ Transaction.class
│     │  │        ├─ exception
│     │  │        │  └─ InvalidLoginException.class
│     │  │        ├─ repository
│     │  │        │  ├─ AccountRepository.class
│     │  │        │  ├─ ChequeRepository.class
│     │  │        │  ├─ CurrentAccountRepository.class
│     │  │        │  ├─ CustomerRepository.class
│     │  │        │  ├─ FileRepository.class
│     │  │        │  ├─ FixedDepositRepository.class
│     │  │        │  ├─ LoginRepository.class
│     │  │        │  ├─ MoneyTransferRepository.class
│     │  │        │  ├─ SavingAccountRepository.class
│     │  │        │  └─ TransactionRepository.class
│     │  │        ├─ repositoryimpl
│     │  │        │  ├─ AccountRepositoryImpl.class
│     │  │        │  ├─ ChequeRepositoryImpl.class
│     │  │        │  ├─ CurrentAccountRepositoryImpl.class
│     │  │        │  ├─ CustomerRepositoryImpl.class
│     │  │        │  ├─ FileRepositoryImpl.class
│     │  │        │  ├─ FixedDepositRepositoryImpl.class
│     │  │        │  ├─ LoginRepositoryImpl.class
│     │  │        │  ├─ moneyTransferRepository.class
│     │  │        │  ├─ MoneyTransferRepositoryImpl.class
│     │  │        │  ├─ SavingAccountRepositoryImpl.class
│     │  │        │  └─ TransactionRepositoryImpl.class
│     │  │        ├─ rowmapper
│     │  │        │  ├─ AccountRowMapper.class
│     │  │        │  ├─ AccountRowMapperr.class
│     │  │        │  ├─ ChequeRowMapper.class
│     │  │        │  ├─ CurrentAccount.class
│     │  │        │  ├─ CurrentAccountRowMapper.class
│     │  │        │  ├─ FileRowMapper.class
│     │  │        │  ├─ LoginCustomerRowMapper.class
│     │  │        │  ├─ LoginRowMapper.class
│     │  │        │  ├─ MoneyTransferRowMapper.class
│     │  │        │  └─ TransactionRowMapper.class
│     │  │        ├─ service
│     │  │        │  ├─ AccountService.class
│     │  │        │  ├─ ChequeService.class
│     │  │        │  ├─ CurrentAccountService.class
│     │  │        │  ├─ CustomerService.class
│     │  │        │  ├─ FileService.class
│     │  │        │  ├─ FixedDepositService.class
│     │  │        │  ├─ LoginService.class
│     │  │        │  ├─ MoneyTransferService.class
│     │  │        │  ├─ SavingAccountService.class
│     │  │        │  └─ TransactionService.class
│     │  │        ├─ serviceimpl
│     │  │        │  ├─ AccountServiceImpl.class
│     │  │        │  ├─ ChequeServiceImpl.class
│     │  │        │  ├─ CurrentAccountServiceImpl.class
│     │  │        │  ├─ CustomerServiceImpl.class
│     │  │        │  ├─ FileServiceImpl.class
│     │  │        │  ├─ FixedDepositServiceImpl.class
│     │  │        │  ├─ LoginServiceImpl.class
│     │  │        │  ├─ MoneyTransferServiceImpl.class
│     │  │        │  ├─ SavingsAccountServiceImpl.class
│     │  │        │  └─ TransactionServiceImpl.class
│     │  │        └─ someChnages.zip
│     │  ├─ META-INF
│     │  │  ├─ MANIFEST.MF
│     │  │  └─ maven
│     │  │     └─ com.mindgate.main
│     │  │        └─ Banking_Application
│     │  │           ├─ pom.properties
│     │  │           └─ pom.xml
│     │  └─ static
│     │     ├─ images
│     │     │  ├─ FirstSlide.mp4
│     │     │  ├─ passport-photo.jpg
│     │     │  ├─ SecondSlide.mp4
│     │     │  └─ ThirdSlide.mp4
│     │     └─ Navy Minimalist Business Landing Page Desktop Prototype.png
│     ├─ generated-sources
│     │  └─ annotations
│     ├─ generated-test-sources
│     │  └─ test-annotations
│     ├─ maven-archiver
│     │  └─ pom.properties
│     ├─ maven-status
│     │  └─ maven-compiler-plugin
│     │     ├─ compile
│     │     │  └─ default-compile
│     │     │     ├─ createdFiles.lst
│     │     │     └─ inputFiles.lst
│     │     └─ testCompile
│     │        └─ default-testCompile
│     │           ├─ createdFiles.lst
│     │           └─ inputFiles.lst
│     ├─ surefire-reports
│     │  ├─ com.mindgate.main.BankingApplicationTests.txt
│     │  └─ TEST-com.mindgate.main.BankingApplicationTests.xml
│     └─ test-classes
│        └─ com
│           └─ mindgate
│              └─ main
│                 └─ BankingApplicationTests.class
└─ Project_description.txt
```

package org.example.service;

import org.example.dao.ApplicationDao;
import org.example.dao.CustomerDao;
import org.example.dao.DaoFactory;
import org.example.dao.DaoFactory1;
import org.example.entity.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class ApplicationService {

    public static void insert() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first and last name as it appears on drivers license: ");
        String customerName = scanner.nextLine() + " PENDING APPROVAL";

        String yearJoined = String.valueOf(LocalDateTime.now());
        System.out.println("Please enter your initial deposit required to open the account: ");
        float accountBalance = scanner.nextFloat();

        if ( accountBalance <= 0){
            System.out.println("ERROR INVALID DEPOSIT AMOUNT: Deposit must be great than $0");

        }
        else {
            Customer customer = new Customer(customerName, yearJoined, accountBalance);

            ApplicationDao applicationDao = DaoFactory1.getApplicationDao();
            applicationDao.insert(customer);
            System.out.println("ACCOUNT PENDING APPROVAL -->  " + customer.toString());
        }

    }

    public static void accept() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first and last name: ");
        String customerName = scanner.nextLine() + " PENDING APPROVAL";
        String yearJoined = String.valueOf(LocalDateTime.now());

        float accountBalance = 0;
        Customer customer = new Customer(customerName, yearJoined, accountBalance);

        ApplicationDao applicationDao = DaoFactory1.getApplicationDao();
        applicationDao.insert(customer);
        System.out.println("ACCOUNT PENDING APPROVAL -->  " + customer.toString());
        System.out.println("");
        System.out.println("You can begin making deposits after your account has been approved.");


    }

    public static void getCustomerById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Log in with your customer Id# number: ");
        int id = scanner.nextInt();

        ApplicationDao applicationDao = DaoFactory1.getApplicationDao();
        Customer customer = applicationDao.getCustomerById(id);

        System.out.println("Customer Id: #"+id +" your account balance is: $" + customer.getAccountBalance());

    }

    public static void update() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerFloat = new Scanner(System.in);

        System.out.println("Log in with your customer Id#: ");
        int id = scannerInt.nextInt();


        System.out.println("Select option 1 or 2: ");
        System.out.println("1: Deposit");
        System.out.println("2: Withdraw");
        int selection = scannerInt.nextInt();


        if ( selection == 1) {
            System.out.println("How much will you be depositing today");
            float deposit = scannerFloat.nextFloat();

            ApplicationDao applicationDao = DaoFactory1.getApplicationDao();
            Customer customer = applicationDao.getCustomerById(id);
            if(deposit > 0) {
                float accountBalance = deposit + customer.getAccountBalance();

                System.out.println("Your new account balance is: " + accountBalance);

                Customer customer1 = new Customer(id, accountBalance);
                applicationDao.update(customer1);
            }
            else {
                System.out.println("ERROR INVALID DEPOSIT: deposit must be great than $0");
            }

            }
        else if( selection == 2) {
            System.out.println("How much with you be withdrawing today?");
            float withdraw = scannerFloat.nextFloat();

            ApplicationDao applicationDao = DaoFactory1.getApplicationDao();
            Customer customer = applicationDao.getCustomerById(id);

            if(withdraw < customer.getAccountBalance()) {
                float accountBalance = customer.getAccountBalance() - withdraw;

                System.out.println("Your remaining account balance is: " + accountBalance);

                Customer customer1 = new Customer(id, accountBalance);
                applicationDao.update(customer1);
            }
            else {
                System.out.println("ERROR INSUFFICIENT FUNDS: withdraw amount not available in account");
            }
        }
        else {
            System.out.println("INVALID ENTRY ERROR: Please enter 1 or 2");

            }

    }

    public static void transfer() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerFloat = new Scanner(System.in);

        System.out.println("Log in with your customer Id#: ");
        int id = scannerInt.nextInt();
        ApplicationDao applicationDao = DaoFactory1.getApplicationDao();
        Customer customer = applicationDao.getCustomerById(id);


        System.out.println("What account Id# will you be transferring to today?");
        int idTransfer = scannerInt.nextInt();
        ApplicationDao applicationDao1 = DaoFactory1.getApplicationDao();
        Customer customer1 = applicationDao.getCustomerById(idTransfer);

        System.out.println("How much will you be transferring today?");
        float transfer = scannerFloat.nextFloat();
        if (transfer < customer.getAccountBalance()) {
            float accountBalance = customer1.getAccountBalance() + transfer;
            float accountBalance2 = customer.getAccountBalance() - transfer;

            Customer customer2 = new Customer(idTransfer, accountBalance);
            applicationDao1.transfer(customer2);
            Customer customer3 = new Customer(id, accountBalance2);
            applicationDao.transfer(customer3);

        }
        else {
            System.out.println("ERROR INSUFFICIENT FUNDS: transfer amount not available in account");
        }
        }
    public static void transferAccept() {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerFloat = new Scanner(System.in);

        System.out.println("Log in with your customer Id#: ");
        int id = scannerInt.nextInt();
        ApplicationDao applicationDao = DaoFactory1.getApplicationDao();
        Customer customer = applicationDao.getCustomerById(id);


        System.out.println("What account Id# will you be receiving a transfer from?");
        int idTransfer = scannerInt.nextInt();
        ApplicationDao applicationDao1 = DaoFactory1.getApplicationDao();
        Customer customer1 = applicationDao.getCustomerById(idTransfer);

        System.out.println("How much will the transfer be for?");
        float transfer = scannerFloat.nextFloat();
        if (transfer < customer1.getAccountBalance()) {
            float accountBalance = customer1.getAccountBalance() - transfer;
            float accountBalance2 = customer.getAccountBalance() + transfer;

            Customer customer2 = new Customer(idTransfer, accountBalance);
            applicationDao1.transfer(customer2);
            Customer customer3 = new Customer(id, accountBalance2);
            applicationDao.transfer(customer3);

        }
        else {
            System.out.println("ERROR INSUFFICIENT FUNDS: transferee does not have required funds");
        }
    }
    }


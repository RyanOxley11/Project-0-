package org.example;

import org.example.service.ApplicationService;
import org.example.service.CustomerService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        boolean flag4 = true;
        while (flag4) {
            System.out.println("Welcome to Ryan's World Bank");
            System.out.println("Select an option 1-3: ");
            System.out.println("1: Enter 1 for Employee");
            System.out.println("2: Enter 2 for Customer");
            System.out.println("3: Enter 3 for New User");
            System.out.println("4: Exit Program");
            Scanner scanner1 = new Scanner(System.in);
            int flag1 = scanner1.nextInt();
            switch (flag1) {
                case 1:

                    Scanner scanner = new Scanner(System.in);
                    boolean flag = true;
                    while (flag) {
                        System.out.println("Select an option 1-9: ");
                        System.out.println("1: Add New Customer");
                        System.out.println("2: Get Account Balance by ID");
                        System.out.println("3: Get Account Balance by Name");
                        System.out.println("4: Get All Customers");
                        System.out.println("5: Get All Transactions");
                        System.out.println("6: Approve Customer");
                        System.out.println("7: Update Customer");
                        System.out.println("8: Delete Customer");
                        System.out.println("9: Return to Main Menu");


                        int choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                CustomerService.insertCustomer();
                                break;
                            case 2:
                                CustomerService.getCustomerById();
                                break;
                            case 3:
                                CustomerService.getCustomerByName();
                                break;
                            case 4:
                                CustomerService.getAllCustomers();
                                break;
                            case 5:
                                CustomerService.getAllTransactions();
                                break;
                            case 6:
                                CustomerService.approve();
                                break;
                            case 7:
                                CustomerService.updateCustomer();
                                break;
                            case 8:
                                CustomerService.deleteCustomer();
                                break;
                            case 9:
                                System.out.println("You've exited the program.");
                                flag = false;
                                break;
                            default:
                                System.out.println("Please enter 1-7");
                        }
                        System.out.println();
                    }
                    break;

                case 2:

                    boolean flag2 = true;
                    while (flag2) {
                        System.out.println("Select an option 1-6: ");
                        System.out.println("1: Apply for Account");
                        System.out.println("2: View Account Balance");
                        System.out.println("3: Deposit/Withdraw");
                        System.out.println("4: Transfer Funds");
                        System.out.println("5: Receive Transfer");
                        System.out.println("6: Return to Main Menu");
                        int choice1 = scanner1.nextInt();

                        switch (choice1) {
                            case 1:
                                ApplicationService.insert();
                                //flag2 = false;
                                break;
                            case 2:
                                ApplicationService.getCustomerById();
                                //flag2 = false;
                                break;
                            case 3:
                                ApplicationService.update();
                                //flag2 = false;
                                break;
                            case 4:
                                ApplicationService.transfer();
                                break;
                            case 5:
                                ApplicationService.transferAccept();
                                break;
                            case 6:
                                flag2 = false;
                                System.out.println("");
                                System.out.println("You've exited the program.");
                                break;
                            default:
                                System.out.println("");
                                System.out.println("INVALID ENTRY ERROR: Please enter 1-4");


                        }
                        System.out.println("");
                    }
                    System.out.println("Thank you for doing your banking here at Ryan's World Bank! ");
                    System.out.println("We hope you have a wonderful day! ");
                    System.out.println("");
                    break;

                case 3:
                    boolean flag3 = true;
                    while (flag3) {
                        System.out.println("Select an option 1-2: ");
                        System.out.println("1: Apply for Customer Account");
                        System.out.println("2: Exit Program");
                        int choice1 = scanner1.nextInt();

                        switch (choice1) {
                            case 1:
                                ApplicationService.accept();
                                break;

                            case 2:
                                flag3 = false;
                                System.out.println("");
                                System.out.println("You've exited the program.");
                                break;
                            default:
                                System.out.println("");
                                System.out.println("INVALID ENTRY ERROR: Please enter 1-2");


                        }
                        System.out.println("");
                    }

                case 4:
                    flag4 = false;
                    System.out.println("Thank you for doing your banking here at Ryan's World Bank! ");
                    System.out.println("We hope you have a wonderful day! ");
                    System.out.println("");
                    break;

                default:
                    System.out.println("");
                    System.out.println("INVALID ENTRY ERROR: Please enter 1-4");
                }

            }


        }
    }




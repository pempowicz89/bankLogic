package ejilxq9.logic;

import java.util.ArrayList;
/**
 * bank-logic klasssen
 *
 * @author Jens Lindström
 * @email ejilxq-9@studera.ltu.se
 * @course D0018D Objekorienterad programmering
 */

public class BankLogic {

    private int accountNumber = 1001;
    private ArrayList<Customer> customerList = new ArrayList<>();

        //Hämtar alla kunder med customerList
    public ArrayList<String> getAllCustomers() {
        if (customerList.size() > 0) {
            System.out.println(customerList.toString());
        }
        ArrayList<String> allCustomers = new ArrayList<>();
        for (Customer customer : customerList) {
            allCustomers.add(customer.toString());
        }
        return allCustomers;
    }
        //Skapar nya kunder
    public boolean createCustomer(String name, String surName, String pNr)
    {
        boolean create = true;
        for (Customer customer : customerList) {

            if (customer.getpNr().equals(pNr)) {
                create = false;
                break;
            }
        }

        if(create) {
            customerList.add(new Customer(name, surName, pNr));
            create = true;
        }

        return create;
    }
            //Skapar ett savingaccount
    public int createSavingsAccount(String pNr) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                // Kunden finns
                SavingsAccount account = new SavingsAccount(0, 1.0, "savings", accountNumber);
                accountNumber++;
                // Kontot skapat, lägg till hos kund
                customer.addAccount(account);
                // Ta ut kontonummer, returna det
                int accountNbr = account.getAccountNumber();
                return accountNbr;
            }
        }
        // Ingen kund fanns med det personnummret fanns, returnera -1
        return -1;
    }
            //hämtar alla konton hos specifika kunder
    public String getAccount(String pNr, int accountId) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                // kunden fanns, kolla konton
                for (SavingsAccount account : customer.getCustomerAccounts()) {
                    if (account.getAccountNumber() == accountId) {
                        // Kontot fanns, returnera toString()
                        return account.toString();
                    }
                }
                // kontot fanns inte, returnera
                return null;
            }
        }
        // ingen kund med det personnumret, returnera null
        return null;
    }

    public ArrayList<String> getCustomer(String pNr) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                //kunden finns
                String customerNameAndPNr = customer.toString();
                // f�r varje konto, lägg till "kontonummer amount kontotyp ränta"
                ArrayList<String> customerData = new ArrayList<String>();
                customerData.add(customerNameAndPNr); // Index 0 tillagd i listan

                // l�gg till info om alla kundens konton
                for (SavingsAccount account : customer.getCustomerAccounts()) {
                    customerData.add(account.toString());
                }
                return customerData;
            }
        }
        return null;
    }
            //ändrar specifika kunders namn.
    public boolean changeCustomerName(String name, String surname, String pNr) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                customer.setName(name);
                customer.setSurname(surname);
                return true;
            }
        }
            //om man inte ändrar på förnamn, efternamn eller personnummer så returnerar den inget
        return false;
    }

            //stänger specifika kunders konto
    public ArrayList<Customer> closeAccount(String pNr, int accountId) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                ArrayList<SavingsAccount> customerAccounts = customer.getCustomerAccounts();
                for (SavingsAccount customerAccount : customerAccounts) {
                    if (accountId == customerAccount.getAccountNumber()) {

                        return customerList;
                    }
                }

            }

        }

        return null;
    }
            //raderar kunderna
    public String deleteCustomer(String pNr) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                ArrayList<String> customerData = getCustomer(pNr);
                customerList.remove(customer);


                return customerData.toString();
            }
        }

        return null;
    }
            //sätter in pengar på kundernas specifika konto
    public boolean deposit(String pNr, int accountId, double amount) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                ArrayList<SavingsAccount> customerAccounts = customer.getCustomerAccounts();
                for (SavingsAccount customerAccount : customerAccounts) {
                    if (accountId == customerAccount.getAccountNumber()) {

                        customerAccount.setAmount(customerAccount.getAmount()+amount);

                        return true;
                    }

                }
            }

        }
        return false;

    }
            //tar ut pengar från kundernas specifika konton
    public boolean withdraw(String pNr, int accountId, double amount) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNr())) {
                ArrayList<SavingsAccount> customerAccounts = customer.getCustomerAccounts();
                for (SavingsAccount customerAccount : customerAccounts) {
                    if (accountId == customerAccount.getAccountNumber()) {
                        if (amount <= customerAccount.getAmount()){
                            customerAccount.setAmount(customerAccount.getAmount()-amount);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
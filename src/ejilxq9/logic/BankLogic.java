package ejilxq9.logic;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Huvudklassen som innehåller alla klasser, Customer och Accountyper så som SavinAccount.
 *
 * @author Jens Lindström
 * @email ejilxq-9@studera.ltu.se
 * @course D0018D Objekorienterad programmering
 */

public class BankLogic {
    static Scanner input = new Scanner(System.in);

    private ArrayList<Customer> customerList = new ArrayList<Customer>();

    public ArrayList<Customer> addCustomers() {
        customerList.add(new Customer("Kalle", "Karlsson", "8505221898"));
        customerList.add(new Customer("Pelle", "Persson", "6911258876"));
        customerList.add(new Customer("Lotta", "Larsson", "7505121231"));

        return customerList;
    }
    /**
     * Returnerar en ArrayList<String> som innehåller en presentation av bankens
     * alla kunder.
     *
     * @param //void
     * @return void
     */

    public ArrayList<String> getAllCustomers() {
        if (customerList.size() > 0) {
            System.out.println(customerList.toString());
        }
        ArrayList<String> allCustomers = new ArrayList<String>();
        for (Customer customer : customerList) {
            allCustomers.add(customer.toString());
        }
        return allCustomers;
    }
    /**
     * Skapar ett Customer objekt och lägger den i en ArrayList
     *
     * @param //String
     *          name, surname, pNr
     * @return boolean
     */

    public boolean createCustomer(String name, String surname, String pNr) {
        do {
            for (Customer customer : customerList) {
                System.out.print("Enter name: ");
                name = input.nextLine();
                System.out.print("Enter surname: ");
                surname = input.nextLine();
                System.out.print("Enter personal number: ");
                pNr = input.nextLine();
            }
        } while (!pNr.matches(pNr));

        return true;
    }
    /**
     * Skapar ett unikt konto till kund med personnummer pNr. Returnerar -1 om den
     * inte skapades.
     *
     * @param //String
     *          pNr
     * @return account number
     */

    public int createSavingsAccount(String pNo) {
        for (Customer customer : customerList) {
            if(pNo.equals(customer.getpNo())) {
                /** Kunden finns*/
                SavingsAccount account = new SavingsAccount(1.0, 1.0, "savings");
                /** Kontot skapat, lägg till hos kund*/
                customer.addAccount(account);
                /** Ta ut kontonummer, returna det*/
                int accountNbr = account.getAccountNumber();
                return accountNbr;
            }
        }
        /** Ingen kund fanns med det personnummret innan, returnera -1*/
        return -1;
    }

    public String getAccount(String pNo, int accountId) {
        for (Customer customer : customerList) {
            if(pNo.equals(customer.getpNo())) {
                /** kunden fanns, kolla konton*/
                for (SavingsAccount account : customer.getCustomerAccounts()) {
                    if(account.getAccountNumber() == accountId) {
                        /** Kontot fanns, returnera toString()*/
                        return account.toString();
                    }
                }
                /** kontot fanns inte, returnera*/
                return null;
            }
        }
        /** ingen kund med det personnumret, returnera null*/
        return null;
    }
    /**
     * Returnerar en ArrayList<String> som innehåller informationen om kunden
     * inklusive dennes konton.
     *
     * @param //String
     *          pNr
     * @return ArrayList<String>
     */

    public ArrayList<String> getCustomer(String pNr) {
        for (Customer customer : customerList) {
            if (pNr.equals(customer.getpNo())) {
                String customerNameAndPNo = customer.toString();

                ArrayList<String> customerData = new ArrayList<String>();
                customerData.add(customerNameAndPNo);


                for (SavingsAccount account : customer.getCustomerAccounts()) {
                    customerData.add(account.toString());
                }
                return customerData;
            }
        }
        return null;
    }
    /**
     * Byter namn på existerande kund.
     *
     * @param //String
     *          name, surname, pNr
     * @return boolean
     */

    public boolean changeCustomerName(String name, String surname, String pNo) {
        for (Customer customer : customerList) {
            if (pNo.equals(customer.getpNo())) {
                customer.setName(name);
                customer.setSurname(surname);
                return true;
            }
        }

        return false;
    }
    /**
     * Avslutar ett konto med kontonnummer accountId som tillhör kunden pNr.
     * Returnerar null om inget konto togs bort
     *
     * @param //String
     *          pNr, int accountId
     * @return String
     */

    public ArrayList<Customer> closeAccount(String pNo, int accountId) {
        for (Customer customer : customerList) {
            if (pNo.equals(customer.getpNo())) {
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
    /**
     * Tar bort och alla dess konton från banken. Allt som togs bort returneras som
     * strängar i ArrayList.
     *
     * @param //String
     *          pNr
     * @return ArrayList<String>
     */

    public String deleteCustomer(String pNo) {
        for (Customer customer : customerList) {
            if (pNo.equals(customer.getpNo())) {
                ArrayList<String> customerData = getCustomer(pNo);
                customerList.remove(customer);


                return customerData.toString();
            }
        }

        return null;
    }
    /**
     * Gör ett uttag på konto med kontonnummer accountId som tillhör kundens pNr.
     * Returnerar true om det gick bra annars false
     *
     * @param //String
     *          pNr, int accountId, double amount
     * @return boolean
     */

    public boolean withdraw(String pNo, int accountId, double amount) {
        for (Customer customer : customerList) {
            if (pNo.equals(customer.getpNo())) {
                ArrayList<SavingsAccount> customerAccounts = customer.getCustomerAccounts();
                for (SavingsAccount customerAccount : customerAccounts) {
                    if (accountId == customerAccount.getAccountNumber()) {

                        customerAccount.setAmount(amount);
                        return true;
                    } else {
                        return false;
                    }

                }
            }
        }

        return  false;
    }/**
     * Gör en insättning på konto med kontonnummer accountId som tillhör kunden pNr.
     * Returnerar true om det gick bra annars false
     *
     * @param //String
     *          pNr, int accountId, double amount.
     * @return boolean
     *//**/


    public boolean deposit(String pNo, int accountId, double amount) {
        for (Customer customer : customerList) {
            if (pNo.equals(customer.getpNo())) {
            } else{
                return false;
            }
        }
        return true;
    }
}
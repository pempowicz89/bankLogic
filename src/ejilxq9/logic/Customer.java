package ejilxq9.logic;

import java.util.ArrayList;
/**
 * Customer-klassen.
 *
 * @author Jens Lindström
 * @email ejilxq-9@studera.ltu.se
 * @course D0018D Objekorienterad programmering
 */


public class Customer {

    private String name;
    private String surname;
    private String pNr;

    //Arraylisten som sedan används i BankLogic
    public ArrayList<SavingsAccount> customerAccounts = new ArrayList<>();
    //Konstruktorn
    public Customer(String s, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.pNr = pNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getpNr() {
        return pNr;
    }

    public ArrayList<SavingsAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(ArrayList<SavingsAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + pNr;
    }

    public void addAccount(SavingsAccount account) {
        customerAccounts.add(account);
    }
}

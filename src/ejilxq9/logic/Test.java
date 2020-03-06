package ejilxq9.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        TestBank bankMenu = new TestBank();
        bankMenu.test();
    }

    /**
     * D0018D, Lab 1: Checks the classes Account, Customer and BankLogic
     * This class can be updated during the course.
     * Last changes:  2019-11-08
     * @author Susanne Fahlman, susanne.fahlman@ltu.se
     */


    public static class TestBank {
        private BankLogic bank = new BankLogic();

        //-----------------------------------------------------------------------------------
        // Runs the tests
        //-----------------------------------------------------------------------------------
        public void test() throws FileNotFoundException {
            String customerName;
            String customerSurname;
            String personalNumber;
            int accountNumber;
            double amount;

            // Sends error messages in an text file called err.txt instead of to the console
            // err.txt should be empty if everything is implemented correctly
            File file = new File("err.txt");
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setErr(ps);

            System.out.println("Testing begins, check the printout below but also check that the file err.txt");

            // Gets the empty list
            testingGetAllCustomers();
            System.out.println("# []");
            System.out.println();

            // Create customers
            customerName = "Karl";
            customerSurname = "Karlsson";
            personalNumber = "8505221898";
            if (!testingCreateCustomer(customerName, customerSurname, personalNumber))
                System.err.println("Error: createCustomer(" + customerName + "," + customerSurname + "," + personalNumber + ")");

            customerName = "Donald";
            customerSurname = "Duck";
            personalNumber = "8505221898";
            if (testingCreateCustomer(customerName, customerSurname, personalNumber)) // Should not work, personal number should be unique
                System.err.println("Error: createCustomer(" + customerName + "," + customerSurname + "," + personalNumber + ")");

            customerName = "Pelle";
            customerSurname = "Persson";
            personalNumber = "6911258876";
            if (!testingCreateCustomer(customerName, customerSurname, personalNumber))
                System.err.println("Error: createCustomer(" + customerName + "," + customerSurname + "," + personalNumber + ")");

            customerName = "Lotta";
            customerSurname = "Larsson";
            personalNumber = "7505121231";
            if (!testingCreateCustomer(customerName, customerSurname, personalNumber))
                System.err.println("Error: createCustomer(" + customerName + "," + customerSurname + "," + personalNumber + ")");

            customerName = "Ronald";
            customerSurname = "Duck";
            personalNumber = "6911258876";
            if (testingCreateCustomer(customerName, customerSurname, personalNumber)) // Should not work, personal number should be unique
                System.err.println("Error: createCustomer(" + customerName + "," + customerSurname + "," + personalNumber + ")");

            // Get all customers in the list and prints it
            System.out.println();
            testingGetAllCustomers();
            // Prints an example how the output should look, try to get your output as close to this as possible
            // Don't print the '#' however that is just to make it clear what is the expected output
            System.out.print("# [Karl Karlsson 8505221898,");
            System.out.print(" Pelle Persson 6911258876,");
            System.out.println(" Lotta Larsson 7505121231]");
            System.out.println();

            // Change a customers name
            customerName = "Kalle";
            customerSurname = "Karlsson";
            personalNumber = "8505221898";
            if (!testingChangeName(customerName, customerSurname, personalNumber))
                System.err.println("Error: changeCustomerName(" + customerName + "," + customerSurname + "," + personalNumber + ")");

            // Change a customers name
            customerName = "Olle";
            customerSurname = "Karlsson";
            personalNumber = "9905225166";
            if (testingChangeName(customerName, customerSurname, personalNumber))
                System.err.println("Error: changeCustomerName(" + customerName + "," + customerSurname + "," + personalNumber + ")");
            // Get information about the customer
            System.out.println();
            testingGetCustomer("8505221898");
            System.out.println("# [Kalle Karlsson 8505221898]");
            System.out.println();

            // Gets customer that don't exists
            testingGetCustomer("9905225166");
            System.out.println("# null");
            System.out.println();

            // Creates accounts
            personalNumber = "8505221898";
            if (1001 != testingCreateSavingsAccount(personalNumber))
                System.err.println("Error: createSavingsAccount(" + personalNumber + ")");
            personalNumber = "9905225166";
            if (-1 != testingCreateSavingsAccount(personalNumber))
                System.err.println("Error: createSavingsAccount(" + personalNumber + ")");
            personalNumber = "6911258876";
            if (1002 != testingCreateSavingsAccount(personalNumber))
                System.err.println("Error: createSavingsAccount(" + personalNumber + ")");
            personalNumber = "8505221898";
            if (1003 != testingCreateSavingsAccount(personalNumber))
                System.err.println("Error: createSavingsAccount(" + personalNumber + ")");
            personalNumber = "7505121231";
            if (1004 != testingCreateSavingsAccount(personalNumber))
                System.err.println("Error: createSavingsAccount(" + personalNumber + ")");

            // Get information about the customer including accounts
            System.out.println();
            testingGetCustomer("8505221898");
            System.out.print("# [Kalle Karlsson 8505221898, ");
            System.out.print("1001 0.0 Sparkonto 1.0, ");
            System.out.println("1003 0.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the customer including accounts
            testingGetCustomer("6911258876");
            System.out.print("# [Pelle Persson 6911258876, ");
            System.out.println("1002 0.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the customer including accounts
            testingGetCustomer("7505121231");
            System.out.print("# [Lotta Larsson 7505121231, ");
            System.out.println("1004 0.0 Sparkonto 1.0]");
            System.out.println();

            // Deposit and withdraw
            personalNumber = "8505221898";
            accountNumber = 1002;
            amount = 700;
            if (testingDeposit(personalNumber, accountNumber, amount))    // Should not work, not the owner of the account
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "8505221898";
            accountNumber = 1001;
            amount = 500;
            if (!testingDeposit(personalNumber, accountNumber, amount))
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "8505221898";
            accountNumber = 1001;
            amount = 500;
            if (!testingWithdraw(personalNumber, accountNumber, amount))
                System.err.println("Error: withdraw(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "8505221898";
            accountNumber = 1001;
            amount = 1;
            if (testingWithdraw(personalNumber, accountNumber, amount))    // Should not work, not enough money
                System.err.println("Error: withdraw(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "8505221898";
            accountNumber = 1001;
            amount = 1000;
            if (!testingDeposit(personalNumber, accountNumber, amount))
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            // Get information about the customer including accounts
            System.out.println();
            testingGetCustomer("8505221898");
            System.out.print("# [Kalle Karlsson 8505221898, ");
            System.out.print("1001 1000.0 Sparkonto 1.0, ");
            System.out.println("1003 0.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the customer including accounts
            testingGetCustomer("6911258876");
            System.out.print("# [Pelle Persson 6911258876, ");
            System.out.println("1002 0.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the customer including accounts
            testingGetCustomer("7505121231");
            System.out.print("# [Lotta Larsson 7505121231, ");
            System.out.println("1004 0.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the account
            testingGetAccount("8505221898", 1001);
            System.out.println("# 1001 1000.0 Sparkonto 1.0");
            System.out.println();


            testingGetAccount("8505221898", 1002);    // Should not work, not the owner of the account
            System.out.println("# null ");

            // Closes the account
            System.out.println();
            testingCloseAccount("8505221898", 1001);
            System.out.println("# 1001 1000.0 Sparkonto 1.0 10.0");
            System.out.println();

            // Get information about all customers
            testingGetAllCustomers();
            System.out.print("# [Kalle Karlsson 8505221898, ");
            System.out.print("Pelle Persson 6911258876, ");
            System.out.println("Lotta Larsson 7505121231]");
            System.out.println();

            // Deposit
            personalNumber = "8505221898";
            accountNumber = 1003;
            amount = 5000;
            if (!testingDeposit(personalNumber, accountNumber, amount))
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "8505221898";
            accountNumber = 1003;
            amount = 5000;
            if (!testingDeposit(personalNumber, accountNumber, amount))
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            // Creates account
            personalNumber = "7505121231";
            System.out.println();
            if (1005 != testingCreateSavingsAccount(personalNumber))
                System.err.println("Error: createSavingsAccount(" + personalNumber + ")");

            // Get information about the customer including accounts
            System.out.println();
            testingGetCustomer("8505221898");
            System.out.print("# [Kalle Karlsson 8505221898, ");
            System.out.println("1003 10000.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the customer including accounts
            testingGetCustomer("6911258876");
            System.out.print("# [Pelle Persson 6911258876, ");
            System.out.println("1002 0.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the customer including accounts
            testingGetCustomer("7505121231");
            System.out.print("# [Lotta Larsson 7505121231, ");
            System.out.print("1004 0.0 Sparkonto 1.0, ");
            System.out.println("1005 0.0 Sparkonto 1.0]");
            System.out.println();

            // Deposits and withdraws
            personalNumber = "7505121231";
            accountNumber = 1005;
            amount = 1000;
            if (!testingDeposit(personalNumber, accountNumber, amount))
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "7505121231";
            accountNumber = 1005;
            amount = 100;
            if (!testingWithdraw(personalNumber, accountNumber, amount))
                System.err.println("Error: withdraw(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "7505121231";
            accountNumber = 1005;
            amount = 100;
            if (!testingWithdraw(personalNumber, accountNumber, amount))
                System.err.println("Error: withdraw(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "7505121231";
            accountNumber = 1005;
            amount = 100;
            if (!testingWithdraw(personalNumber, accountNumber, amount))
                System.err.println("Error: withdraw(" + personalNumber + "," + accountNumber + "," + amount + ")");

            // Get information about the customer including accounts
            System.out.println();
            testingGetCustomer("7505121231");
            System.out.print("# [Lotta Larsson 7505121231, ");
            System.out.print("1004 0.0 Sparkonto 1.0, ");
            System.out.println("1005 700.0 Sparkonto 1.0]");
            System.out.println();

            // Get information about the account
            testingGetAccount("7505121231", 1005);
            System.out.println("# 1005 700.0 Sparkonto 1.0");
            System.out.println();

            // Deletes the customer including accounts
            testingDeleteCustomer("7505121231");
            System.out.print("# [Lotta Larsson 7505121231, ");
            System.out.print("1004 0.0 Sparkonto 1.0 0.0, ");
            System.out.println("1005 700.0 Sparkonto 1.0 7.0]");
            System.out.println();

            // Tries to delete a customer that don't exists
            testingDeleteCustomer("9905225166");
            System.out.println("# null");
            System.out.println();

            // Tries to delete a customer that don't exists
            testingCloseAccount("7505121231", 1009);
            System.out.println("# null");
            System.out.println();

            // Get information about all customers
            testingGetAllCustomers();
            System.out.print("# [Kalle Karlsson 8505221898, ");
            System.out.println("Pelle Persson 6911258876]");
            System.out.println();

            // Get information about the account
            testingGetAccount("6911258876", 1003); // Should not work
            System.out.println("# null");
            System.out.println();

            // Deposit
            personalNumber = "6911258876";
            accountNumber = 1003;
            amount = 900;
            if (testingDeposit(personalNumber, accountNumber, amount))
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            personalNumber = "6911258876";
            accountNumber = 1002;
            amount = 900;
            if (!testingDeposit(personalNumber, accountNumber, amount))
                System.err.println("Error: deposit(" + personalNumber + "," + accountNumber + "," + amount + ")");

            // Get information about the customer including accounts
            System.out.println();
            testingGetCustomer("6911258876");
            System.out.print("# [Pelle Persson 6911258876, ");
            System.out.println("1002 900.0 Sparkonto 1.0]");
            System.out.println();

            // Withdraw
            personalNumber = "6911258876";
            accountNumber = 1002;
            amount = 900;
            if (!testingWithdraw(personalNumber, accountNumber, amount))
                System.err.println("Error: withdraw(" + personalNumber + "," + accountNumber + "," + amount + ")");

            // Get information about the customer including accounts
            System.out.println();
            testingGetCustomer("6911258876");
            System.out.print("# [Pelle Persson 6911258876, ");
            System.out.println("1002 0.0 Sparkonto 1.0]");
            System.out.println();

            // Deletes the customer
            testingDeleteCustomer("6911258876");
            System.out.print("# [Pelle Persson 6911258876, ");
            System.out.println("1002 0.0 Sparkonto 1.0 0.0]");
            System.out.println();

            // Deletes the customer
            testingDeleteCustomer("8505221898");
            System.out.print("# [Kalle Karlsson 8505221898, ");
            System.out.println("1003 10000.0 Sparkonto 1.0 100.0]");
            System.out.println();

            // No customers left...
            testingGetAllCustomers();
            System.out.println("# []");
            System.out.println();

            ps.close();
        }


        /**
         * Prints the customer list
         */
        private void testingGetAllCustomers() {
            System.out.println("getAllCustomers()");
            ArrayList<String> result = bank.getAllCustomers();
            System.out.println("  " + result);
        }

        /**
         * Prints the customer
         *
         * @param pNr - Personal number of customer that will be printed
         */
        private void testingGetCustomer(String pNr) {
            System.out.println("getCustomer(" + pNr + ")");
            System.out.println("  " + bank.getCustomer(pNr));
        }

        /**
         * Prints the account
         *
         * @param pNr       - Personal number of customer that owns the account
         * @param accountId - Id of the account that will be printed
         */
        private void testingGetAccount(String pNr, int accountId) {
            System.out.println("getAccount(" + pNr + "," + accountId + ")");
            System.out.println("  " + bank.getAccount(pNr, accountId));
        }

        /**
         * Creates a cusomer if no customer exists with the same personal number
         *
         * @param name - Name of the customer
         * @param pNr  - Personal number of customer
         * @return true if customer is created otherwise false
         */
        private boolean testingCreateCustomer(String name, String surname, String pNr) {
            System.out.println("createCustomer(" + name + "," + surname + "," + pNr + ")");
            return bank.createCustomer(name, surname, pNr);
        }

        /**
         * Changes the name of the customer
         *
         * @param name - The new name
         * @param pNr  - Personal number of customer that is getting a new name
         * @return true if customer name is changed otherwise false
         */
        private boolean testingChangeName(String name, String surname, String pNr) {
            System.out.println("changeCustomerName(" + name + "," + surname + "," + pNr + ")");
            return bank.changeCustomerName(name, surname, pNr);
        }

        /**
         * Creates a account
         *
         * @param pNr - Personal number of customer that is getting a new account
         * @return true if account is created otherwise false
         */
        private int testingCreateSavingsAccount(String pNr) {
            System.out.println("createSavingsAccount(" + pNr + ")");
            return bank.createSavingsAccount(pNr);
        }

        /**
         * Deposit the amount
         *
         * @param pNr       - The personal number of the customer that owns the account
         * @param accountId -  The id of the account
         * @param amount    - The amount
         * @return true if amount is deposited otherwise false
         */
        private boolean testingDeposit(String pNr, int accountId, double amount) {
            System.out.println("deposit(" + pNr + "," + accountId + "," + amount + ")");
            return bank.deposit(pNr, accountId, amount);
        }

        /**
         * Withdraws the amount
         *
         * @param pNr       - The personal number of the customer that owns the account
         * @param accountId -  The id of the account
         * @param amount    - The amount
         * @return true if amount is withdrawn otherwise false
         */
        private boolean testingWithdraw(String pNr, int accountId, double amount) {
            System.out.println("withdraw(" + pNr + "," + accountId + "," + amount + ")");
            return bank.withdraw(pNr, accountId, amount);
        }

        /**
         * The account is deleted and result is printed
         *
         * @param pNr       - The personal number of the customer that is to be deleted
         * @param accountId - The id of the account that is closed
         */
        private void testingCloseAccount(String pNr, int accountId) {
            System.out.println("closeAccount(" + pNr + "," + accountId + ")");
            System.out.println("  " + bank.closeAccount(pNr, accountId));
        }

        /**
         * The customer is deleted and result is printed
         *
         * @param pNr - The personal number of the customer that is to be deleted
         */
        private void testingDeleteCustomer(String pNr) {
            System.out.println("deleteCustomer(" + pNr + ")");
            System.out.println("  " + bank.deleteCustomer(pNr));
        }
    }
}






package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static it.unibo.bank.impl.SimpleBankAccount.*;
import static it.unibo.bank.impl.SimpleBankAccount.ATM_TRANSACTION_FEE;
import static it.unibo.bank.impl.StrictBankAccount.TRANSACTION_FEE;
import static org.junit.jupiter.api.Assertions.*;

public class TestStrictBankAccount {

    private final static int INITIAL_AMOUNT = 100;

    // 1. Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    

    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi,INITIAL_AMOUNT);
    }

    // 2. Test the initial state of the StrictBankAccount
    @Test
    public void testInitialization() {
        assertEquals("Mario",mRossi.getName());
        assertEquals("Rossi",mRossi.getSurname());
        assertEquals(1,mRossi.getUserID());
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }


    // 3. Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
    @Test
    public void testManagementFees() {
        bankAccount.deposit(1,100);
        bankAccount.chargeManagementFees(1);
        assertEquals(194.9,bankAccount.getBalance());
    }

    // 4. Test the withdraw of a negative value
    @Test
    public void testNegativeWithdraw() {
        try{
            bankAccount.withdraw(1, -10);
        }
        catch (IllegalArgumentException e){
            assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
            assertEquals("Cannot withdraw a negative amount", e.getMessage());
        }
    }

    // 5. Test withdrawing more money than it is in the account
    @Test
    public void testWithdrawingTooMuch() {
        try{
            bankAccount.withdraw(1, INITIAL_AMOUNT+5);
        }catch(IllegalArgumentException e){
            assertEquals(INITIAL_AMOUNT,bankAccount.getBalance());
            assertEquals("Insufficient balance", e.getMessage());
        }
    }
}

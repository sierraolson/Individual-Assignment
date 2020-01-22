package cs2340.gatech.edu.lab4.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zumong on 2/16/18.
 */

public class Account {

    private String username;
    private String password;
    AccountType type;
    private boolean hasReservation = false;

    /**
     * account constructor
     * @param user username
     * @param pass password
     * @param t USER or ADMIN account type
     */
    public Account(String user, String pass, AccountType t) {
        username = user;
        password = pass;
        type = t;
    }
    public static List<AccountType> legalAccountTypes = Arrays.asList(AccountType.USER,AccountType.ADMIN);

    /**
     * getter for username
     * @return username of Account
     */
    public String getUsername() { return username; }

    /**
     * getter for password
     * @return password of Account
     */
    public String getPassword() { return password; }

    /**
     * getter for reservation status
     * @return boolean for whether or not the account has a reservation
     */
    public boolean getHasReservation() {return hasReservation;}

    /**
     * getter for account type
     * @return USER or ADMIN depending on account type
     */
    public AccountType getAccountType() {
        return type;
    }

    /**
     * toString for Account
     * @return string representation of the account
     */
    public String toString() {
        return username + " " + password + " " + type;
    }

    /**
     * Sets the boolean status of whether or not a user has a reservation
     * @param has boolean status of reservation
     */
    public void setHasReservation(Boolean has) {hasReservation = has;}

    /**
     * equals override for accounts, checks if username is equivalent
     * @param o Object that is cast into an Account
     * @return boolean true if the same account, false if otherwise
     */
    @Override
    public boolean equals(Object o) {
        Account account = (Account) o;
        return account.username.equals(((Account) o).getUsername());
    }

}

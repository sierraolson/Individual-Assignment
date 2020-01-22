package cs2340.gatech.edu.lab4.model;

import java.util.ArrayList;

import cs2340.gatech.edu.lab4.controller.FirebaseController;


public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance;}
    static FirebaseController controller = FirebaseController.getInstance();

    private Model() {

    }
    private static ArrayList<Account> _accounts = new ArrayList<Account>();
    private static ArrayList<Shelter> _shelters = new ArrayList<>();
    private static int _numAccounts = _accounts.size();
    private Shelter _currentShelter;
    private final Shelter theNullShelter = new Shelter(0,"no such shelter","capacity",0,"restriction",0,0,"address","note","phone");

    /**
     * Getter for AccountList
     * @return
     */
    public static Iterable getAccountList() {
        return _accounts;
    }

    /**
     * Iterates over accounts finding users, returns result
     * @return ArrayList of users
     */
    public static ArrayList getUserList() {
        ArrayList<Account> result = new ArrayList<Account>();
        for(Account a: _accounts) {
            if(a.getAccountType().equals(AccountType.USER)) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * Iterates over accounts and returns ArrayList of admins
     * @return
     */
    public static ArrayList getAdminList() {
        ArrayList<Account> result = new ArrayList<Account>();
        for(Account a: _accounts) {
            if(a.getAccountType().equals(AccountType.ADMIN)) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * Updates the number of accounts from database
     * @param a
     */
    public static void getAccountsFromDatabase(Account a){
        _accounts.add(a);
        updateNumAccounts();
    }

    /**
     * Adds new account using information entered, uses post to push to database
     * @param user username
     * @param pass password
     * @param type  admin or user account type
     */
    public static void addNewAccount(String user, String pass, AccountType type) {
        _accounts.add(new Account(user,pass,type));
        updateNumAccounts();
        String id = String.valueOf(_numAccounts - 1);
        FirebaseController.postAccount(id,user,pass,type);

    }

    /**
     * This method is called in LoginActivity searching username and password are in the ArrayList
     * @param user username
     * @param password password
     * @return
     */
    public static boolean isValidUserAndPassword(String user, String password) {
        user = user.replaceAll("\\s", "");
        for (Object a : getAccountList()) {
                System.out.println("account from array" + a);
                if(((Account)a).getUsername().equals(user)
                        && ((Account)a).getPassword().equals(password)) { return true;}


        }
        return false;
    }

    /**
     * Add new shelter with information in parameters
     * @param key
     * @param name name of shelter
     * @param cap total capacity
     * @param aBed number of available beds
     * @param restr shelter restrictions
     * @param longi longitude info
     * @param lati latitude info
     * @param addr address of the shelter
     * @param note notes
     * @param phoneNum shelter contact number
     */
    public static void addShelter(int key, String name, String cap,int aBed, String restr, float longi, float lati, String addr, String note, String phoneNum) {
        boolean shelterFound = false;
        if (!_shelters.isEmpty()) {
            for (Shelter shelter: _shelters) {
                if (shelter.getKey() == key) {
                    shelterFound = true;
                }
            }
            if (!shelterFound) {
                _shelters.add(new Shelter(key, name, cap, aBed, restr,longi,lati,addr,note,phoneNum));
            }
        } else {
            _shelters.add(new Shelter(key, name, cap, aBed, restr,longi,lati,addr,note,phoneNum));
        }

    }

    /**
     * Add new shelter with a shelter object
     * @param newShelter Shelter to be added
     */
    public static void addShelter(Shelter newShelter) {
        boolean shelterFound = false;
        if (!_shelters.isEmpty()) {
            for (Shelter shelter : _shelters) {
                System.out.println("Shelter from array: " + shelter);
                if (shelter.getKey() == newShelter.getKey()) {
                    shelterFound = true;
                }
            }
            if (!shelterFound) {
                _shelters.add(newShelter);
            }
        } else {
            _shelters.add(newShelter);
        }

    }

    /**
     * Iterates through shelter list updating amount of beds
     * @param currentShelter
     */
    public static void updateBedsAvailable(Shelter currentShelter) {
        for (Shelter shelter:_shelters
             ) {
            System.out.println("Shelter Name:" + shelter.getName() + " Beds Available: " + shelter.getAvailableBeds());

        }
    }

    /**
     * Getter for shelter list
     * @return returns ArrayList of shelters
     */
    public static ArrayList<Shelter> getShelters() {
        return _shelters;
    }

    /**
     *  Updates number of accounts to the current size of the account list
     */
    private static void updateNumAccounts() {
        _numAccounts = _accounts.size();
    }

    /**
     * Getter for shelter
     * @return current shelter
     */
    public Shelter getCurrentShelter() {
        return _currentShelter;
    }

    /**
     * setter for current shelter
     * @param shelter shelter to set
     */
    public void setCurrentShelter(Shelter shelter) {
        _currentShelter = shelter;
    }

    /**
     * Shelter getter
     * @param id id of shelter being found
     * @return shelter of id entered or null
     */
    public Shelter getShelterById(int id) {
        for (Shelter s: _shelters) {
            if (s.getKey() == id) {
                return s;
            }
        }
        return theNullShelter;
    }

    /**
     * Shelter setter
     * @param id shelter's id
     * @param newShelter shelter to replace current id
     */
    public void setShelterById(int id, Shelter newShelter) {
        for (int i = 0; i < _shelters.size(); i++) {
            if (_shelters.get(i).getKey() == id) {
                _shelters.set(i, newShelter);
            }
        }
    }


}

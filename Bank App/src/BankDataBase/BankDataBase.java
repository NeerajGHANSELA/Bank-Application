package BankDataBase;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.NumberFormatter;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Base64;
import java.util.LinkedList;

/**
 * bank's implements the hashtable data structure to store the user information.
 * Most frequent operation used: creating an account, updating an account  ----- O(lambda/constant)
 * username is used to the find the hash value for the table
 */
public class BankDataBase {
    // information that needs to be saved:
        // first name
        // last name
        // username
        // password
        // sin number
        // current bank balance

    private LinkedList<Node>[] bank_data_base;  // bank database which is an array with the datatype of LinkedList at each index.
    private int max_size;  // number of buckets or indexes
    private int num_of_nodes;
    private int password_length = 24;
    private class Node {
        private String username;
        private String password;
        private String first_name;
        private String last_name;
        private String SIN_number;
        private float current_bank_balance;

        public Node(String username, String password, String first_name, String last_name, String SIN_num) {
            this.username = username;
            this.password = password;
            this.first_name = first_name;
            this.last_name = last_name;
            this.SIN_number = SIN_num;
            this.current_bank_balance = 0.00F;  // initially every client will have $0 in their bank account
        }
    }

    public BankDataBase(int max_size) {
        this.max_size = max_size;
        this.num_of_nodes = 0;

        this.bank_data_base = new LinkedList[max_size];

        // iterate over the hashtable to create an empty LinkedList in each index.
        for(int i = 0; i < max_size; i++) {
            // in each index of the array, create an empty linked list
            this.bank_data_base[i] = new LinkedList<>();
        }
    }

    /**
     * checks whether the username is in the bank's database.
     * @param username: username entered by the user
     * @return returns true or false by checking whether the username exists in the bank's database
     */
    public boolean Check_username(String username) {    // username received is plain text
        if(Contains(username)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks whether the password entered by the user matches with the one saved in the bank's database
     * @param password: password entered by the user
     * @return returns true or false by checking in the database and whether the password matches for the username entered if it exists.
     */
    public boolean Check_password(String password, String username) {
        // check the hashed username
        String hashed_username = "";
        try {
            hashed_username = Hash(username);
        } catch (Exception ignored) {}

        if(Check_username(username)) {
            // if the username exists
            // goto the username in the table and match its password with the password entered by the user

            // hash the password
            String hashed_password = "";

            try {
                hashed_password = Hash(password);
            } catch(Exception ignored) {}

            int bi = HashValue(hashed_username);   // index in the database array
            int di = SearchInLL(hashed_username, bi);  // index within the LinkedList
            Node node = bank_data_base[bi].get(di);
            String password_should_be = node.password;

            if(hashed_password.equals(password_should_be)) {
                return true;
            } else {
                return false;
            }
        }
        return false;  // if the username doesn't exist then the password also doesn't exist.
    }

    /**
     * @param username the user that the bank balance is returned for
     * @return returns the current bank balance of the user given as a parameter
     */
    public float getCurrentBankBalance(String username) {
        // find the hash value of the username
        // hash the username
        String hashed_username = "";
        try {
            hashed_username = Hash(username);
        } catch (Exception ignored) {}

        int bi = HashValue(hashed_username);
        int di = SearchInLL(hashed_username, bi);

        if(di == -1) {  // the username does not exist in the bank's database
            System.out.println("Such username does not exist");
            return 0.0F;
        } else {  // username does exist in the bank's database
            Node node = bank_data_base[bi].get(di);
            return Float.parseFloat(String.format("%.2f", node.current_bank_balance));
        }
    }


    /**
     * deposits money into the account of the user specified by its username
     * @param username  username account into which the money is to be deposited
     * @param amount  amount to be deposited into the account
     */
    public void DepositMoney(String username, float amount) {
        // find the hash value of the username
        // hash the username
        String hashed_username = "";
        try {
            hashed_username = Hash(username);
        } catch (Exception ignored) {}

        int bi = HashValue(hashed_username);
        int di = SearchInLL(hashed_username, bi);

        if(di == -1) {  // the username does not exist in the bank's database
            System.out.println("Such username does not exist");
        } else {  // username does exist in the bank's database
            Node node = bank_data_base[bi].get(di);
            node.current_bank_balance += amount;  // adding amount to the current bank balance
        }
    }

    public void WithdrawMoney(String username, float amount) {
        // find the hash value of the username
        // hash the username
        String hashed_username = "";
        try {
            hashed_username = Hash(username);
        } catch (Exception ignored) {}

        int bi = HashValue(hashed_username);
        int di = SearchInLL(hashed_username, bi);

        if(di == -1) {  // the username does not exist in the bank's database
            System.out.println("Such username does not exist");
        } else {  // username does exist in the bank's database
            Node node = bank_data_base[bi].get(di);
            node.current_bank_balance -= amount;  // subtract the amount from the current bank balance
        }
    }

    // Find HashValue of the hashed username
    private int HashValue(String username) {  // must be 0 - (max_size -1)
        int bi = username.hashCode();
        return (Math.abs(bi) % max_size);
    }

    /**
     * searches the username in the bucket_index given as a parameter by iterating over all the nodes.
     * @param username username to find
     * @param bucket_index index where the username could be found
     * @return returns the index of the username, where it exists within the LinkedList. returns -1 if not found.
     */
    private int SearchInLL(String username, int bucket_index) { // Hashed username is provided
        // search in the bucket index to check whether the user already has an account or not
        LinkedList<Node> ll = bank_data_base[bucket_index];
        int di = 0;

        for(int i = 0; i < ll.size(); i++) {
            if(ll.get(i).username.equals(username)) {
                di = i;
                return di;  // data index where the username exists within the linked list
            }
        }
        return -1;  // username does not exist
    }

    /**
     * Search whether the username exists within the banks database or not
     * @param username username to find.
     * @return returns true if the username is found. false otherwise.
     */
    private boolean Contains(String username) { // username received is the plain text
        // find the hash value of the username
        // hash the username
        String hashed_username = "";
        try {
            hashed_username = Hash(username);
        } catch (Exception ignored) {}


        int bi = HashValue(hashed_username);
        int di = SearchInLL(hashed_username, bi);

        if(di == -1) {  // the username does not exist in the bank's database
            return false;
        } else {  // username does exist in the bank's database
            return true;
        }
    }

    private String Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(input.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    /**
     * adding a new client's information into the bank's database
     * @param username client's username. used to log in
     * @param password client's password. used to log in
     * @param first_name client's first name
     * @param last_name client's last name
     * @param SIN_number client's SIN number
     */
    public void Add(String username, String password, String first_name, String last_name, String SIN_number) {
        // use the username to find the bucket index

        // Hash the username using SHA-256
        String hashed_username = "";     // changes form of the plain text

        try {
            hashed_username = Hash(username);
        } catch (Exception e) {
            System.out.println("Exception caught in Add method in BankDataBase class.");
        }

        int bi = HashValue(hashed_username);
        int di = SearchInLL(hashed_username, bi);  // username is given to check whether the username provided already exists or not.

        if(di == -1) { // username does not exist
            // create a new account for the user
            // before adding the information into the database, encrypt the data for better protection of the user
            String hashed_password = "";
            String hashed_first_name = "";
            String hashed_last_name = "";
            String hashed_SIN_number = "";

            try {
                hashed_password = Hash(password);
                hashed_first_name = Hash(first_name);
                hashed_last_name = Hash(last_name);
                hashed_SIN_number = Hash(SIN_number);
            } catch(Exception e) {
                System.out.println("Exception caught in Add method in BankDataBase class!!!!!!!!!");
            }

            Node nn = new Node(hashed_username, hashed_password, hashed_first_name, hashed_last_name, hashed_SIN_number);
            bank_data_base[bi].add(nn); // adding the user into the bank's database.
            num_of_nodes++;

        } else {  // key exists
            // may want to update the information of the user

            System.out.println("updating the information in the bank database");
            Node node_to_update = bank_data_base[bi].get(di);
            node_to_update.username = username;
            node_to_update.password = password;
            node_to_update.first_name = first_name;
            node_to_update.last_name = last_name;
            node_to_update.SIN_number = SIN_number;
            node_to_update.current_bank_balance = 0.00F;

            // for now - print (account with the username "" already exists)
            System.out.println("account with the username " + username + " already exists");
        }

        double threshold = 3.0;
        // rehashing
        double lambda = (double) num_of_nodes/max_size;
        if(lambda >= threshold) {
            Rehash();
        }

        System.out.println("Printing database");
        System.out.println(PrintBankDataBase());
    }

    /**
     * used to increase the size of the bank's database as more client's become part of the bank
     */
    private void Rehash() {
        LinkedList<Node>[] old_bucket = bank_data_base;  // old bucket pointing at the old bucket before rehashing
        bank_data_base = new LinkedList[max_size*2];  // creating new bucket, doubling the size of the bucket

        // iterate over the new bucket and make every index to be empty LinkedList
        for(int i = 0; i < bank_data_base.length; i++) {
            bank_data_base[i] = new LinkedList<>();
        }

        // iterating over the old bucket index by index and putting the information into the new bucket
        for(int i = 0; i < old_bucket.length; i++) {
            LinkedList<Node> ll = old_bucket[i];
            for(int j = 0; j < ll.size(); j++) {
                Node node = ll.get(j);
                Add(node.username, node.password, node.first_name, node.last_name, node.SIN_number);
            }
        }
    }

    /**
     * update the password of the user.
     * @param username updating the password of the user with the given username.
     * @param password updating the old password with the given password.
     */
    public void UpdatePassword(String username, String password) {
        // use the username to find the bucket index
        // hash the username
        String hashed_username = "";
        try {
            hashed_username = Hash(username);
        } catch (Exception ignored) {}

        int bi = HashValue(hashed_username);
        int di = SearchInLL(hashed_username, bi);

        if(di == -1) {  // username does not exist
            System.out.println("Username " + username + " does not exist");
        } else {
            // Hash the password
            String hashed_password = "";
            try {
                hashed_password = Hash(password);
            } catch (Exception ignored){};

            Node node_to_update = bank_data_base[bi].get(di);
            String old_password = node_to_update.password;  // old password
            node_to_update.password = hashed_password;
        }
    }

    /**
     * updating the first name of the user.
     * @param username updating the first name of the user with the given username.
     * @param first_name  updating the old first name to the given new first name.
     */
    public void UpdateFirstName(String username, String first_name) {
        // use the username to find the bucket index
        // hash the username
        String hashed_username = "";
        try {
            hashed_username = Hash(username);
        } catch (Exception ignored) {}

        int bi = HashValue(hashed_username);
        int di = SearchInLL(hashed_username, bi);

        if(di == -1) {  // username does not exist
            System.out.println("Username " + username + " does not exist");
        } else {
            // hash the first name
            String hashed_first_name = "";
            try {
                hashed_first_name = Hash(first_name);
            } catch (Exception ignored){};

            Node node_to_update = bank_data_base[bi].get(di);
            node_to_update.first_name = hashed_first_name;
        }
    }


    /**
     * deletes the user from the bank's database.
     * @param username the user that is being removed from the bank's database
     */
    public void Remove(String username) {
        // find the bucket index
        int bi = HashValue(username);
        int di = SearchInLL(username, bi);

        if(di == -1) {  // username does not exist
            System.out.println("An account with username: " + username + " does not exist");
            return;
        } else {  // username does exist
            Node removed_node = bank_data_base[bi].remove(di);
            System.out.println("Removed Client's information: ");
            System.out.println("username: " + removed_node.username);
            System.out.println("first name: " + removed_node.first_name);
            System.out.println("last name: " + removed_node.last_name);
        }
    }

    public String PrintBankDataBase() {
        StringBuilder stringBuilder = new StringBuilder();

        // iterating over the bucket index
        for(int i = 0; i < bank_data_base.length; i++) {
            LinkedList<Node> ll = bank_data_base[i];
                // iterating over the data index
            for(int j = 0; j < ll.size(); j++) {
                Node nn = ll.get(j);
                stringBuilder.append("username: ").append(nn.username).append("\n");
                stringBuilder.append("Password: ").append(nn.password).append("\n");
                stringBuilder.append("First name: ").append(nn.first_name).append("\n");
                stringBuilder.append("Last name: ").append(nn.last_name).append("\n");
                stringBuilder.append("SIN number: ").append(nn.SIN_number).append("\n");
            }
        }

        return stringBuilder.toString();
    }
}

// importing packages
import java.util.*;

// Main class
public class Mini_project {

    // main function 
    public static void main(String[] args) {
        // creating object of Scanner class
        Scanner sc = new Scanner(System.in);

        /*creating an array of objects of Trading class
         * and passing it to account creater
         * and after the registration passing it to the Vyapaar method
         * for the trading purpose
        */
        Trading t[] = new Trading[20];
        int account_no_counter = 0;

        System.out.println("*****TRADING ACCOUNT*****");
        System.out.println("Hello sir, \nWelcome to the trading account");

        account_no_counter = acCreater(account_no_counter, t);

        // starting trading 
        MainOptions();
        Vyapaar(sc.nextInt(), t[account_no_counter]);
        System.out.println("programm terminated successfully");

    }

    // for printing MainOptions
    public static void MainOptions() {
        System.out.println("press 1 : Trading \nPress 2 : Forgot pincode ");
        System.out.println("Press 3 : Change mobile number ");
        System.out.println("Press 4 : Print Data sheet \nPress 5 : Exit");
    }

    // function for creating a new Account 
    public static int acCreater(int account_no_counter, Trading t[]) {
        // creating object of Scanner class
        Scanner sc = new Scanner(System.in);

        System.out.print("yes to continue with previous account ");
        System.out.println("or no to create a new account (yes/no) : ");

        if (sc.next().equals("yes") && account_no_counter > 0) {
            System.out.println("Please Enter your Account no.");
            account_no_counter = sc.nextInt();
        } else {
            if (account_no_counter == 0)
                System.out.println("YOU DON'T HAVE ANY ACCOUNT");
            System.out.print("To create a new account, \nPlease Enter your Full Name : ");
            String name = sc.nextLine();
            String name2 = sc.nextLine();
            System.out.print("Please create a PINCODE and REMEMBER it! : ");
            int pincode = sc.nextInt();
            System.out.print("Please Enter your Mobile number : ");
            String mobile_no = sc.next();
            account_no_counter++;
            t[account_no_counter] = new Trading();
            t[account_no_counter].save(name2, mobile_no, pincode);
            System.out.println("Dear, " + name2 + ", YOU ARE SUCCESSFULLY LOGGED IN !");
        }
        return account_no_counter;
    }

    // the main Vyapaar method
    public static void Vyapaar(int a, Trading t1) {

        // creating object of Scanner class
        Scanner sc = new Scanner(System.in);

        // switch on main options
        switch (a) {
            case 1: {
                t1.Print_Tradings();
                int trade_menu = sc.nextInt();

                // switch on trading options
                switch (trade_menu) {

                    // for printing account balance
                    case 1: {
                        System.out.println("Plese enter your pin");
                        if (t1.check_pin(sc.nextInt())) 
                            t1.print_balance();
                        else
                            System.out.println("\n WRONG PIN \n");

                        break;
                    }

                    // for buying stocks 
                    case 2: {
                        System.out.println("Plese enter your pin");
                        if (t1.check_pin(sc.nextInt())) {
                            try {
                                t1.buy();
                            } catch (InsufficientBalanceException e) {
                                System.out.println(e);
                            }
                        } else
                            System.out.println("\n WRONG PIN \n");

                        break;
                    }

                    // for selling stocks
                    case 3: {
                        System.out.println("Plese enter your pin");
                        if (t1.check_pin(sc.nextInt())) 
                            t1.sell();
                        else
                            System.out.println("\n WRONG PIN \n");
                        break;
                    }

                    // for withdrawing money
                    case 4: {
                        System.out.println("Plese enter your pin");
                        if (t1.check_pin(sc.nextInt())) {
                            System.out.println("How much money do you want to withdraw");
                            try {
                                t1.withdraw(sc.nextInt());
                            } catch (InsufficientBalanceException e) {
                                System.out.println(e);
                            }
                        } else
                            System.out.println("\n WRONG PIN \n");
                        break;
                    }

                    // for depositing money
                    case 5: {
                        System.out.println("Plese enter your pin");
                        if (t1.check_pin(sc.nextInt())) {
                            System.out.println("How much money do you want to deposit");
                            try {
                                t1.deposit(sc.nextInt());
                            } catch (DepositLimitException o) {
                                System.out.println(o);
                            }
                        } else
                            System.out.println("\n WRONG PIN \n");      
                        break;
                    }

                    // for switching to mainoptions
                    case 6: {
                        MainOptions();
                        Vyapaar(sc.nextInt(), t1);
                        return;
                    }
                    case 7: return;
                }
                break;
            }

            // case 2 of main options : change pincode
            case 2: {
                System.out.print("Enter your name : ");
                if(sc.nextLine().equals(t1.name)) {
                    System.out.print("Enter your mobile no. : ");
                    if(sc.nextLine().equals(t1.mobile_number)){
                        System.out.print("Enter new pincode :");
                        t1.pincode = sc.nextInt();
                        System.out.println("**Your pincode has successfully changed**");
                    }
                    else {
                        System.out.println("Incurrect mobile number");
                    }
                } else {
                    System.out.println("Wrong name");
                }
                break;
            }

            // for changing mobile number
            case 3: {
                System.out.print("Enter your name : ");
                if(sc.nextLine().equals(t1.name)) {
                    System.out.print("Enter your pincode : ");
                    if(sc.nextInt()==(t1.pincode)){
                        System.out.print("Enter new mobile number :");
                        t1.mobile_number = sc.next();
                        System.out.println("**Your mobile number has successfully changed**");
                    }
                    else {
                        System.out.println("Incurrect pincode number");
                    }
                } else {
                    System.out.println("Wrong name");
                }
                break;
            }

            // for printing data sheet
            case 4: {
                // databook;
                System.out.print("Enter your mobile no. : ");
                if(sc.nextLine().equals(t1.mobile_number)){
                    System.out.println("Enter your pincode : ");
                    if(sc.nextInt()==t1.pincode) {
                        System.out.print("Datasheet is printing :");
                        t1.printDataSheet();
                    }
                    else {
                        System.out.println("Incurrect mobile number");
                    }
                } else {
                    
                    System.out.println("Wrong name");
                }
                break;
            }

            default: {
                return;
            }
        }
        
        // In the last of process
        System.out.println("Do you want to process further, If yes then press 1 otherwise anything");
        if (sc.next().equals("1")) {
            MainOptions();
            Vyapaar(sc.nextInt(), t1);
        }
        
    }

}

// the main trading class
class Trading extends Accounting {

    // creating object of Scanner class
    Scanner sc = new Scanner(System.in);

    private int myStocks;

    public int get_myStock() {
        return myStocks;
    }

    public void print_mystocks() {
        System.out.println(get_myStock());
    }

    public void set_myStocks(int a) {
        myStocks = a;
    }

    public void Print_Tradings() {
        System.out.println(" Press 1: Check balance \n Press 2: Buy \n Press 3: Sell");
        System.out.println(" Press 4: withdraw \n Press 5: Deposit \n Press 6: Main menu \n Press 7: Exit");
    }

    // for buying stocks
    public void buy() throws InsufficientBalanceException {

        int stock = (int) (Math.random() * (9980) + 20);
        System.out.println("The value of one Stock is Rs. " + stock);
        System.out.println("How many stocks do you want to buy");
        int noOfStocks = sc.nextInt();

        if (noOfStocks * stock > this.get_balance()) {
            throw new InsufficientBalanceException("InsufficientBalanceException : you have not sufficient balance");
        } else {
            int kharcha = this.get_balance() - noOfStocks * stock;
            this.set_balance(kharcha);
        }
        System.out.println("[YOU HAVE SUCCESSFULLY BOUGHT " + noOfStocks + " stocks ]");
        int maza = this.get_myStock() + noOfStocks;
        this.set_myStocks(maza);
    }

    // for selling stocks
    public void sell() {

        int stock = (int) (Math.random() * (9980) + 20);
        System.out.println("The value of one Stock is Rs. " + stock);
        System.out.println("You have " + this.get_myStock() + " stocks, How many do you want to sell");
        int noOfStocks = sc.nextInt();
        if (noOfStocks <= this.get_myStock()) {
            int kharcha2 = this.get_balance() + noOfStocks * stock;
            this.set_balance(kharcha2);
            System.out.println("[YOU HAVE SUCCESSFULLY SOLD " + noOfStocks + " STOCKS]");
            int maza2 = this.get_myStock() - noOfStocks;
            this.set_myStocks(maza2);
        } else {
            System.out.println("[YOU DON'T HAVE " + noOfStocks + " STOCKS ]");
        }
    }

    // function for printing datasheet
    public void printDataSheet(){
        System.out.println("\t\tName : "+this.name);
        System.out.println("\t\tAccount number : "+this.account_number);
        System.out.println("\t\tMobile Number : "+this.mobile_number);
        System.out.println("\t\tAccount balance : "+this.get_balance());
        System.out.println("\t\tNo. of Stocks : "+this.get_myStock());

    }

}

// Account class to handle accounting processes
class Accounting  {

    protected String name;
    protected String mobile_number;
    protected int pincode;
    protected int account_number;
    public void save(String name, String mobile_number, int pincode) {
        this.name = name;
        this.mobile_number = mobile_number;
        this.pincode = pincode;
        // this.account_number = account_number;
    }


    protected int acc_balance = 10000;

    public int get_balance() {
        return this.acc_balance;
    }

    public void print_balance() {
        System.out.println("your account balance is " + this.acc_balance);
    }

    public void set_balance(int a) {
        this.acc_balance = a;
    }

    public void deposit(int money) throws DepositLimitException {
        if (money > 10000) {
            throw new DepositLimitException("DepositLimitException : can't deposit more than 10000 at a time");
        }
        this.acc_balance = acc_balance + money;
    }

    public void withdraw(int money) throws InsufficientBalanceException {
        if (acc_balance < money) {
            throw new InsufficientBalanceException("InsufficientBalanceException : you have not sufficient balance");
        } else {
            this.acc_balance = acc_balance - money;
        }
    }

    public boolean check_pin(int a) {
        if (this.pincode == a) {
            return true;
        } else
            return false;
    }
}

// the exception classes
class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        System.out.println(message);
    }
}

// the exception classes
class DepositLimitException extends Exception {
    DepositLimitException(String message) {
        System.out.println(message);
    }
}

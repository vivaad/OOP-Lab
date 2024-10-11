class BankAccount {
    private String depositorName, accountNumber, accountType;
    private double balance;
    private static final double RATE_OF_INTEREST = 5.0;

    public BankAccount() {
        this("", "", "", 0.0);
    }

    public BankAccount(String depositorName, String accountNumber, String accountType, double balance) {
        this.depositorName = depositorName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
        System.out.println(amount > 0 ? "Deposited: " + amount : "Deposit amount must be positive.");
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= 500.0) balance -= amount;
        System.out.println(amount > 0 ? (balance - amount >= 500.0 ? "Withdrawn: " + amount : "Insufficient balance or withdrawal amount exceeds allowed limit.") : "Withdrawal amount must be positive.");
    }

    public void displayDetails() {
        System.out.println("Account Holder's Name: " + depositorName + "\nAccount Number: " + accountNumber + "\nAccount Type: " + accountType + "\nBalance: " + balance);
    }

    public static void displayRateOfInterest() {
        System.out.println("Rate of Interest: " + RATE_OF_INTEREST + "%");
    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount();
        account1.displayDetails();
        account1.deposit(1000);
        account1.withdraw(200);
        account1.displayDetails();

        BankAccount account2 = new BankAccount("John Doe", "123456789", "Saving", 1500);
        account2.displayDetails();
        account2.deposit(500);
        account2.withdraw(100);
        account2.displayDetails();

        displayRateOfInterest();
    }
}

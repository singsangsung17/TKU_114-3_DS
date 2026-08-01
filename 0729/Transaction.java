public class Transaction {
    private String transactionId;
    private String accountId;
    private int amount;
    private int sequence;

    public Transaction(String transactionId, String accountId, int amount, int sequence) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.sequence = sequence;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public String toString() {
        return String.format(
            "%-6s 帳號 %-10s 金額 %-8d 時間序號 %-5d",
            transactionId, accountId, amount, sequence
        );
    }
}

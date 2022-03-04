// "static void main" must be defined in a public class.
/*
A few notes:
- The same withdrawal or deposit cannot be used for multiple different transfers. If there's a case where a given withdrawal or deposit can be matched with multiple possible transfers, use the first occurrence in the list.
- A transfer can only be made between different wallets.

For example, given:
[
	('tx_id_1', 'wallet_id_1', '2020-01-01 15:30:20 UTC', 'out', 5.3),  # 5.3 BTC was withdrawn out of 'wallet_id_1'
	('tx_id_2', 'wallet_id_1', '2020-01-03 12:05:25 UTC', 'out', 3.2),  # 3.2 BTC was withdrawn out of 'wallet_id_1'
	('tx_id_3', 'wallet_id_2', '2020-01-01 15:30:20 UTC', 'in', 5.3),   # 5.3 BTC was deposited into 'wallet_id_2'
	('tx_id_4', 'wallet_id_3', '2020-01-01 15:30:20 UTC', 'in', 5.3),   # 5.3 BTC was deposited into 'wallet_id_3'
]

Expected output:
[
	('tx_id_1', 'tx_id_3'),
]

Add a few tests to verify your implementation works on a variety of input

*/
public class Main {
    
    public static class Transaction{
        
        String txid;
        String walletid;
        String timestamp;
        String action;
        double amount;
        
        public Transaction(String txid, String walletid, String timestamp, String action, double amount){
            this.txid = txid;
            this.walletid = walletid;
            this.timestamp = timestamp;
            this.action = action;
            this.amount = amount;
            
        }
        
    }
    
    public static HashMap<String,String> findMatchingTransactions(List<Transaction> transactions){
        // given a list of transactions, find the matching transactions
        /*
        for two transactions to match:
            - same date
            - same btc amount
            - one transaction has to be in and the other transaction has to be out
        add to hashmap? if we see the correct counterpart - return to list and remove from hashmap?
        */
        HashMap<String,String> matchingtrx = new HashMap<String,String>();
        
        HashSet<Transaction> trxset = new HashSet<Transaction>();
        
        
        for(int i=0; i<transactions.size();i++){
            
            for(int j=i+1; j<transactions.size(); j++){
                                
                if(isTransactionsMatch  (transactions.get(i) , transactions.get(j) ) ){
                    
                    // if a transaction is already a key or value in a map, skip it
                    if(
                           matchingtrx.containsKey(transactions.get(i).txid) 
                        || matchingtrx.containsValue(transactions.get(i).txid)
                        || matchingtrx.containsValue(transactions.get(j).txid)
                        || matchingtrx.containsValue(transactions.get(j).txid)
                      
                      ){
                        continue;
                    }
                    
                    // if it's been matched, don't replace it
                   // System.out.println("matching these two transactions: " + transactions.get(i).txid + " and " + transactions.get(j).txid);
                    matchingtrx.put(transactions.get(i).txid , transactions.get(j).txid );
                }
            }
        }
        
        
        
        return matchingtrx;
        
    }
    
    // method to check if two transactions are a match
    public static boolean isTransactionsMatch(Transaction trx1, Transaction trx2){
        // trx1.timestamp == trx2.timestamp && (Double.compare(trx1.amount, trx2.amount) == 0) && trx1.action != trx2.action
        if(trx1.timestamp == trx2.timestamp && (Double.compare(trx1.amount, trx2.amount) == 0) && trx1.action != trx2.action){
            return true;
        }
        
        return false;
    }
    
    
    public static void main(String[] args) {
        
        // data setup for test case 1: positive test with multiple possible matches
        
        Transaction newtrx1 = new Transaction("tx_id_1", "wallet_id_1", "2020-01-01 15:30:20 UTC", "out", 5.3);
        Transaction newtrx2 = new Transaction("tx_id_2", "wallet_id_1", "2020-01-03 12:05:25 UTC", "out", 3.2);
        Transaction newtrx3 = new Transaction("tx_id_3", "wallet_id_2", "2020-01-01 15:30:20 UTC", "in", 5.3);
        Transaction newtrx4 = new Transaction("tx_id_4", "wallet_id_3", "2020-01-01 15:30:20 UTC", "in", 5.3);
        Transaction newtrx5 = new Transaction("tx_id_5", "wallet_id_1", "2020-01-01 15:30:20 UTC", "out", 5.3);
        Transaction newtrx6 = new Transaction("tx_id_6", "wallet_id_1", "2020-01-03 12:05:25 UTC", "out", 3.2);
        Transaction newtrx7 = new Transaction("tx_id_7", "wallet_id_2", "2020-01-01 15:30:20 UTC", "in", 5.3);
        Transaction newtrx8 = new Transaction("tx_id_8", "wallet_id_3", "2020-01-01 15:30:20 UTC", "in", 5.3);
        Transaction newtrx9 = new Transaction("tx_id_9", "wallet_id_1", "2020-01-01 15:30:20 UTC", "out", 5.3);
        Transaction newtrx10 = new Transaction("tx_id_10", "wallet_id_1", "2020-01-03 12:05:25 UTC", "out", 3.2);
        Transaction newtrx11 = new Transaction("tx_id_11", "wallet_id_2", "2020-01-01 15:30:20 UTC", "in", 5.3);
        Transaction newtrx12 = new Transaction("tx_id_12", "wallet_id_3", "2020-01-01 15:30:20 UTC", "in", 5.3);
        
        List<Transaction> test_set_1 = new ArrayList<Transaction>();
        
        test_set_1.add(newtrx1);
        test_set_1.add(newtrx2);
        test_set_1.add(newtrx3);
        test_set_1.add(newtrx4);
        test_set_1.add(newtrx5);
        test_set_1.add(newtrx6);
        test_set_1.add(newtrx7);
        test_set_1.add(newtrx8);
        test_set_1.add(newtrx9);
        test_set_1.add(newtrx10);
        test_set_1.add(newtrx11);
        test_set_1.add(newtrx12);
        
        
        // data set up for test case 2: no matches
        
        Transaction newtrx2_1 = new Transaction("tx_id_1", "wallet_id_1", "2020-01-01 15:30:20 UTC", "out", 100);
        Transaction newtrx2_2 = new Transaction("tx_id_2", "wallet_id_1", "2020-01-03 12:05:25 UTC", "out", 1);

        
        List<Transaction> test_set_2 = new ArrayList<Transaction>();
        
        test_set_2.add(newtrx2_1);
        test_set_2.add(newtrx2_2);
        
        List<Transaction> test_set_2 = new ArrayList<Transaction>();
        
        test_set_2.add(newtrx2_1);
        test_set_2.add(newtrx2_2);
        
        
        // call driver method to determine which transactions match
        System.out.println(findMatchingTransactions(test_set_1));    
        System.out.println(findMatchingTransactions(test_set_2));

    }
    
}

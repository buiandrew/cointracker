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
                
                System.out.println("comparing these two: " + transactions.get(i).walletid + " " + transactions.get(j).walletid );
                
                if(isTransactionsMatch  (transactions.get(i) , transactions.get(j) ) ){
                    System.out.println("found a match at i,j: " + i + " " + j);
                    
                    // if it's been matched, don't replace it
                    if(matchingtrx.containsKey(transactions.get(i).txid)){
                        continue;
                    }
                    
                    matchingtrx.put(transactions.get(i).txid , transactions.get(j).txid );
                }
            }
        }
        
        
        
        return matchingtrx;
        
    }
    
    // method to check if two transactions are a match
    public static boolean isTransactionsMatch(Transaction trx1, Transaction trx2){
        System.out.println("method: checking match...");
        // check amounts are same
        System.out.println(trx1.timestamp);
        System.out.println(trx2.timestamp);
        // trx1.timestamp == trx2.timestamp && (Double.compare(trx1.amount, trx2.amount) == 0) && trx1.action != trx2.action
        if(trx1.timestamp == trx2.timestamp && (Double.compare(trx1.amount, trx2.amount) == 0) && trx1.action != trx2.action){
            return true;
        }
        
        return false;
    }
    
    
    public static void main(String[] args) {
        
        // data setup
        Transaction newtrx1 = new Transaction("tx_id_1", "wallet_id_1", "2020-01-01 15:30:20 UTC", "out", 5.3);
        Transaction newtrx2 = new Transaction("tx_id_2", "wallet_id_1", "2020-01-03 12:05:25 UTC", "out", 3.2);
        Transaction newtrx3 = new Transaction("tx_id_3", "wallet_id_2", "2020-01-01 15:30:20 UTC", "in", 5.3);
        Transaction newtrx4 = new Transaction("tx_id_4", "wallet_id_3", "2020-01-01 15:30:20 UTC", "in", 5.3);
        
        List<Transaction> trxlist = new ArrayList<Transaction>();
        trxlist.add(newtrx1);
        trxlist.add(newtrx2);
        trxlist.add(newtrx3);
        trxlist.add(newtrx4);
        
        // call driver method to determine which transactions match
        System.out.println(findMatchingTransactions(trxlist));
        
    }
    
    
    // algorithm to take JSON and return matching transaction IDs
    
    
    
    
    
}

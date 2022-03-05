public class Main {
    
    // This class stores each Transaction Object
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
    
    // Given a list of withdrawals and desposits (List of Transaction Objects), detect the likely transfers amongst them.
    public static HashMap<String,String> findMatchingTransactions(List<Transaction> transactions){

        HashMap<String,String> matchingtrx = new HashMap<String,String>();
        
        for(int i=0; i<transactions.size();i++){
            
            // if this transaction is already in the result map, continue
            if(   matchingtrx.containsKey(transactions.get(i).txid) 
               || matchingtrx.containsValue(transactions.get(i).txid)){
                continue;
            }
            
            for(int j=i+1; j<transactions.size(); j++){
                
                // if transaction.get(j) is already in the result map, continue
                if(   matchingtrx.containsKey(transactions.get(j).txid) 
                   || matchingtrx.containsValue(transactions.get(j).txid)){
                    continue;
                }
                                
                if(isTransactionsMatch  (transactions.get(i) , transactions.get(j) ) ){
                    // add the matching transactions pair into the result map
                    matchingtrx.put(transactions.get(i).txid , transactions.get(j).txid );
                    }

            }
        }
        
        return matchingtrx;
    }
    
    // method to check if two transactions are a match
    public static boolean isTransactionsMatch(Transaction trx1, Transaction trx2){
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
        
        
        // call driver method to determine which transactions match
        System.out.println(findMatchingTransactions(test_set_1));    
        System.out.println(findMatchingTransactions(test_set_2));

    }
    
}

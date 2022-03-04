# cointracker
Howdy! Thanks for checking out my cointracker take home assignment. Please read below to understand the problem and my approach.

_____________________________________________________________________________________________________________________________________________________________________________

Instructions:
Given a list of withdrawals and desposits, detect the likely transfers amongst them.

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
_____________________________________________________________________________________________________________________________________________________________________________

Approach:

I currently do not have JSON parsing libraries installed, so I went with declaring an object for each transaction, and driving the matching algorithm by accessing each object's data. With more time, it should be simple to introduce a JSON parsing step to consume data easier. This is a brute force approach by checking each transaction against all other transactions. If two transactions are a determined match, they are stored in a map to return. Two transactions are determined a match if they have the same amount of BTC, opposite set of actions (in vs out), and the same transaction date. Also, a transaction cannot be a part of multiple matches.

Future approach:

To build upon this, I would explore a way to develop so that I do not need to perform this algorithm in O(n^2) time. There may be a way to store each transaction into a hashset, and query the hashset for a suitable match, so the lookup is quicker than looking at each other transaction.

_____________________________________________________________________________________________________________________________________________________________________________


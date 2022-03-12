
CREATE TABLE IF NOT EXISTS public.IR_Master
(
"IR-NO" VARCHAR(10),
"NATURE-OF-REMITTANCE"	NUMERIC(3),
"NATURE-OF-REMITTANCE-SUB-CODE" 	VARCHAR(1),
"PAID-STATS"	NUMERIC(1),
"PRINT-ADVISING-MK" VARCHAR(1),
"BE-ADVISING-BRANCH" NUMERIC(3),
"PROCESS-BRANCH" NUMERIC(3),
"OUR-CUSTOMER" VARCHAR(1),
"CUSTOMER-ID" VARCHAR(11),
"CURRENCY" VARCHAR(3),
"IR-AMT" NUMERIC(15,2),
"FX-DEPOSIT" NUMERIC(15,2),
"SPOT-SETTLED-FX"	NUMERIC(15,2),
"COMM-CHARGE"	NUMERIC(11,2),
"TO-US-FXRATE"	NUMERIC(9,5),
"SENDER-REFERENCE"	VARCHAR(16),
"REMITTER-INFO-1"  	VARCHAR(35),
"REMITTER-INFO-2"  VARCHAR(35),
"REMITTER-INFO-3" VARCHAR(35),
"REMITTER-INFO-4" VARCHAR(35),
"BENEFICIARY-AC"	VARCHAR(35),
"BENEFICIARY-INFO-1"	VARCHAR(35),
"BENEFICIARY-INFO-2" VARCHAR(35),
"BENEFICIARY-INFO-3" VARCHAR(35),
"BENEFICIARY-INFO-4" VARCHAR(35),
"CHARGE-TYPE"	VARCHAR(3),
"REMIT-BANK"  VARCHAR(11),
"REMIT-BANK-INFO1" VARCHAR(35),
"REMIT-BANK-INFO2" VARCHAR(35),
"REMIT-BANK-INFO3" VARCHAR(35),
"REMIT-BANK-INFO4" VARCHAR(35),
"DEPOSIT-BANK" VARCHAR(11),
"VALUE-DATE" VARCHAR(8),
"ADVISING-DATE"	VARCHAR(8),
"PRINT-ADVISING-DATE"	VARCHAR(8),
"PROCESS-DATE"	VARCHAR(8),
"CUSTOMER-TEL-NO" VARCHAR(15),
"ACCOUNT-NO" VARCHAR(11),
"EXCHANGE-RATE"	NUMERIC(10,6),
"BENEFICIARY-KIND"	VARCHAR(2),
"CUSTOMER-BIRTH-DATE"	VARCHAR(8),
"NOTICE-1"	NUMERIC(2),
"NOTICE-2"	NUMERIC(2),
"NOTICE-3"	NUMERIC(2)
);


CREATE TABLE IF NOT EXISTS public.IR_SWIFT_MESSAGE
(
"SEQ-NO"   NUMERIC(6),
"STATS"   NUMERIC(1),
"TX-TIME"   	NUMERIC(7),
"CURRENCY"   	VARCHAR(3),
"IR-AMT"   NUMERIC(15,2),
"SENDER-REFERENCE"   VARCHAR(16),
"ORDERING-INSTITUTION"     VARCHAR(11),
"ORDERING-INSTITUTION-1"   VARCHAR(35),
"ORDERING-INSTITUTION-2"   VARCHAR(35),
"ORDERING-INSTITUTION-3"   VARCHAR(35),
"ORDERING-INSTITUTION-4"   VARCHAR(35),
"ACCOUNT-INSTITUTION" VARCHAR(11),
"ACCOUNT-INSTITUTION-AC"   	VARCHAR(35),
"ACCOUNT-INSTITUTION-INFO-1"   	VARCHAR(35),
"ACCOUNT-INSTITUTION-INFO-2"   	VARCHAR(35),
"ACCOUNT-INSTITUTION-INFO-3"   	VARCHAR(35),
"ACCOUNT-INSTITUTION-INFO-4"   	VARCHAR(35),
"BENEFICIARY-AC"   	VARCHAR(35),
"BENEFICIARY-INFO-1"   VARCHAR(35),
"BENEFICIARY-INFO-2"   VARCHAR(35),
"BENEFICIARY-INFO-3"   VARCHAR(35),
"BENEFICIARY-INFO-4"   VARCHAR(35),
"REMITTER-INFO-1"		VARCHAR(35),
"REMITTER-INFO-2"		VARCHAR(35),
"REMITTER-INFO-3"		VARCHAR(35),
"REMITTER-INFO-4"		VARCHAR(35),
"CHARGE-TYPE"   VARCHAR(3),
"SENDER-CHARGE-CURRENCY-1" VARCHAR(3),
"SENDER-CHARGE-1" NUMERIC(7,2),
"SENDER-CHARGE-CURRENCY-2" VARCHAR(3),
"SENDER-CHARGE-2" NUMERIC(7,2),
"SENDER-CHARGE-CURRENCY-3" VARCHAR(3),
"SENDER-CHARGE-3" NUMERIC(7,2),
"NOTICE-1"   	NUMERIC(2),
"NOTICE-2"   	NUMERIC(2),
"NOTICE-3"   	NUMERIC(2),
"REMIT-BANK"   	VARCHAR(11),
"REMIT-BANK-INFO1" VARCHAR(35),
"REMIT-BANK-INFO2" VARCHAR(35),
"REMIT-BANK-INFO3" VARCHAR(35),
"REMIT-BANK-INFO4" VARCHAR(35),
"DEPOSIT-BANK"			VARCHAR(11),
"VALUE-DATE"   	NUMERIC(8),
"RECEIVE-DATE"   	NUMERIC(8),
"NST-VST-MK"   				VARCHAR(1),
);
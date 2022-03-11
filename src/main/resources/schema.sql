
CREATE TABLE IF NOT EXISTS public.IR_Master
(
"IR-NO" VARCHAR(10),
"IR-MARK"	NUMERIC(3),
"PAID-STATS"	NUMERIC(1),
"PRINT-ADV-MK" VARCHAR(1),
"BE-ADV-BRANCH" NUMERIC(3),
"PROCESS-BRANCH" NUMERIC(3),
"OUR-CUST" VARCHAR(1),
"CUSTOMER-ID" VARCHAR(11),
"CURENCY" NUMERIC(3),
"IR-AMT" NUMERIC(15,2),
"FX-DEPOSIT" NUMERIC(15,2),
"SPOT-SETTLED-FX"	NUMERIC(15,2),
"COMM-CHARGE"	NUMERIC(11,2),
"TO-US-FXRATE"	NUMERIC(9,5),
"SW20"	VARCHAR(16),
"SW50-1"  	VARCHAR(35),
"SW50-2"  VARCHAR(35),
"SW50-3" VARCHAR(35),
"SW50-4" VARCHAR(35),
"SW59-AC"	VARCHAR(35),
"SW59-1"	VARCHAR(35),
"SW59-2" VARCHAR(35),
"SW59-3" VARCHAR(35),
"SW59-4" VARCHAR(35),
"SW71A"	VARCHAR(3),
"REMIT-BANK"  VARCHAR(11),
"REMIT-BK-NAME1" VARCHAR(35),
"REMIT-BK-NAME2" VARCHAR(35),
"DEPOSIT-BANK" VARCHAR(11),
"VALUE-DATE" VARCHAR(8),
"ADV-DATE"	VARCHAR(8),
"PRINT-ADV-DATE"	VARCHAR(8),
"PPOCESS-DATE"	VARCHAR(8),
"CUST-TEL-NO" VARCHAR(15),
"AC-NO" VARCHAR(11),
"EXCHANGE-RATE"	NUMERIC(10,6),
"SW71F-CUR-1"	VARCHAR(3),
"SW71F-1"	NUMERIC(15,2),
"SW71F-CUR-2"	VARCHAR(3),
"SW71F-2"	NUMERIC(15,2),
"SW71F-CUR-3"	VARCHAR(3),
"SW71F-3"	NUMERIC(15,2),
"BENE-KIND"	VARCHAR(2),
"690-SUB-CODE" 	VARCHAR(1),
"CUS-BIRTH-DATE"	VARCHAR(8),
"NOTICE-1"	NUMERIC(2),
"NOTICE-2"	NUMERIC(2),
"NOTICE-3"	NUMERIC(2)
);
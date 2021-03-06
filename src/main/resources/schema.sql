
CREATE TABLE IF NOT EXISTS public.IR_Master
(
"IR_NO" VARCHAR(10),
"NATURE_OF_REMITTANCE"	VARCHAR(3),
"NATURE_OF_REMITTANCE_SUB_CODE" 	VARCHAR(1),
"PAID_STATS"	NUMERIC(1),
"PRINT_ADVISING_MK" VARCHAR(1),
"BE_ADVISING_BRANCH" VARCHAR(3),
"PROCESS_BRANCH" NUMERIC(3),
"OUR_CUSTOMER" VARCHAR(1),
"CUSTOMER_ID" VARCHAR(11),
"CURRENCY" VARCHAR(3),
"IR_AMT" NUMERIC(15,2),
"FX_DEPOSIT" NUMERIC(15,2),
"SPOT_SETTLED_FX"	NUMERIC(15,2),
"COMM_CHARGE"	NUMERIC(11,2),
"TO_US_FXRATE"	NUMERIC(9,5),
"SENDER_REFERENCE"	VARCHAR(16),
"REMITTER_INFO-1"  	VARCHAR(35),
"REMITTER_INFO-2"  VARCHAR(35),
"REMITTER_INFO-3" VARCHAR(35),
"REMITTER_INFO-4" VARCHAR(35),
"BENEFICIARY_AC"	VARCHAR(35),
"BENEFICIARY_INFO-1"	VARCHAR(35),
"BENEFICIARY_INFO-2" VARCHAR(35),
"BENEFICIARY_INFO-3" VARCHAR(35),
"BENEFICIARY_INFO-4" VARCHAR(35),
"CHARGE_TYPE"	VARCHAR(3),
"REMIT_BANK"  VARCHAR(11),
"REMIT_BANK_INFO1" VARCHAR(35),
"REMIT_BANK_INFO2" VARCHAR(35),
"REMIT_BANK_INFO3" VARCHAR(35),
"REMIT_BANK_INFO4" VARCHAR(35),
"DEPOSIT_BANK" VARCHAR(11),
"VALUE_DATE" DATE,
"ADVISING_DATE"	DATE,
"PRINT_ADVISING_DATE"	DATE,
"PROCESS_DATE"	DATE,
"CUSTOMER_TEL_NO" VARCHAR(15),
"ACCOUNT_NO" VARCHAR(11),
"EXCHANGE_RATE"	NUMERIC(10,6),
"BENEFICIARY_KIND"	VARCHAR(2),
"CUSTOMER_BIRTH_DATE"	DATE,
"NOTICE_1"	NUMERIC(2),
"NOTICE_2"	NUMERIC(2),
"NOTICE_3"	NUMERIC(2),
"STATUS"    VARCHAR(1)
);


CREATE TABLE IF NOT EXISTS public.IR_SWIFT_MESSAGE
(
"SEQ_NO"   NUMERIC(6),
"STATS"   NUMERIC(1),
"TX_TIME"   	NUMERIC(6),
"CURRENCY"   	VARCHAR(3),
"IR_AMT"   NUMERIC(15,2),
"SENDER_REFERENCE"   VARCHAR(16),
"ORIGIN_REMIT_SWIFT_TID"   VARCHAR(11),
"ORIGIN_REMIT_BANK_INFO_1"   VARCHAR(35),
"ORIGIN_REMIT_BANK_INFO_2"   VARCHAR(35),
"ORIGIN_REMIT_BANK_INFO_3"   VARCHAR(35),
"ORIGIN_REMIT_BANK_INFO_4"   VARCHAR(35),
"ACCOUNT_SWIFT_TID" VARCHAR(11),
"ACCOUNT_BANK_INFO_1"   	VARCHAR(35),
"ACCOUNT_BANK_INFO_2"   	VARCHAR(35),
"ACCOUNT_BANK_INFO_3"   	VARCHAR(35),
"ACCOUNT_BANK_INFO_4"   	VARCHAR(35),
"BENEFICIARY_AC"   	VARCHAR(11),
"BENEFICIARY_INFO_1"    VARCHAR(35),
"BENEFICIARY_INFO_2"    VARCHAR(35),
"BENEFICIARY_INFO_3"    VARCHAR(35),
"BENEFICIARY_INFO_4"    VARCHAR(35),
"REMITTER_INFO_1"		VARCHAR(35),
"REMITTER_INFO_2"		VARCHAR(35),
"REMITTER_INFO_3"		VARCHAR(35),
"REMITTER_INFO_4"		VARCHAR(35),
"CHARGE_TYPE"   VARCHAR(3),
"SENDER_CHARGE_CURRENCY_1" VARCHAR(3),
"SENDER_CHARGE_1" NUMERIC(7,2),
"SENDER_CHARGE_CURRENCY_2" VARCHAR(3),
"SENDER_CHARGE_2" NUMERIC(7,2),
"SENDER_CHARGE_CURRENCY_3" VARCHAR(3),
"SENDER_CHARGE_3" NUMERIC(7,2),
"NOTICE_1"   	NUMERIC(2),
"NOTICE_2"   	NUMERIC(2),
"NOTICE_3"   	NUMERIC(2),
"REMIT_SWIFT_TID"  VARCHAR(11),
"REMIT_BANK_INFO_1"  VARCHAR(35),
"REMIT_BANK_INFO_2"  VARCHAR(35),
"REMIT_BANK_INFO_3"  VARCHAR(35),
"REMIT_BANK_INFO_4"  VARCHAR(35),
"DEPOSIT_SWIFT_TID"			VARCHAR(11),
"VALUE_DATE"   	DATE,
"RECEIVE_DATE"   	DATE,
"NST_VST_MK"   				VARCHAR(1)
);


CREATE TABLE IF NOT EXISTS public.FXRATE_TABLE
(
"DATE"      VARCHAR(10),
"CURRENCY"  VARCHAR(3),
"SPOT_BOUGH_FXRATE"   VARCHAR(10),
"COST_SPOT_BOUGH_FXRATE"  VARCHAR(11),
"COST_SPOT_SOLD_FXRATE"   VARCHAR(11),
"SPOT_SOLD_FXRATE"  VARCHAR(10)
);
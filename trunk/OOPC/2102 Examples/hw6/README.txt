Sample files for Homework 6 are found in this package:

(1) prices.dat contains 30 days worth of trading information for the daily activity 
    of a set of 30 securities (labeled Security_0 through Security_29). The format
    of the file is:

    The first five lines of the file are:

Id   TradeDate   HighPrice   LowPrice   ClosePrice   OpenPrice   Volume
Security_0   12/6/2006   78.75   75   75   75   1716575433
Security_1   12/6/2006   69.68   62.98   67   67   190931991
Security_2   12/6/2006   36.04   32.01   33.66   34   1376541906
Security_3   12/6/2006   46.01   41.71   43   43   1001003073
...

	The columns in the input file are:
	
	   * Id         - The identifier of the Security (Security_0 through Security_29)
	   * TradeDate  - The date on which the trade information was recorded.
	   * HighPrice  - The high price for the day
	   * LowPrice   - The low price for the day
	   * ClosePrice - The closing price of the security at the end of the day
	   * OpenPrice  - The price of the security at the beginning of the day
	   * Volume     - The total number of shares of the security that were sold on this day
	  
(2) securities.dat contains the information about the 30 securities (labeled Security_0 through
    Security_29).
    
    The first five lines of the file are:
    
Id   Ex   SIC   SPR   Currency
Security_0   O      MEDICAL   B   USD
Security_1   AM      BANKING   AAA   FFR
Security_2   LN      MEDICAL   B   USD
Security_3   LN      ENTERTAINMENT   B   GBP
    
    The columns in this input file are:
    
      * Id          - The identifier of the Security (Security_0 through Security_29)
      * Ex          - The exchange that sells the security (NY for new york, LN for London)
      * SIC         - The sector for which the security is classified
      * SPR         - Standard and Poor's Bond Rating [http://www.atlanticfinancial.com/pdf_forms/fidelity_investments/the_case_for_investing_in_high-yield_viewpoint.pdf]
      * Currency    - The currency by which this security is bought/sold.
 
    An investment grade security is rated as AAA, AA, A, or BBB.
    

    
package databean;

import org.genericdao.PrimaryKey;

import java.util.Date;

@PrimaryKey("transactionId")
public class TransactionBean {
	
	private int transactionId;
	private int customerId;
	private int fundId;
	private Date executeDate;
	private long shares;
	private String transactionType;
	private long amount;
	
	public int getTransactionId()   	{ return transactionId;   }
	public int getCustomerId()   		{ return customerId;   	  }
	public int getFundId()   			{ return fundId;   	      }
	public Date getExecuteDate()   		{ return executeDate;     }
	public long getShares()   			{ return shares;   	      }
	public String getTransactionType()  { return transactionType; }
	public long getAmount()   			{ return amount;   	      }
	
	public void setTransactionId(int t)       { transactionId = t;    }
	public void setCustomerId(int c)          { customerId = c;       }
	public void setFundId(int f)      	      { fundId = f;           }
	public void setExecuteDate(Date d)        { executeDate = d;      }
	public void setShares(long s)      	      { shares = s;           }
	public void getTransactionType(String y)  { transactionType = y;  }
	public void setAmount(long m)      	      { amount = m;           }
	
}

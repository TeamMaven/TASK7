package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId,customerId")
public class PositionBean {
	
	private int customerId;
	private int fundId;
	private long shares;
	
	public int getCustomerId()   	{ return customerId;   }
	public int getFundId()   		{ return fundId;   	   }
	public long getShares()   		{ return shares;   	   }
	
	public void setCustomerId(int c)      { customerId = c;    }
	public void setFundId(int f)      	  { fundId = f;        }
	public void setShares(long s)      	  { shares = s;        }

}

package databean;

import org.genericdao.PrimaryKey;
import java.util.Date;

@PrimaryKey("priceDate,fundId")
public class HistoryBean {
	
	private int fundId;
	private Date priceDate;
	private long price;
	
	public int getFundId()   		{ return fundId;   	   }
	public Date getPriceDate()   	{ return priceDate;    }
	public long getPrice()   		{ return price;   	   }
	
	public void setFundId(int f)      	  { fundId = f;        }
	public void setPriceDate(Date d)      { priceDate = d;     }
	public void setPrice(long p)      	  { price = p;         }

}

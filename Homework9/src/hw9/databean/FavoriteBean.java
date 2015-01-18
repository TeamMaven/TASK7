/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("favoriteId")
public class FavoriteBean {
	private int favoriteId;
	private int userId;
	private String url;
	private String comment;
	private int clickCount;
	
	public int getFavoriteId() { return favoriteId; }
    public int getUserId() 	   { return userId;     }
    public String getUrl()     { return url;        }
    public String getComment() { return comment;    }
    public int getClickCount() { return clickCount; }

    public void setFavoriteId(int f) { favoriteId = f; }
	public void setUserId(int u)     { userId = u;     }
	public void setUrl(String u)     { url = u;        }
	public void setComment(String c) { comment = c;    }
	public void setClickCount(int c) { clickCount = c; }
}

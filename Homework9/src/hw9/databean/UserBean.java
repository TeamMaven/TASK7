/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userId")
public class UserBean {
    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public int getUserId()       { return userId;    }
    public String getEmail()     { return email;     }
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName;  }
    public String getPassword()  { return password;  }

    public void setUserId(int i)       { userId = i;    }
    public void setEmail(String e)     { email = e;     }
    public void setFirstName(String f) { firstName = f; }
    public void setLastName(String l)  { lastName = l;  }
    public void setPassword(String p)  { password = p;  }
}

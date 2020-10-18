/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectMSSQL;

/**
 *
 * @author daiya
 */
public class List2 {

    private String sName,sRate,spName,dateTime,phone,email,addrs;
    
    public List2(String sName,String sRate,String spName,String dateTime){
        this.sName=sName;
        this.sRate=sRate;
        this.spName=spName;
        this.dateTime=dateTime;
    }
    
     public List2(String sName,String sRate,String spName,String dateTime,String addrs,String email,String phone){
        this.sName=sName;
        this.sRate=sRate;
        this.spName=spName;
        this.dateTime=dateTime;
        this.addrs=addrs;
        this.email=email;
        this.phone=phone;
    }

    public String getsName() {
        return sName;
    }
    public String getsRate() {
        return sRate;
    }
    public String getSpName() {
        return spName;
    }
    public String getDateTime() {
        return dateTime;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddrs() {
        return addrs;
    }
    
    

   
  
} 

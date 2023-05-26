/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMS;

import java.util.Date;

/**
 *
 * @author frees
 */
public class GetGuestData {
    
    private Integer customerNum;   //objects
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private Double total;
    private Date checkIn;
    private Date checkOut;
    private String roomNum;
    private String roomType;
    private String areaN;
    private String areaT;
    
    
    
    public GetGuestData(int customerNum, String firstName, String lastName, String phoneNumber, String emailAddress ,Double total, Date checkIn, Date checkOut,
                        String roomNum,String roomType,String areaN,String areaT){
        this.customerNum = customerNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.total = total;
        this.checkIn = checkIn;
        this.checkOut = checkOut;      
        this.roomNum = roomNum;      
        this.roomType = roomType;      
        this.areaN = areaN;      
        this.areaT = areaT;      
    }
    
    public Integer getCustomerNum(){
        return customerNum;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public Double getTotal(){
        return total;
    }
    public Date getCheckIn(){
        return checkIn;
    }
    public Date getCheckOut(){
        return checkOut;
    }
    public String getRoomType(){
        return roomType;
    }
    public String getRoomNum(){
        return roomNum;
    }
    public String getAreaType(){
        return areaT;
    }
    public String getAreaNum(){
        return areaN;
    }
    
       
}

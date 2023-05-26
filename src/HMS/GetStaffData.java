package HMS;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yesimyigitbasi
 */
public class GetStaffData {
    private Integer employeeID;
    private String firstName;
    private String lastName;
    private String title;
    private String supervisor;
    private String languages;
    private String phoneNumber;
    
    public GetStaffData(Integer employeeID, String firstName, String lastName, String title, String supervisor, String languages, String phoneNumber)
    {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.supervisor = supervisor;
        this.languages = languages;
        this.phoneNumber = phoneNumber;
    }
    
    public Integer getEmployeeID(){
        return employeeID;
    }
    public String getEmployeeFirstName()
    {
        return firstName;
    }
    public String getEmployeeLastName()
    {
        return lastName;
    }
    public String getEmployeeTitle()
    {
        return title;
    }
    public String getEmployeeSupervisor()
    {
        return supervisor;
    }
    public String getEmployeeLanguages()
    {
        return languages;
    }
    public String getEmployeePhoneNumber()
    {
        return phoneNumber;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMS;

/**
 *
 * @author youse
 */
public class GetFacilitiesData {
    private Integer areaNum;
    private String areaType;
    private String areaStatus;
    private Integer areaCapacity;
    private Double areaPrice;
    
    public GetFacilitiesData(Integer areaNum, String areaType, String areaStatus, Integer areaCapacity, Double areaPrice)
    {
        this.areaNum = areaNum;
        this.areaType = areaType;
        this.areaStatus = areaStatus;
        this.areaCapacity = areaCapacity;      
        this.areaPrice = areaPrice;

    }
    
    public Integer getAreaNum(){
        return areaNum;
    }
    public String getAreaType()
    {
        return areaType;
    }
    public String getStatus()
    {
        return areaStatus;
    }
    public Integer getCapacity()
    {
        return areaCapacity;
    }
    public Double getPrice()
    {
        return areaPrice;
    }
    
}

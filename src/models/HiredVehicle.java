package models;

import utility.FileHandler;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HiredVehicle implements Serializable {

    private static final long serialVersionUID = 6098736424535717790L;
    private static final String FILE_NAME = "hired-vehicles.dat";

    public static final int TYPE_HIRED_VEHICLE = 3000;
    public static final int STATUS_APPROVED = 30001, STATUS_DISAPPROVED = 30002, STATUS_PENDING = 30003,
            STATUS_EXPIRED = 30004;

    private int vehicleRegNo;
    private String username;

    private String additionalInfo;

    private Date requestDate;

    private int status;

    public HiredVehicle(){

    }

    public HiredVehicle(int vehicleRegNo, String username, String additionalInfo, Date requestDate, int status){
        this.vehicleRegNo = vehicleRegNo;
        this.username = username.toLowerCase();
        this.additionalInfo = additionalInfo;
        this.requestDate = requestDate;
        this.status = status;
    }


    public void create() throws FileNotFoundException {
        FileHandler<HiredVehicle> fileHandler = new FileHandler<HiredVehicle>();
        fileHandler.writeObject(this, FILE_NAME, true);
    }

    public boolean delete() {
        FileHandler<HiredVehicle> fileHandler = new FileHandler<HiredVehicle>();
        return fileHandler.deleteMatchingObject(FILE_NAME, this);
    }

    public List<HiredVehicle> getHiredVehiclesList(){
        FileHandler<HiredVehicle> fileHandler = new FileHandler<HiredVehicle>();
        List hiredVehicles = fileHandler.readObjects(FILE_NAME);

        return hiredVehicles;
    }

    public static List<HiredVehicle> getDummyList(){
        List<HiredVehicle> dummyList = new ArrayList<>();
        dummyList.add(new HiredVehicle(5, "SafalFrom2050", "Want to pre-pay for gas.", new Date(), STATUS_PENDING));
        return dummyList;
    }

    public int getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(int vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username.toLowerCase();
        this.username = username;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public int getStatus() {
        return status;
    }

    public String getStringStatus(){
        if(status == STATUS_APPROVED) return "Approved";
        if(status == STATUS_DISAPPROVED) return "Disapproved";
        if(status == STATUS_PENDING) return "Pending";
        if(status == STATUS_EXPIRED) return "Expired";

        return "Not Specified";
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        HiredVehicle hiredVehicle = (HiredVehicle) obj;

        // Vehicle registration number and customer ID both should match
        return (hiredVehicle.getVehicleRegNo() == this.vehicleRegNo && hiredVehicle.getUsername().equalsIgnoreCase(this.username));
    }
}

package travelplan.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author fdse
 */
@Data
public class TestAPIGateway3 {

    private Date travelDate;

    private String trainNumber;

    private String startStation;

    private String destStation;

    private int seatType;

    public TestAPIGateway3(){
        //Default Constructor
    }

}

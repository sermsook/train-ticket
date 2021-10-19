package travelplan.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author fdse
 */
@Data
public class TestAPIGateway11 {

    private Date travelDate;

    private String trainNumber;

    private String startStation;

    private String destStation;

    private int seatType;

    public TestAPIGateway11(){
        //Default Constructor
    }

}

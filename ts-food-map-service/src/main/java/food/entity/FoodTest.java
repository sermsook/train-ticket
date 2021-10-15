package food.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class FoodTest implements Serializable{

    private String foodName;
    private double price;
    public FoodTest(){
        //Default Constructor
    }

}

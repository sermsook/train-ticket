package food.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class FoodTest1 implements Serializable{

    private String foodName;
    private double price;
    public FoodTest1(){
        //Default Constructor
    }

}

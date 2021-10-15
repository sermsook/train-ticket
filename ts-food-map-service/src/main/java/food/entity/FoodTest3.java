package food.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class FoodTest3 implements Serializable{

    private String foodName;
    private double price;
    public FoodTest3(){
        //Default Constructor
    }

}

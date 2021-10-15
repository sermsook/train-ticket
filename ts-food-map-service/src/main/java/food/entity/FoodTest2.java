package food.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class FoodTest2 implements Serializable{

    private String foodName;
    private double price;
    public FoodTest2(){
        //Default Constructor
    }

}

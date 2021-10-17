package com.trainticket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@Document(collection="addMoney")
public class MoneyTest3 {
    private String userId;
    private String money; //NOSONAR

    public MoneyTest3(){
        //Default Constructor
    }

}
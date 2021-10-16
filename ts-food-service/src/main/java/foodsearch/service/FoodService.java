package foodsearch.service;

import edu.fudan.common.util.Response;
import foodsearch.entity.*;
import org.springframework.http.HttpHeaders;


public interface FoodService {

    Response createFoodOrder(FoodOrder afoi, HttpHeaders headers);
    Response deleteFoodOrder(String orderId, HttpHeaders headers);

    Response findByOrderId(String orderId, HttpHeaders headers);

    Response updateFoodOrder(FoodOrder updateFoodOrder, HttpHeaders headers);

    Response findAllFoodOrder(HttpHeaders headers);

    Response getAllFood(String date, String startStation, String endStation, String tripId, HttpHeaders headers);

    Response callFoodMapServiceTestESBUsage(HttpHeaders headers);

}

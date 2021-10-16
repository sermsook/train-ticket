package food.service;

import edu.fudan.common.util.Response;
import food.entity.FoodStore;
import food.entity.TrainFood;
import food.repository.FoodStoreRepository;
import food.repository.TrainFoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class FoodMapServiceImpl implements FoodMapService {

    @Autowired
    FoodStoreRepository foodStoreRepository;

    @Autowired
    TrainFoodRepository trainFoodRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodMapServiceImpl.class);

    String success = "Success";
    String noContent = "No content";

    @Override
    public Response createFoodStore(FoodStore fs, HttpHeaders headers) {
        FoodStore fsTemp = foodStoreRepository.findById(fs.getId());
        if (fsTemp != null) {
            FoodMapServiceImpl.LOGGER.info("[Food Map Service][Init FoodStore] Already Exists Id: {}", fs.getId());
            return new Response<>(0, "Already Exists Id", null);
        } else {
            foodStoreRepository.save(fs);
            return new Response<>(1, "Save Success", fs);
        }
    }

    @Override
    public TrainFood createTrainFood(TrainFood tf, HttpHeaders headers) {
        TrainFood tfTemp = trainFoodRepository.findById(tf.getId());
        if (tfTemp != null) {
            FoodMapServiceImpl.LOGGER.info("[Food Map Service][Init TrainFood] Already Exists Id: {}", tf.getId());
        } else {
            trainFoodRepository.save(tf);
        }
        return tf;
    }

    @Override
    public Response listFoodStores(HttpHeaders headers) {
        List<FoodStore> foodStores = foodStoreRepository.findAll();
        if (foodStores != null && !foodStores.isEmpty()) {
            return new Response<>(1, success, foodStores);
        } else {
            return new Response<>(0, "Foodstore is empty", null);
        }
    }

    @Override
    public Response listTrainFood(HttpHeaders headers) {
        List<TrainFood> trainFoodList = trainFoodRepository.findAll();
        if (trainFoodList != null && !trainFoodList.isEmpty()) {
            return new Response<>(1, success, trainFoodList);
        } else {
            return new Response<>(0, noContent, null);
        }
    }

    @Override
    public Response listFoodStoresByStationId(String stationId, HttpHeaders headers) {
        List<FoodStore> foodStoreList = foodStoreRepository.findByStationId(stationId);
        if (foodStoreList != null && !foodStoreList.isEmpty()) {
            return new Response<>(1, success, foodStoreList);
        } else {
            return new Response<>(0, "FoodStore is empty", null);
        }
    }

    @Override
    public Response listTrainFoodByTripId(String tripId, HttpHeaders headers) {
        List<TrainFood> trainFoodList = trainFoodRepository.findByTripId(tripId);
        if (trainFoodList != null) {
            return new Response<>(1, success, trainFoodList);
        } else {
            return new Response<>(0, noContent, null);
        }
    }

    @Override
    public Response getFoodStoresByStationIds(List<String> stationIds) {
        List<FoodStore> foodStoreList = foodStoreRepository.findByStationIdIn(stationIds);
        if (foodStoreList != null) {
            return new Response<>(1, success, foodStoreList);
        } else {
            return new Response<>(0, noContent, null);
        }
    }

    @Override
    public Response callNotificationServiceWelcome(HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-notification-service:17853/api/v1/notifyservice/welcome",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response callCancelServiceWelcome(HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-cancel-service:18885/api/v1/cancelservice/welcome",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response callConfigServiceWelcome(HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-config-service:15679/api/v1/configservice/welcome",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response callAssuranceServiceWelcome(HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-assurance-service:18888/api/v1/assuranceservice/welcome",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response callPriceServiceWelcome(HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-price-service:16579/api/v1/priceservice/prices/welcome",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response callSecurityServiceWelcome(HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-security-service:11188/api/v1/securityservice/welcome",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return re.getBody();
    }
}

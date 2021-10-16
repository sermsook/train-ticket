package train.service;

import edu.fudan.common.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import train.entity.TrainType;
import train.repository.TrainTypeRepository;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainTypeRepository repository;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public boolean create(TrainType trainType, HttpHeaders headers) {
        boolean result = false;
        if (repository.findById(trainType.getId()) == null) {
            TrainType type = new TrainType(trainType.getId(), trainType.getEconomyClass(), trainType.getConfortClass());
            type.setAverageSpeed(trainType.getAverageSpeed());
            repository.save(type);
            result = true;
        }
        return result;
    }

    @Override
    public TrainType retrieve(String id, HttpHeaders headers) {
        if (repository.findById(id) == null) {
            return null;
        } else {
            return repository.findById(id);
        }
    }

    @Override
    public boolean update(TrainType trainType, HttpHeaders headers) {
        boolean result = false;
        if (repository.findById(trainType.getId()) != null) {
            TrainType type = new TrainType(trainType.getId(), trainType.getEconomyClass(), trainType.getConfortClass());
            type.setAverageSpeed(trainType.getAverageSpeed());
            repository.save(type);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id, HttpHeaders headers) {
        boolean result = false;
        if (repository.findById(id) != null) {
            repository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<TrainType> query(HttpHeaders headers) {
        return repository.findAll();
    }

    @Override
    public Response callFoodMapServiceTestESBUsage(HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-food-map-service:18855/api/v1/foodmapservice/test/esbusage",
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return re.getBody();
    }

}

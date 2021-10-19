package travelplan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/testapigateway10")
public class TravelPlanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelPlanController.class);

    @GetMapping(path = "/api/v10/test/apiGateway" )
    public String home() {
        return "Welcome to [ Test Service ] !";
    }

}

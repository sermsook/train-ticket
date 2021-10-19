package adminroute.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fdse
 */
@Data
@NoArgsConstructor
public class AdminRouteTest4 {

    private String id;

    private List<String> stations;

    private List<Integer> distances;

    private String startStationId;

    private String terminalStationId;

}
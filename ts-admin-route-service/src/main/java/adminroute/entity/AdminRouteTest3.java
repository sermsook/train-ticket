package adminroute.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fdse
 */
@Data
@NoArgsConstructor
public class AdminRouteTest3 {

    private String id;

    private List<String> stations;

    private List<Integer> distances;

    private String startStationId;

    private String terminalStationId;

}

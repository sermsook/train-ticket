package adminroute.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fdse
 */
@Data
@NoArgsConstructor
public class AdminRouteInfo {
    private String loginId;

    private String startStation;

    private String endStation;

    private String stationList;

    private String distanceList;

    private String id;

}

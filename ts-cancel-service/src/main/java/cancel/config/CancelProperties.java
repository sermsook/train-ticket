package cancel.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "cancel.config")
@Validated
public class CancelProperties {

    private String notificationserviceurl;

    private String orderserviceurl;

    private String orderotherserviceurl;

    private String userserviceurl;

    private String insidepaymentservice;
}

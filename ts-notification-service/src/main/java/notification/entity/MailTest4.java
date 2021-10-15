package notification.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author fdse
 */
@Data
public class MailTest4 {

    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List < Object > attachments;

    private Map < String, Object > model;

    public MailTest4() {
        contentType = "text/plain";
    }

}
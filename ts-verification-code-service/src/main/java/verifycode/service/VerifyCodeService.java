package verifycode.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author fdse
 */
public interface VerifyCodeService {

    Map<String, Object> getImageCode(int width, int height, OutputStream os, HttpServletRequest request, HttpServletResponse response, HttpHeaders headers);

    boolean verifyCode(HttpServletRequest request, HttpServletResponse response, String receivedCode, HttpHeaders headers);

    Response callFoodMapServiceTestESBUsage(HttpHeaders headers);

}

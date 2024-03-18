package leare.apiGateway.controllers.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError() {
        String htmlContent = "<!DOCTYPE html><html><head><style type='text/css'>p {color: red;font-weight: 900;font-size: 20px;font-family: Helvetica, Arial, sans-serif;}</style></head><body style='background-color:black;'><p>Error manin</p></body></html>";
        return new ResponseEntity<>(htmlContent, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package leare.apiGateway.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class appStatus {
    
    @CrossOrigin
    @GetMapping("/up")
    public ResponseEntity<Boolean> up() {
        return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
    }

}
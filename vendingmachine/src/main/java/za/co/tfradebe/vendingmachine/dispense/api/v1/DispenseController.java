package za.co.tfradebe.vendingmachine.dispense.api.v1;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.tfradebe.vendingmachine.dispense.api.v1.dto.DispenseRequest;
import za.co.tfradebe.vendingmachine.dispense.api.v1.dto.DispenseResponse;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class DispenseController {
    @PostMapping("dispense")
    public ResponseEntity<DispenseResponse> checkout(@Valid @RequestBody DispenseRequest request) {
        try {
            log.error("Dispense the following amounts {}", request.toString(), request.toString());
            return new ResponseEntity<>(new DispenseResponse("SUCCESS",null,null), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Something went wrong with checkout: {}", request.toString(), e);
            throw e;
        }
    }
}

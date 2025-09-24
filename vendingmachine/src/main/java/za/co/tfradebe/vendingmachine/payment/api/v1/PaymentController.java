package za.co.tfradebe.vendingmachine.payment.api.v1;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tfradebe.vendingmachine.payment.api.v1.dto.PaymentRequest;
import za.co.tfradebe.vendingmachine.payment.api.v1.dto.PaymentResponse;
import za.co.tfradebe.vendingmachine.payment.db.AMOUNT;
import za.co.tfradebe.vendingmachine.payment.db.repos.MoneyRepo;
import za.co.tfradebe.vendingmachine.payment.service.PaymentService;

import java.util.*;
import java.util.stream.Collectors;

import static za.co.tfradebe.vendingmachine.payment.util.PaymentResponseUtil.createSuccessResponse;


@RestController
@RequestMapping("/api/v1/payment")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    private final MoneyRepo moneyRepo;

    public PaymentController(PaymentService paymentService, MoneyRepo moneyRepo) {
        this.paymentService = paymentService;
        this.moneyRepo = moneyRepo;
    }

    @GetMapping
    public ResponseEntity<PaymentResponse> getMoneyDenominators(){
        try{
            var response = AMOUNT.values();
            return new ResponseEntity<>(createSuccessResponse(List.of(response)), HttpStatus.OK);
        }catch (Exception e){
            log.error("Something went wrong with getMoneyDenominators: ", e);
            throw e;
        }
    }

    @GetMapping("/moneyOnMachine")
    public ResponseEntity<Map<AMOUNT,Integer>> getMoneyOnMachine(){
        try{
            var unsortedMap = moneyRepo.findAll().stream().collect(Collectors.toMap(moneyEntity -> moneyEntity.getAmount() , moneyEntity -> moneyEntity.getQuantity()));
            var sortedMap = new TreeMap<AMOUNT,Integer>((o1, o2) -> o2.getValue() - o1.getValue());
            sortedMap.putAll(unsortedMap);
            return new ResponseEntity<>(sortedMap, HttpStatus.OK);
        }catch (Exception e){
            log.error("Something went wrong with getMoneyOnMachine: ", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> process(@Valid @RequestBody PaymentRequest request) {
        try {
            var response = paymentService.calculateChange(request.getCartTotal(), request.getMoneyInserted());
            return new ResponseEntity<>(createSuccessResponse(response), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Something went wrong with checkout: {}", request.toString(), e);
            throw e;
        }
    }

}

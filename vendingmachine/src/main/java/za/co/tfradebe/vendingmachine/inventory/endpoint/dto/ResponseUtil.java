package za.co.tfradebe.vendingmachine.inventory.endpoint.dto;

import java.util.List;

public class ResponseUtil {

    private ResponseUtil(){}

    public static ProductResponse createSuccessResponse(List<ProductDao> products){
        var response = new ProductResponse();
        response.setData(products);
        response.setStatus("SUCCESS");
        return response;
    }
}

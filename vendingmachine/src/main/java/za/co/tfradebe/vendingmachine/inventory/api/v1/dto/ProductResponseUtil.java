package za.co.tfradebe.vendingmachine.inventory.api.v1.dto;

import java.util.List;

public class ProductResponseUtil {

    private ProductResponseUtil(){}

    public static ProductResponse createSuccessResponse(List<ProductDao> products){
        var response = new ProductResponse();
        response.setData(products);
        response.setStatus("SUCCESS");
        return response;
    }
}

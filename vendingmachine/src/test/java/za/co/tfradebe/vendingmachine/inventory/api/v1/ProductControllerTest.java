package za.co.tfradebe.vendingmachine.inventory.api.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductDao;
import za.co.tfradebe.vendingmachine.inventory.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    public void should_return_all() throws Exception {
        when(productService.findAll()).thenReturn(List.of(new ProductDao(1L,"Product1",5,6),new ProductDao(2L,"Product2",7,7)));

        mockMvc.perform(get("/api/v1/products")).andExpect(status().isOk());
    }


}

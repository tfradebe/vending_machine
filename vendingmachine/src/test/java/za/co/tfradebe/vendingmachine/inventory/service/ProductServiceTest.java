package za.co.tfradebe.vendingmachine.inventory.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductDao;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductRequest;
import za.co.tfradebe.vendingmachine.inventory.api.v1.dto.ProductRequestData;
import za.co.tfradebe.vendingmachine.inventory.db.entities.ProductEntity;
import za.co.tfradebe.vendingmachine.inventory.db.repos.ProductRepo;
import za.co.tfradebe.vendingmachine.inventory.exception.NotEnoughQuantityException;
import za.co.tfradebe.vendingmachine.inventory.mapper.ProductMapper;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepo productRepo;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductService productService;

//    @Test
//    public void find_all(){
//        when(productRepo.findAll()).thenReturn(List.of(new ProductEntity(1L, "Product1","AA", 5, 5),new ProductEntity(2L, "Product2","AB", 5, 5)));
//        when(productMapper.map(anyList())).thenReturn(List.of(new ProductDao(1L, "Product1","AA", 5, 5),new ProductDao(2L, "Product2","AA", 5, 5)));
//        var result = productService.findAll();
//        assertNotNull(result);
//        assertEquals(2,result.size());
//    }
//
//    @Test
//    public void checkout_success(){
//        when(productRepo.findAllAsMap()).thenReturn(Map.of(1L,new ProductEntity(1L, "Product1","AA", 5, 5),2L,new ProductEntity(2L, "Product2","AA", 5, 5)));
//        var request = new ProductRequest();
//        var data = new ProductRequestData();
//        data.setId(1L);
//        data.setQuantityRequired(3);
//        request.setData(List.of(data));
//
//        productService.checkout(request);
//
//        verify(productRepo,times(1)).save(any());
//
//    }
//
//    @Test
//    public void test_checkout_more_quantity(){
//        when(productRepo.findAllAsMap()).thenReturn(Map.of(1L,new ProductEntity(1L, "Product1","AA", 5, 5),2L,new ProductEntity(2L, "Product2","AA", 5, 5)));
//        var request = new ProductRequest();
//        var data = new ProductRequestData();
//        data.setId(1L);
//        data.setQuantityRequired(13);
//        request.setData(List.of(data));
//
//        assertThrows(NotEnoughQuantityException.class,() -> productService.checkout(request));
//
//    }
//
//    @Test
//    public void test_checkout_not_available_product(){
//        when(productRepo.findAllAsMap()).thenReturn(Map.of(1L,new ProductEntity(1L, "Product1","AA", 5, 5),2L,new ProductEntity(2L, "Product2","AA", 5, 5)));
//        var request = new ProductRequest();
//        var data = new ProductRequestData();
//        data.setId(10L);
//        data.setQuantityRequired(13);
//        request.setData(List.of(data));
//
//        productService.checkout(request);
//
//        verify(productRepo,times(0)).save(any());
//
//
//    }

}

package com.example.SellerInventory;

import com.example.SellerInventory.exceptions.SellerAlreadyExists;
import com.example.SellerInventory.exceptions.SellerAlreadyOnboardedToWarehouse;
import com.example.SellerInventory.exceptions.SellerDoesNotExist;
import com.example.SellerInventory.exceptions.WarehouseDoesNotExist;
import com.example.SellerInventory.service.WarehouseService;
import com.example.SellerInventory.service.ProductService;
import com.example.SellerInventory.service.SellerService;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SellerInventoryApplication {
    public static void main(String args[]) {
        WarehouseService warehouseService = WarehouseService.getInstance();
        ProductService productService = ProductService.getInstance();
        SellerService sellerService = SellerService.getInstance();

        warehouseService.addWarehouse(1);
        warehouseService.addWarehouse(2);
        warehouseService.addWarehouse(3);
        warehouseService.addWarehouse(4);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse("2024-06-17");
            warehouseService.addCapacity(1, date1, 1000);

            Date date2 = sdf.parse("2024-06-18");
            warehouseService.addCapacity(2, date2, 2000);
            warehouseService.addCapacity(1, date2, 2000);

            Date date3 = sdf.parse("2024-06-19");
            warehouseService.addCapacity(2, date3, 3000);
            warehouseService.addCapacity(1, date3, 3000);

            Date date4 = sdf.parse("2024-06-20");
            warehouseService.addCapacity(2, date4, 4000);
            warehouseService.addCapacity(1, date4, 4000);


            warehouseService.warehouseCapacity(1, date1);


            sellerService.registerService(1);

            sellerService.addProduct(10, "colgate", 1);
            sellerService.addProduct(11, "soap", 1);
            sellerService.addProduct(12, "table", 1);
            sellerService.addProduct(13, "chair", 1);
            sellerService.addProduct(14, "cushion", 1);

            sellerService.onboardWarehouse(1,1);
            sellerService.onboardWarehouse(1,2);
            sellerService.onboardWarehouse(1,3);

            Map<Integer, Integer> productIdQuantity = new HashMap<>();
            productIdQuantity.put(10, 20);
            productIdQuantity.put(11, 40);
            productIdQuantity.put(12, 60);
            productIdQuantity.put(13, 100);

            Map<Integer, Date> warehouseIdDateMap = sellerService.getSlots(1, productIdQuantity, date1);
            sellerService.reserveSlot(1, productIdQuantity, date1, 1);
            sellerService.getDetails(1);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (WarehouseDoesNotExist e) {
            throw new RuntimeException(e);
        } catch (SellerDoesNotExist e) {
            throw new RuntimeException(e);
        } catch (SellerAlreadyExists e) {
            throw new RuntimeException(e);
        } catch (SellerAlreadyOnboardedToWarehouse e) {
            throw new RuntimeException(e);
        }
    }
}

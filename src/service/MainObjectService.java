package service;

import java.util.HashMap;

public class MainObjectService {
    private HashMap<String, Object> objects;

    private MainObjectService () {
        objects = new HashMap<>();
        QueryBuilder queryBuilder = new QueryBuilder();
        ProductService productService = new ProductService(queryBuilder);
        CustomerService customerService = new CustomerService(queryBuilder);
        LineItemService lineItemService = new LineItemService(queryBuilder, productService);
        OrderService orderService = new OrderService(queryBuilder, customerService, lineItemService);

        objects.put(QueryBuilder.class.getName(), queryBuilder);
        objects.put(ProductService.class.getName(), productService);
        objects.put(CustomerService.class.getName(), customerService);
        objects.put(LineItemService.class.getName(), lineItemService);
        objects.put(OrderService.class.getName(), orderService);
    }

    private static MainObjectService instance = null;

    public static MainObjectService getInstance () {
        if (instance == null) {
            instance = new MainObjectService();
        }

        return instance;
    }

    public HashMap<String, Object> getObjects() {
        return objects;
    }
}

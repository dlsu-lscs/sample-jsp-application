package service;

public class MainService {
    private ProductService productService;
    private CustomerService customerService;
    private OrderService orderService;
    private LineItemService lineItemService;
    private QueryBuilder builder;

    private MainService () {
        this.builder = new QueryBuilder();
        this.productService = new ProductService(builder);
        this.customerService = new CustomerService(builder);
        this.lineItemService = new LineItemService(builder, productService);
        this.orderService = new OrderService(builder, customerService, lineItemService);
    }

    private static MainService instance = null;

    public static MainService getInstance () {
        if (instance == null) {
            instance = new MainService();
        }

        return instance;
    }

    public ProductService getProductService() {
        return productService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public LineItemService getLineItemService() {
        return lineItemService;
    }

    public QueryBuilder getBuilder() {
        return builder;
    }

}

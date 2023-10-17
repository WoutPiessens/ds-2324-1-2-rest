package be.kuleuven.foodrestservice.domain;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class OrderRepository {

    private static Map<String, Order> orders = new HashMap<>();


    public String createOrder(String address){
        UUID uniqueID = UUID.randomUUID();
        String id = uniqueID.toString();
        Order order = new Order(id, address);
        orders.put(id, order);
        return id;
    }


    public Collection<Order> getAllOrder(){
        return orders.values();
    }

}

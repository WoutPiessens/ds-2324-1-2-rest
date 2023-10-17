package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.domain.Order;
import be.kuleuven.foodrestservice.domain.OrderRepository;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class MealsRestRpcStyleController {

    private final MealsRepository mealsRepository;
    private final OrderRepository orderRepository;

    @Autowired
    MealsRestRpcStyleController(MealsRepository mealsRepository, OrderRepository orderRepository) {
        this.mealsRepository = mealsRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/restrpc/meals/{id}")
    Meal getMealById(@PathVariable String id) {
        Optional<Meal> meal = mealsRepository.findMeal(id);

        return meal.orElseThrow(() -> new MealNotFoundException(id));
    }

    @GetMapping("/restrpc/meals")
    Collection<Meal> getMeals() {
        return mealsRepository.getAllMeal();
    }

    @GetMapping("/restrpc/meals/cheapestmeal")
    Meal getCheapestMeal(){
        Optional<Meal> meal = mealsRepository.getCheapestMeal();
        return meal.orElseThrow(MealNotFoundException::new);
    }


    @GetMapping("/restrpc/meals/mostenergeticmeal")
    Meal getMostEnergeticMeal(){
        Optional<Meal> meal = mealsRepository.getMostEnergeticMeal();
        return meal.orElseThrow(MealNotFoundException::new);
    }



    @GetMapping("/restrpc/order/{address}")
    String createOrder(@PathVariable String address){
        String id = orderRepository.createOrder(address);
        return id;


    }

    @GetMapping("/restrpc/order")
    Collection<Order> getOrders(){
        return orderRepository.getAllOrder();

    }
}
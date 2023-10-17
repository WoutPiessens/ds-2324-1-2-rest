package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.*;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping("/restrpc/meals")
    Integer addMeal(){
        Meal meal = new Meal();
        UUID uniqueID = UUID.randomUUID();
        String id = uniqueID.toString();
        meal.setId(id);
        meal.setName("vol au vent");
        meal.setDescription("kip met champignonsaus");
        meal.setMealType(MealType.MEAT);
        meal.setKcal(777);
        meal.setPrice(9.95);
        return mealsRepository.addMeal(meal);
    }

    @PutMapping("/restrpc/meals/{mealid}")
    Integer updateMeal(@PathVariable String mealid){
        return mealsRepository.updateMeal(mealid);
    }

    @DeleteMapping("/restrpc/meals/{mealid}")
    Integer deleteMeal(@PathVariable String mealid){
        return mealsRepository.deleteMeal(mealid);
    }
}
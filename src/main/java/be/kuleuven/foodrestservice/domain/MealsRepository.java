package be.kuleuven.foodrestservice.domain;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.*;

@Component
public class MealsRepository {
    // map: id -> meal
    private static final Map<String, Meal> meals = new HashMap<>();

    @PostConstruct
    public void initData() {

        Meal a = new Meal();
        a.setId("5268203c-de76-4921-a3e3-439db69c462a");
        a.setName("Steak");
        a.setDescription("Steak with fries");
        a.setMealType(MealType.MEAT);
        a.setKcal(1100);
        a.setPrice((10.00));

        meals.put(a.getId(), a);

        Meal b = new Meal();
        b.setId("4237681a-441f-47fc-a747-8e0169bacea1");
        b.setName("Portobello");
        b.setDescription("Portobello Mushroom Burger");
        b.setMealType(MealType.VEGAN);
        b.setKcal(637);
        b.setPrice((7.00));

        meals.put(b.getId(), b);

        Meal c = new Meal();
        c.setId("cfd1601f-29a0-485d-8d21-7607ec0340c8");
        c.setName("Fish and Chips");
        c.setDescription("Fried fish with chips");
        c.setMealType(MealType.FISH);
        c.setKcal(950);
        c.setPrice(5.00);

        meals.put(c.getId(), c);
    }

    public Optional<Meal> findMeal(String id) {
        Assert.notNull(id, "The meal id must not be null");
        Meal meal = meals.get(id);
        return Optional.ofNullable(meal);
    }

    public Collection<Meal> getAllMeal() {
        return meals.values();
    }

    public Integer addMeal(Meal meal){
        meals.put(meal.id, meal);
        return 200;
    }

    public Integer updateMeal(String mealid, Meal newMeal){
        meals.remove(mealid);
        meals.put(newMeal.getId(), newMeal);
        return 200;
    }

    public Integer deleteMeal(String mealid){
        meals.remove(mealid);
        return 200;
    }

    public Optional<Meal> getCheapestMeal() {
        Meal cheapestMeal = null;

        Double lowestPrice = Double.POSITIVE_INFINITY;
        for (Meal meal : meals.values()) {
            if (meal.getPrice() < lowestPrice) {
                lowestPrice = meal.getPrice();
                cheapestMeal = meal;
            }

        }
        return Optional.ofNullable(cheapestMeal);
    }


    public Optional<Meal> getMostEnergeticMeal() {
        Meal mostEnergeticMeal = null;

        Integer mostKcals = 0;
        for (Meal meal : meals.values()) {
            if (meal.getKcal() > mostKcals) {
                mostKcals = meal.getKcal();
                mostEnergeticMeal = meal;
            }

        }
        return Optional.ofNullable(mostEnergeticMeal);
    }


}

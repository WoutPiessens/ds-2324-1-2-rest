package be.kuleuven.foodrestservice.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



@Schema(name="Order", description = "An order")
public class Order {


    @Schema(name="id", description="Unique id of the order", format="uuid")
    protected String id;
    @Schema(name="address", description="The address of the meal")
    protected String address;

    @Schema(name="meals", description="The meals of the order")
    protected Map<String, Meal> meals = new HashMap<>();


    public Order(String id, String address){
        this.id = id;
        this.address = address;
    }


    public void addMeal(Meal meal){
        meals.put(meal.getId(), meal);
    }

    public Collection<Meal> getAllMeal() {
        return meals.values();
    }

}

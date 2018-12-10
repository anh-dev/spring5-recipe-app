package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class BootstrapConfig implements CommandLineRunner {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    public BootstrapConfig(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        UnitOfMeasure quantity = unitOfMeasureRepository.findByDescription("Quantity").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Tablespoon").get();

        Recipe guacamole = new Recipe();

        Ingredient avocado = new Ingredient();
        avocado.setAmount(new BigDecimal(2));
        avocado.setDescription("2 ripe avocados");
        avocado.setUom(quantity);
        avocado.setRecipe(guacamole);

        Ingredient kosherSalt = new Ingredient();
        kosherSalt.setAmount(new BigDecimal(0.5));
        kosherSalt.setDescription("1/2 teaspoon Kosher salt");
        kosherSalt.setUom(teaspoon);
        kosherSalt.setRecipe(guacamole);

        Ingredient lemonJuice = new Ingredient();
        lemonJuice.setAmount(new BigDecimal(1));
        lemonJuice.setDescription("1 Tbsp of fresh lime juice or lemon juice");
        lemonJuice.setUom(tablespoon);
        lemonJuice.setRecipe(guacamole);

        Ingredient onion = new Ingredient();
        onion.setAmount(new BigDecimal(2));
        onion.setDescription("2 Tbsp to 1/4 cup of minced red onion or thinly sliced green onion");
        onion.setUom(tablespoon);
        onion.setRecipe(guacamole);

        Ingredient chiles = new Ingredient();
        chiles.setAmount(new BigDecimal(1));
        chiles.setDescription("1-2 serrano chiles, stems and seeds removed, minced");
        chiles.setUom(quantity);
        chiles.setRecipe(guacamole);

        Ingredient  cilantro = new Ingredient();
        cilantro.setAmount(new BigDecimal(2));
        cilantro.setDescription("2 tablespoons cilantro (leaves and tender stems), finely chopped");
        cilantro.setUom(tablespoon);
        cilantro.setRecipe(guacamole);

        Ingredient  pepper = new Ingredient();
        pepper.setAmount(new BigDecimal(1));
        pepper.setDescription("A dash of freshly grated black pepper");
        pepper.setUom(dash);
        pepper.setRecipe(guacamole);

        Ingredient  tomato = new Ingredient();
        tomato.setAmount(new BigDecimal(0.5));
        tomato.setDescription("1/2 ripe tomato, seeds and pulp removed, chopped");
        tomato.setUom(quantity);
        tomato.setRecipe(guacamole);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(avocado);
        ingredients.add(kosherSalt);
        ingredients.add(lemonJuice);
        ingredients.add(onion);
        ingredients.add(chiles);
        ingredients.add(cilantro);
        ingredients.add(pepper);
        ingredients.add(tomato);

        Category mexican = categoryRepository.findByDescription("Mexican").get();
        Set<Category> categories = new HashSet<>();
        categories.add(mexican);

        Notes notes = new Notes();
        notes.setRecipeNotes("The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.");



        guacamole.setCookTime(10);
        guacamole.setDescription("How to Make Perfect Guacamole Recipe");
        guacamole.setDifficulty(Difficulty.MODERATE);
        guacamole.setDirections("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        guacamole.setServings(2);
        guacamole.setIngredients(ingredients);
        guacamole.setCategories(categories);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setSource("WEB");
        guacamole.setNotes(notes);

        recipeRepository.save(guacamole);

    }
}

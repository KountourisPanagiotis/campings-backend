package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Category;
import java.util.List;

/**
 * ICategoriesDAO is an interface for managing categories in the database.
 * It provides the necessary methods for creating, reading, updating, and deleting category records.
 *
 * @author Kountouris Panagiotis.
 */
public interface ICategoriesDAO {

    /**
     * Retrieves a category by its code.
     *
     * @param catCode A String that represents the category code.
     * @return Category object corresponding to the provided category code.
     */
    Category getCategoryByCode(String catCode);

    /**
     * Retrieves all categories.
     *
     * @return List of all Category objects from the database.
     */
    List<Category> getAllCategories();

    /**
     * Adds a new category to the database.
     *
     * @param category A Category object that needs to be added to the database.
     * @return The Category object that was added to the database.
     */
    Category insertCategory(Category category);

    /**
     * Updates an existing category in the database.
     *
     * @param category A Category object that needs to be updated in the database.
     * @return The Category object that was updated in the database.
     */
    Category updateCategory(Category category);

    /**
     * Deletes a category from the database.
     *
     * @param catCode A String that represents the category code of the category to be deleted.
     * @return The Category object that was deleted from the database.
     */
    Category deleteCategory(String catCode);
}

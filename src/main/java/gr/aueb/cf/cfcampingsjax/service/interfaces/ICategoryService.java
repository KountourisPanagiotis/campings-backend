package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.CategoryDTO;
import gr.aueb.cf.cfcampingsjax.model.Category;

import java.util.List;

/**
 * ICategoryService is an interface for managing categories in the application.
 * It provides methods for retrieving, inserting, updating, and deleting category records.
 * It also includes methods for converting between CategoryDTO and Category objects.
 *
 * @author Kountouris Panagiotis.
 */
public interface ICategoryService {

    /**
     * Retrieves a category by its code.
     *
     * @param catCode The unique code of the category to retrieve.
     * @return CategoryDTO object corresponding to the category with the provided code.
     */
    CategoryDTO getCategoryByCode(String catCode);

    /**
     * Retrieves all categories.
     *
     * @return List of all CategoryDTO objects.
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Adds a new category to the database.
     *
     * @param categoryDTO The CategoryDTO object representing the new category to be added.
     * @return The added CategoryDTO object.
     */
    CategoryDTO insertCategory(CategoryDTO categoryDTO);

    /**
     * Updates an existing category in the database.
     *
     * @param categoryDTO The CategoryDTO object representing the category to be updated.
     * @return The updated CategoryDTO object.
     */
    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    /**
     * Deletes a category from the database.
     *
     * @param catCode The unique code of the category to delete.
     * @return The deleted CategoryDTO object.
     */
    CategoryDTO deleteCategory(String catCode);

    /**
     * Converts a Category object to a CategoryDTO object.
     *
     * @param category The Category object to be converted.
     * @return The corresponding CategoryDTO object.
     */
    CategoryDTO convertToDTO(Category category);

    /**
     * Converts a CategoryDTO object to a Category object.
     *
     * @param categoryDTO The CategoryDTO object to be converted.
     * @return The corresponding Category object.
     */
    Category convertToModel(CategoryDTO categoryDTO);
}

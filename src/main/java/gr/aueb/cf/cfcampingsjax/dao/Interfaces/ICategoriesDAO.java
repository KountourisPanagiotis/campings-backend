package gr.aueb.cf.cfcampingsjax.dao.Interfaces;

import gr.aueb.cf.cfcampingsjax.model.Category;

import java.util.List;

public interface ICategoriesDAO {

    Category getCategoryByCode(String catCode);
    List<Category> getAllCategories();
    Category insertCategory(Category category);
    public Category updateCategory(Category category);
    Category deleteCategory(String catCode);

}

package gr.aueb.cf.cfcampingsjax.service.interfaces;

import gr.aueb.cf.cfcampingsjax.dto.CategoryDTO;
import gr.aueb.cf.cfcampingsjax.model.Category;

import java.util.List;

public interface ICategoryService {

    CategoryDTO getCategoryByCode(String catCode);
    List<CategoryDTO> getAllCategories();
    CategoryDTO insertCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(String catCode);
    CategoryDTO convertToDTO(Category category);
    Category convertToModel(CategoryDTO categoryDTO);
}

package gr.aueb.cf.cfcampingsjax.service.implementations;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICategoriesDAO;
import gr.aueb.cf.cfcampingsjax.dto.CategoryDTO;
import gr.aueb.cf.cfcampingsjax.model.Category;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ICategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    private final ICategoriesDAO categoryDAO;

    // Dependency Injection
    public CategoryServiceImpl(ICategoriesDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public CategoryDTO getCategoryByCode(String catCode) {
        if(catCode == null || catCode.isEmpty()) {
            throw new IllegalArgumentException("Category code cannot be null or empty.");
        }
        Category category = categoryDAO.getCategoryByCode(catCode);
        return convertToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOs.add(convertToDTO(category));
        }
        return categoryDTOs;
    }

    @Override
    public CategoryDTO insertCategory(CategoryDTO categoryDTO) {
        Category category = convertToModel(categoryDTO);
        if (categoryDAO.getCategoryByCode(category.getCatCode()) != null) {
            throw new IllegalArgumentException("Category with this code already exists.");
        }
        category = categoryDAO.insertCategory(category);
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = convertToModel(categoryDTO);
        if (categoryDAO.getCategoryByCode(category.getCatCode()) == null) {
            throw new IllegalArgumentException("No such category exists to update.");
        }
        category = categoryDAO.updateCategory(category);
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO deleteCategory(String catCode) {
        if(catCode == null || catCode.isEmpty()) {
            throw new IllegalArgumentException("Category code cannot be null or empty.");
        }
        Category category = categoryDAO.deleteCategory(catCode);
        return convertToDTO(category);
    }

    public CategoryDTO convertToDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getCatCode(), category.getAreaM2(), category.getUnitCost());
    }

    public Category convertToModel(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return new Category(categoryDTO.getCatCode(), categoryDTO.getAreaM2(), categoryDTO.getUnitCost());
    }
}

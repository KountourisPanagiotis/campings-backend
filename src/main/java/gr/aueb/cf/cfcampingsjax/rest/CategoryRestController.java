package gr.aueb.cf.cfcampingsjax.rest;

import gr.aueb.cf.cfcampingsjax.dao.Interfaces.ICategoriesDAO;
import gr.aueb.cf.cfcampingsjax.dao.implementations.CategoriesDAOImpl;
import gr.aueb.cf.cfcampingsjax.dto.CategoryDTO;
import gr.aueb.cf.cfcampingsjax.service.implementations.CategoryServiceImpl;
import gr.aueb.cf.cfcampingsjax.service.interfaces.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/category")
public class CategoryRestController {
    private final ICategoryService categoryService;

    public CategoryRestController() {
        ICategoriesDAO categoryDAO = new CategoriesDAOImpl();
        this.categoryService = new CategoryServiceImpl(categoryDAO);
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all categories", tags = {"categories"})
    public Response getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return Response.ok(categories).build();
    }

    @Path("/{catCode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryByCode(@PathParam("catCode") String catCode) {
        CategoryDTO category = categoryService.getCategoryByCode(catCode);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(category).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCategory(CategoryDTO categoryDTO) {
        try {
            CategoryDTO createdCategory = categoryService.insertCategory(categoryDTO);
            return Response.status(Response.Status.CREATED).entity(createdCategory).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/{catCode}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(CategoryDTO categoryDTO) {
        try {
            CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO);
            if (updatedCategory == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(updatedCategory).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{catCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategory(@PathParam("catCode") String catCode) {
        try {
            CategoryDTO deletedCategory = categoryService.deleteCategory(catCode);
            if (deletedCategory == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(deletedCategory).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

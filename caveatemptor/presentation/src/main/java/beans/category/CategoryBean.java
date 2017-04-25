package beans.category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mapping.CategoryMapper;
import mapping.Tree;
import mapping.TreeMapper;
import repository.entities.Category;
import category.ICategoryBuilderService;

import com.google.gson.Gson;

import dto.CategoryDTO;
import exceptions.CategoryException;

@ManagedBean(name = "category")
@RequestScoped
public class CategoryBean {

	@EJB
	private ICategoryBuilderService iCategoryBuilderService;

	// TODO: handle exception
	public String getTree() {

		try {

			Category root = iCategoryBuilderService.getRoot(1L);

			CategoryDTO rootDTO = CategoryMapper.getCategoryDTO(root);
			Tree tree = TreeMapper.getTree(rootDTO);

			Gson gson = new Gson();

			return gson.toJson(tree);

		} catch (CategoryException e) {

		}

		return null;
	}

}

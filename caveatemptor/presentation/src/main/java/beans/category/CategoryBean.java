package beans.category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mapping.Tree;
import mapping.TreeMapper;
import category.ICategoryBuilderService;

import com.google.gson.Gson;

import dto.CategoryDTO;
import exceptions.CategoryException;

@ManagedBean(name = "category")
@RequestScoped
public class CategoryBean {

	@EJB
	private ICategoryBuilderService iCategoryBuilderService;

	// TODO: inject the category JSON
	private String tree;

	public String getInitializedTree() {

		Gson gson = new Gson();

		try {
			CategoryDTO rootDTO = iCategoryBuilderService.getRootDTO(1L);
			Tree tree = TreeMapper.getTree(rootDTO);

			return gson.toJson(tree);
		} catch (CategoryException e) {
			return null;
		}
	}

	public String getTree() {
		setTree(getInitializedTree());
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

}

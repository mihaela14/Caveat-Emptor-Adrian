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
	private String minimalTree;

	private String fullTree;

	public String getInitializedTree(String type) {

		Gson gson = new Gson();

		try {
			CategoryDTO rootDTO = iCategoryBuilderService.getRootDTO(1L);
			Tree tree = TreeMapper.getTree(rootDTO);

			switch (type) {
			case "minimal":
				return gson.toJson(tree);
			case "full":
				return gson.toJson(rootDTO);
			default:
				return null;
			}

		} catch (CategoryException e) {
			return null;
		}
	}

	public String getMinimalTree() {
		setMinimalTree(getInitializedTree("minimal"));
		return minimalTree;
	}

	public void setMinimalTree(String minimalTree) {
		this.minimalTree = minimalTree;
	}

	public String getFullTree() {
		setFullTree(getInitializedTree("full"));
		return fullTree;
	}

	public void setFullTree(String fullTree) {
		this.fullTree = fullTree;
	}

}

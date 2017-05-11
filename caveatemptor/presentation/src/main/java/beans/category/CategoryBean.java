package beans.category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mapping.TreeMapper;
import beans.category.utils.TreeTypes;
import category.CategoryService;

import com.google.gson.Gson;

import dto.CategoryDTO;
import dto.Tree;
import exceptions.CategoryException;

@ManagedBean(name = "category")
@RequestScoped
public class CategoryBean {

	@EJB
	private CategoryService categoryService;

	private String minimalTree;

	private String fullTree;

	@PostConstruct
	public void init() {
		minimalTree = getInitializedTree(TreeTypes.MINIMAL.toString());
		fullTree = getInitializedTree(TreeTypes.FULL.toString());
	}

	public String getInitializedTree(String type) {

		Gson gson = new Gson();

		try {
			CategoryDTO rootDTO = categoryService.getRootDTO();
			Tree tree = TreeMapper.getTree(rootDTO);

			if (type.equalsIgnoreCase(TreeTypes.MINIMAL.toString())) {
				return gson.toJson(tree);
			} else if (type.equalsIgnoreCase(TreeTypes.FULL.toString())) {
				return gson.toJson(rootDTO);
			} else {
				return null;
			}

		} catch (CategoryException e) {
			return null;
		}
	}

	public String getMinimalTree() {
		return minimalTree;
	}

	public void setMinimalTree(String minimalTree) {
		this.minimalTree = minimalTree;
	}

	public String getFullTree() {
		return fullTree;
	}

	public void setFullTree(String fullTree) {
		this.fullTree = fullTree;
	}

}

package beans.category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mapping.Tree;
import mapping.TreeMapper;
import beans.category.utils.TreeTypes;
import category.CategoryService;

import com.google.gson.Gson;

import dto.CategoryDTO;
import exceptions.CategoryException;

@ManagedBean(name = "category")
@RequestScoped
public class CategoryBean {

	@EJB
	private CategoryService categoryService;

	private String minimalTree;

	private String fullTree;

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
		setMinimalTree(getInitializedTree(TreeTypes.MINIMAL.toString()));
		return minimalTree;
	}

	public void setMinimalTree(String minimalTree) {
		this.minimalTree = minimalTree;
	}

	public String getFullTree() {
		setFullTree(getInitializedTree(TreeTypes.FULL.toString()));
		return fullTree;
	}

	public void setFullTree(String fullTree) {
		this.fullTree = fullTree;
	}

}

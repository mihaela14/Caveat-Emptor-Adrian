package beans.category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import category.CategoryService;
import constants.Routes;
import dto.CategoryDTO;
import exceptions.CategoryException;

@ManagedBean(name = "category_operations")
@RequestScoped
public class CategoryOperationsBean {

	private String name;

	private String description;

	private Long categoryId;

	@EJB
	private CategoryService categoryService;

	public String addChildCategory() {

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setName(name);
		categoryDTO.setDescription(description);

		try {
			categoryService.addChildCategory(categoryDTO, categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			return null;
		}
	}

	public String addRootCategory() {

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setName(name);
		categoryDTO.setDescription(description);

		try {
			categoryService.addRootCategory(categoryDTO);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			return null;
		}
	}

	public String removeCategory() {

		try {
			categoryService.removeCategory(categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			return null;
		}
	}

	public String updateCategory() {

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setName(name);
		categoryDTO.setDescription(description);

		try {
			categoryService.updateCategory(categoryDTO, categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}

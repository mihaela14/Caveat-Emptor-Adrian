package beans.category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import category.CategoryService;
import constants.Routes;
import dto.CategoryDTO;
import exceptions.CategoryException;

@ManagedBean(name = "category_operations")
@SessionScoped
public class CategoryOperationsBean {

	private String name;

	private String description;

	private Long categoryId;

	@EJB
	private CategoryService categoryService;

	public String save() {

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setName(name);
		categoryDTO.setDescription(description);

		try {
			categoryService.addCategory(categoryDTO, categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			return null;
		}

	}

	public String remove() {

		try {
			categoryService.removeCategory(categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
		}

		return null;
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

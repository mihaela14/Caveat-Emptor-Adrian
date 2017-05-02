package beans.category;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import constants.Routes;
import category.ICategoryService;
import dto.CategoryDTO;
import exceptions.CategoryException;

@ManagedBean(name = "category_operations")
@RequestScoped
public class CategoryOperationsBean {

	private String name;

	private String description;

	private Long categoryId;

	@EJB
	ICategoryService iCategoryService;

	public String save() {

		CategoryDTO categoryDTO = new CategoryDTO();

		categoryDTO.setName(name);
		categoryDTO.setDescription(description);

		try {
			iCategoryService.addCategory(categoryDTO, categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
		}

		return null;
	}

	public String remove() {

		try {
			iCategoryService.removeCategory(categoryId);
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

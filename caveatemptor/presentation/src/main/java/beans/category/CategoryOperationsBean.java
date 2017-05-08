package beans.category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import utils.FacesContextMessage;
import category.CategoryService;
import constants.Forms;
import constants.Routes;
import dto.CategoryDTO;
import exceptions.CategoryException;

@ManagedBean(name = "category_operations")
@RequestScoped
public class CategoryOperationsBean {

	private CategoryDTO categoryDTO;

	private Long categoryId;

	@EJB
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		categoryDTO = new CategoryDTO();
	}

	public String addChildCategory() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			categoryService.addChildCategory(categoryDTO, categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			FacesContextMessage.addMessage(facesContext,
					Forms.CATEGORY.getName(), e.getMessage());
			return null;
		}
	}

	public String addRootCategory() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			categoryService.addRootCategory(categoryDTO);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			FacesContextMessage.addMessage(facesContext,
					Forms.CATEGORY.getName(), e.getMessage());
			return null;
		}
	}

	public String deleteCategory() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			categoryService.deleteCategory(categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			FacesContextMessage.addMessage(facesContext,
					Forms.CATEGORY.getName(), e.getMessage());
			return null;
		}
	}

	public String updateCategory() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			categoryService.updateCategory(categoryDTO, categoryId);
			return Routes.CATEGORY_REDIRECT.getUrl();
		} catch (CategoryException e) {
			FacesContextMessage.addMessage(facesContext,
					Forms.CATEGORY.getName(), e.getMessage());
			return null;
		}
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

}

package beans.bid;

import item.ItemService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import repository.entities.Category;
import repository.repositories.category.CategoryRepository;
import category.CategoryService;
import dto.CategoryDTO;
import dto.ItemPagination;
import dto.ItemRow;
import exceptions.CategoryException;
import exceptions.ItemException;

@ManagedBean(name = "bidBean")
@ViewScoped
public class BidBean {

	@EJB
	private ItemService itemService;

	@EJB
	private CategoryService categoryService;

	@EJB
	private CategoryRepository categoryRepository;

	private List<ItemRow> itemRows;

	private CategoryDTO categoryDTO;

	// TODO: handle exception
	@PostConstruct
	public void init() {
		categoryDTO = new CategoryDTO();
		itemRows = new ArrayList<>();
	}

	public void getItemRowsByCategory() throws CategoryException {

		ItemPagination itemPagination = new ItemPagination();
		itemPagination.setUsed(false);

		List<Category> categories = categoryService.getCategories(categoryDTO);

		try {
			for (Category category : categories) {
				List<ItemRow> rows = itemService.getItemRowsByCategory(
						category, itemPagination);
				itemRows.addAll(rows);
			}
		} catch (CategoryException | ItemException e) {
		}
	}

	public List<ItemRow> getItemRows() {
		return itemRows;
	}

	public void setItemRows(List<ItemRow> itemRows) {
		this.itemRows = itemRows;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

}

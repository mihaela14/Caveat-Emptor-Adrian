package category;

import repository.entities.Category;
import dto.CategoryDTO;
import exceptions.CategoryException;

public interface CategoryService {

	Category getRoot() throws CategoryException;

	CategoryDTO getRootDTO() throws CategoryException;

	void addChildCategory(CategoryDTO categoryDTO, Long categoryId)
			throws CategoryException;

	void addRootCategory(CategoryDTO categoryDTO) throws CategoryException;

	void updateCategory(CategoryDTO categoryDTO, Long id)
			throws CategoryException;

	void deleteCategory(Long id) throws CategoryException;
}

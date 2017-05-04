package category;

import repository.entities.Category;
import dto.CategoryDTO;
import exceptions.CategoryException;

public interface CategoryService {

	Category getRoot() throws CategoryException;
	
	CategoryDTO getRootDTO() throws CategoryException;

	void addCategory(CategoryDTO categoryDTO, Long categoryId)
			throws CategoryException;

	void removeCategory(Long id) throws CategoryException;
}

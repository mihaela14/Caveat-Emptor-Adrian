package category;

import dto.CategoryDTO;
import exceptions.CategoryException;

public interface ICategoryService {

	CategoryDTO getRootDTO() throws CategoryException;

	void addCategory(CategoryDTO categoryDTO, Long categoryId)
			throws CategoryException;

	void removeCategory(Long id) throws CategoryException;
}

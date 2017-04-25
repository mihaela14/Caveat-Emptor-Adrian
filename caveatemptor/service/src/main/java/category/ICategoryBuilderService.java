package category;

import dto.CategoryDTO;
import exceptions.CategoryException;

public interface ICategoryBuilderService {

	CategoryDTO getRootDTO(Long id) throws CategoryException;
}

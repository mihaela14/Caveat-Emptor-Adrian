package category;

import repository.entities.Category;
import exceptions.CategoryException;

public interface ICategoryBuilderService {

	Category getRoot(Long id) throws CategoryException;
}

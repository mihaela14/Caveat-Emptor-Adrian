package category;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.CategoryMapper;
import repository.entities.Category;
import repository.repositories.category.ICategoryRepository;
import dto.CategoryDTO;
import exceptions.CategoryException;

@Stateless
@Remote(ICategoryBuilderService.class)
public class CategoryBuilderService implements ICategoryBuilderService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private ICategoryRepository iCategoryRepository;

	@Override
	public CategoryDTO getRootDTO(Long id) throws CategoryException {

		Category root = iCategoryRepository.getSingleEntityById(id,
				entityManager);

		return CategoryMapper.getCategoryDTO(root);
	}

}

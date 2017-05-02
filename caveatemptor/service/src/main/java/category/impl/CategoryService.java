package category.impl;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.CategoryMapper;
import repository.entities.Category;
import repository.repositories.category.ICategoryRepository;
import category.ICategoryService;
import constants.CategoryEntity;
import dto.CategoryDTO;
import exceptions.CategoryException;

@Stateless
@Remote(ICategoryService.class)
public class CategoryService implements ICategoryService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private ICategoryRepository iCategoryRepository;

	@Override
	public CategoryDTO getRootDTO() throws CategoryException {

		Category root = iCategoryRepository.getSingleEntityById(
				CategoryEntity.ROOT_ID.getValue(), entityManager);

		return CategoryMapper.getCategoryDTO(root);
	}

	@Override
	public void addCategory(CategoryDTO categoryDTO, Long categoryId)
			throws CategoryException {

		Category category = CategoryMapper.getCategory(categoryDTO);

		if (categoryId == null) {
			Category root = iCategoryRepository.getSingleEntityById(
					CategoryEntity.ROOT_ID.getValue(), entityManager);

			category.setParent(root);
			iCategoryRepository.add(category, entityManager);
		} else {

			Category selectedCategory = iCategoryRepository
					.getSingleEntityById(categoryId, entityManager);

			if (selectedCategory.getName().equals(categoryDTO.getName())) {
				selectedCategory.setDescription(categoryDTO.getDescription());
				iCategoryRepository.add(selectedCategory, entityManager);
			} else {
				category.setParent(selectedCategory);
				iCategoryRepository.add(category, entityManager);
			}
		}
	}

	@Override
	public void removeCategory(Long id) throws CategoryException {

		Category category = iCategoryRepository.getSingleEntityById(id,
				entityManager);

		for (Category child : category.getCategories()) {
			child.setParent(category.getParent());
			iCategoryRepository.add(child, entityManager);
		}

		iCategoryRepository.remove(category, entityManager);
	}

}

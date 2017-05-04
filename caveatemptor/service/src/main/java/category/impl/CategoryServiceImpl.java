package category.impl;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.CategoryMapper;
import repository.entities.Category;
import repository.repositories.category.CategoryRepository;
import category.CategoryService;
import constants.CategoryEntity;
import dto.CategoryDTO;
import exceptions.CategoryException;

@Stateless
@Remote(CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private CategoryRepository categoryRepository;

	@Override
	public Category getRoot() throws CategoryException {

		Category root = categoryRepository.getSingleEntityById(
				CategoryEntity.ROOT_ID.getValue(), entityManager);
		return root;
	}

	@Override
	public CategoryDTO getRootDTO() throws CategoryException {

		return CategoryMapper.getCategoryDTO(getRoot());
	}

	@Override
	public void addCategory(CategoryDTO categoryDTO, Long categoryId)
			throws CategoryException {

		Category category = CategoryMapper.getCategory(categoryDTO);

		if (categoryId == null) {
			addCategoryToRoot(category);
		} else {
			Category selectedCategory = categoryRepository.getSingleEntityById(
					categoryId, entityManager);

			if (selectedCategory.getName().equals(categoryDTO.getName())) {
				selectedCategory.setDescription(categoryDTO.getDescription());
				categoryRepository.add(selectedCategory, entityManager);
			} else {
				category.setParent(selectedCategory);
				categoryRepository.add(category, entityManager);
			}
		}
	}

	private void addCategoryToRoot(Category category) throws CategoryException {

		Category root = getRoot();

		category.setParent(root);
		categoryRepository.add(category, entityManager);
	}

	@Override
	public void removeCategory(Long id) throws CategoryException {

		Category category = categoryRepository.getSingleEntityById(id,
				entityManager);

		for (Category child : category.getCategories()) {
			child.setParent(category.getParent());
			categoryRepository.add(child, entityManager);
		}

		categoryRepository.remove(category, entityManager);
	}

}

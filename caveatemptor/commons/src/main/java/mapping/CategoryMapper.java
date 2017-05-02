package mapping;

import java.util.ArrayList;
import java.util.List;

import dto.CategoryDTO;
import repository.entities.Category;

public class CategoryMapper {

	private CategoryMapper() {
	}

	public static Category getCategory(CategoryDTO categoryDTO) {

		Category category = new Category();

		if (categoryDTO != null) {
			category.setId(categoryDTO.getId());
			category.setName(categoryDTO.getName());
			category.setDescription(categoryDTO.getDescription());

			if (categoryDTO.getCategories() != null) {
				List<Category> children = new ArrayList<>();

				for (CategoryDTO childDTO : categoryDTO.getCategories()) {
					Category child = CategoryMapper.getCategory(childDTO);
					children.add(child);
				}

				category.setCategories(children);
			}
		}

		return category;
	}

	public static CategoryDTO getCategoryDTO(Category category) {

		CategoryDTO categoryDTO = new CategoryDTO();

		if (category != null) {
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTO.setDescription(category.getDescription());

			if (category.getCategories() != null) {
				List<CategoryDTO> childrenDTO = new ArrayList<>();

				for (Category child : category.getCategories()) {
					CategoryDTO childDTO = CategoryMapper.getCategoryDTO(child);
					childrenDTO.add(childDTO);
				}

				categoryDTO.setCategories(childrenDTO);
			}
		}

		return categoryDTO;
	}
}

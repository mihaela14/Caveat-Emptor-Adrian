package dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1823021465333683291L;

	private Long id;

	private CategoryDTO parent;

	private List<CategoryDTO> categories;

	private String name;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryDTO getParent() {
		return parent;
	}

	public void setParent(CategoryDTO parent) {
		this.parent = parent;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

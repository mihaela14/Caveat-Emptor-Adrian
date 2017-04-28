package dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1823021465333683291L;

	private Long id;

	private Long parentId;

	private List<CategoryDTO> children;

	private String name;

	private String description;

	public CategoryDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CategoryDTO> getCategories() {
		return children;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.children = categories;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}

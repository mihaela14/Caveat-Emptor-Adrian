package dto;

import java.util.List;

public class Tree {

	private String text;

	private List<Tree> nodes;

	private Long id;

	private String description;

	public Tree() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Tree> getNodes() {
		return nodes;
	}

	public void setNodes(List<Tree> nodes) {
		this.nodes = nodes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

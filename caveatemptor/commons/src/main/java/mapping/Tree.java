package mapping;

import java.util.List;

public class Tree {

	private String text;

	private List<Tree> nodes;

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

}

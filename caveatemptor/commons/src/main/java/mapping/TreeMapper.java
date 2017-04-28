package mapping;

import java.util.ArrayList;
import java.util.List;

import dto.CategoryDTO;

public class TreeMapper {

	private TreeMapper() {
	}

	public static Tree getTree(CategoryDTO categoryDTO) {

		Tree tree = new Tree();

		if (categoryDTO != null) {
			tree.setText(categoryDTO.getName());
			tree.setDescription(categoryDTO.getDescription());

			if (categoryDTO.getCategories() != null) {
				List<Tree> nodes = new ArrayList<>();

				for (CategoryDTO childDTO : categoryDTO.getCategories()) {
					Tree node = TreeMapper.getTree(childDTO);
					nodes.add(node);
				}

				tree.setNodes(nodes);
			}
		}

		return tree;
	}
}

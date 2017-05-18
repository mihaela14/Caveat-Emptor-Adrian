function updateForms(name, description) {

	document.getElementById('categoryForm:name').value = name;
	document.getElementById('categoryForm:description').value = description;
	document.getElementById('categoryForm:categoryId').value = null;
}

function getParent(child) {

	if (child !== undefined) {
		return $('#tree').treeview('getParent', child);
	}
}

function selectNode(nodeId, flag) {

	var node = $('#tree').treeview('getNode', nodeId);
	$('#tree').treeview('selectNode', [ node, {
		silent : true
	} ]);

	createBreadcrumb(node, $('#breadcrumb'));

	if (flag) {
		updateForms(node['text'], node['description']);
		document.getElementById('categoryForm:categoryId').value = node['id'];
	}
}

function getBreadcrumb(node) {

	var parent;
	var breadcrumb = [];

	breadcrumb.push(node);

	do {
		parent = getParent(node);
		node = jQuery.extend(true, {}, parent);
		breadcrumb.push(node);
	} while (node['nodeId'] !== undefined);

	breadcrumb.pop();
	return breadcrumb;
}

function createBreadcrumb(data, breadcrumb, hiddenButton) {

	breadcrumb.empty();
	var _breadcrumb = getBreadcrumb(data);

	for (var i = _breadcrumb.length - 1; i >= 0; --i) {

		var li = document.createElement('li');
		li.setAttribute('class', 'breadcrumb-item');

		var id = _breadcrumb[i]['nodeId'];
		li.setAttribute('id', id);

		li.setAttribute('onclick', 'selectNode(id, true)');

		var a = document.createElement('a');
		a.setAttribute('href', '#');
		a.setAttribute('id', 'id' + i);
		li.appendChild(a);

		document.getElementById('breadcrumb').appendChild(li);
		document.getElementById('id' + i).innerHTML = _breadcrumb[i]['text'];
	}

	var selectedId = _breadcrumb[0]['nodeId'];

	var list = document.getElementById(selectedId);
	list.setAttribute('class', 'breadcrumb-item active');
	list.removeChild(list.childNodes[0]);

	document.getElementById(data['nodeId']).innerHTML = _breadcrumb[0]['text'];
}

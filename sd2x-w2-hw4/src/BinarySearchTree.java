
public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;

		Node(E value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node) obj;
			return other.value.compareTo(value) == 0 && other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}

	protected Node root = null;

	protected void visit(Node n) {
		System.out.println(n.value);
	}

	public boolean contains(E val) {
		return contains(root, val);
	}

	protected boolean contains(Node n, E val) {
		if (n == null)
			return false;

		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}

	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}

	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			}
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			}
		}
	}

	public boolean remove(E val) {
		return remove(root, null, val);
	}

	protected boolean remove(Node n, Node parent, E val) {
		if (n == null)
			return false;

		if (val.compareTo(n.value) == -1) {
			return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
			return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null) {
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n) {
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
		} else {
			return maxValue(n.rightChild);
		}
	}

	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/

	// Method #1.
	public Node findNode(E val) {
		return findNode(root, val);
	}

	private Node findNode(Node n, E val) {
		if (n == null) {
			return null;
		}
		if (val == null) {
			return null;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return n;
		} else if (cmp < 0) {
			return findNode(n.leftChild, val);
		} else {
			return findNode(n.rightChild, val);
		}
	}

	// Method #2.
	protected int depth(E val) {
		return depth(root, val, 0);
	}

	private int depth(Node n, E val, int currentDepth) {
		if (n == null) {
			return -1;
		}
		if (val == null) {
			return -1;
		}

		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return currentDepth;
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				return -1;
			} else {
				return depth(n.leftChild, val, currentDepth + 1);
			}
		} else {
			if (n.rightChild == null) {
				return -1;
			} else {
				return depth(n.rightChild, val, currentDepth + 1);
			}
		}
	}

	// Method #3.
	protected int height(E val) {
		return height(root, val);
	}

	private int height(Node n, E val) {
		if (n == null) {
			return -1;
		}
		if (val == null) {
			return -1;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			if (n.leftChild == null && n.rightChild == null) {
				return 0;
			} else if (n.leftChild != null && n.rightChild != null) {
				int leftHeight = height(n.leftChild, n.leftChild.value);
				int rightHeight = height(n.rightChild, n.rightChild.value);
				return 1 + Math.max(leftHeight, rightHeight);
			} else if (n.leftChild != null) {
				return 1 + height(n.leftChild, n.leftChild.value);
			} else {
				return 1 + height(n.rightChild, n.rightChild.value);
			}
		} else if (cmp < 0) {
			return height(n.leftChild, val);
		} else {
			return height(n.rightChild, val);
		}
	}

	// Method #4.
	protected boolean isBalanced(Node n) {
		if (n == null) {
			return false;
		}
		if (!contains(n.value)) {
			return false;
		}
		int leftHeight = n.leftChild == null ? -1 : height(n.leftChild, n.leftChild.value);
		int rightHeight = n.rightChild == null ? -1 : height(n.rightChild, n.rightChild.value);
		return Math.abs(leftHeight - rightHeight) <= 1;
	}

	// Method #5. .
	public boolean isBalanced() {
//		System.out.println("root " + root);
		if (root == null) {
			return false;
		}
		if (!isBalanced(root)) {
			return false;
		}
		return isFullBalanced(root.leftChild) && isFullBalanced(root.rightChild);
	}

	private boolean isFullBalanced(Node node) {
		if (!isBalanced(node)) {
			return false;
		}
		if (node.leftChild != null && node.rightChild != null) {
			return isFullBalanced(node.leftChild) && isFullBalanced(node.rightChild);
		} else if (node.leftChild == null && node.rightChild == null) {
			return true;
		} else if (node.leftChild != null) {
			return isFullBalanced(node.leftChild);
		} else if (node.rightChild != null) {
			return isFullBalanced(node.rightChild);
		}
		return true;
	}

}

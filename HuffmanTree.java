/**
 * Huffman Binary Tree generation using set values for frequencies, and string
 * encoding/decoding.
 * 
 * @author Skully (https://github.com/ImSkully)
 * @email contact@skully.tech
 */

class HuffmanTree {
	/**
	 * Takes the given string and encodes it using the Binary Frequency Table.
	 * 
	 * @param textToEncode The string to encode.
	 * @param theTree      The root of the Binary Tree to use.
	 * @return The string encoded.
	 */
	static String encodeString(String textToEncode, TreeNode theTree) {
		TreeNode rootNode = theTree; // Variable to reference the top of the tree since original will be adjusted.

		QueueReferenceBased treeCache = new QueueReferenceBased(); // Temporary storage for tree nodes.
		StringBuilder encodedString = new StringBuilder(); // Encoded string to return.
		int completedChars = 0; // Total number of characters encoded.

		// While the number of characters that have been encoded is not the same as the
		// length of the text we need to encode.
		while (completedChars != textToEncode.length()) {
			// If this current tree node is leaf. (No children to the left or right)
			if (theTree.getLeft() == null && theTree.getRight() == null) {
				Frequency leafFrequency = (Frequency) theTree.getItem(); // Get the Frequency.

				// If the frequency object's char matches the current character we are looking for.
				if (textToEncode.charAt(completedChars) == leafFrequency.getC()) {
					// We found the character, now we traverse our way back up to the root and keep
					// track of the path we take.
					TreeNode parentNode = theTree.getParent(); // Set parentNode to the parent of the current node.
					while (parentNode != null) // Until we reach the root node. (Since the root doesn't have a parent)
					{
						// This wasn't the root node, so sent the parent to the node above again.
						parentNode = theTree.getParent();

						/*
						 * Update the result to include a trail of the path back up to the root.
						 * Add a '0' every time we go left.
						 * Add a '1' every time we go right.
						 */
						if (parentNode.getLeft() == theTree)
							encodedString.append('0');
						else
							encodedString.append('1');

						// Update theTree to be the parentNode now, if theTree is the rootNode, we have
						// reached the top of the Binary Tree again.
						theTree = parentNode;
						if (theTree == rootNode)
							break;
					}

					theTree = rootNode; // Set theTree as the rootNode so it can traverse the Binary Tree again.
					treeCache.dequeueAll(); // Clear the treeCache.
					completedChars++; // Increment the completedChars counter since we completed one character.
				} else {
					theTree = ((TreeNode) treeCache.dequeue()).getRight();
				}
			} else // This tree isn't a leaf.
			{
				// Queue the current tree into the cache so we know the path we went.
				treeCache.enqueue(theTree);
				theTree = theTree.getLeft();
			}
		}

		// Return the encoded string.
		return new StringBuilder(encodedString).reverse().toString();
	}

	/**
	 * Takes the given encoded string and outputs the decoded plain text message.
	 * 
	 * @param textToDecode The encoded string.
	 * @param theTree      The root of the Binary Tree to use for decoding.
	 * @return The decoded string.
	 */
	static String decodeString(String textToDecode, TreeNode theTree) {
		TreeNode rootNode = theTree; // Variable to reference the top of the tree since original will be adjusted.

		StringBuilder decodedMessage = new StringBuilder();

		for (int i = 0; i < textToDecode.length(); i++) {
			char currentChar = textToDecode.charAt(i);
			if (currentChar == '0') {
				theTree = theTree.getLeft();
			} else {
				theTree = theTree.getRight();
			}

			if (theTree.getLeft() == null && theTree.getRight() == null) {
				Frequency theFrequency = (Frequency) theTree.getItem();
				decodedMessage.append(theFrequency.getC());
				theTree = rootNode;
			}
		}

		return new StringBuilder(decodedMessage).reverse().toString();
	}

	/**
	 * Takes the given frequencyTable and generates a Binary Tree with it.
	 * 
	 * @param frequencyTable The table to use for generation.
	 * @return The root node of the tree which was generated containing all children
	 *         nodes.
	 */
	static TreeNode generateTree(QueueReferenceBased frequencyTable) {
		while (frequencyTable.getSize() != 1) // While the frequencyTable still has more than one object.
		{
			// Fetch two lowest frequencies from the table.
			TreeNode firstLowest = (TreeNode) frequencyTable.dequeue();
			TreeNode secondLowest = (TreeNode) frequencyTable.dequeue();

			// Get the sum of both the two lowest frequencies.
			int firstLowestFreq, secondLowestFreq;
			firstLowestFreq = ((Frequency) firstLowest.getItem()).getFreq();
			secondLowestFreq = ((Frequency) secondLowest.getItem()).getFreq();

			// Create new parent node and set the two lowest as its children.
			TreeNode parentNode = new TreeNode(new Frequency(firstLowestFreq + secondLowestFreq, '*'), firstLowest,
					secondLowest, null);

			// Set the parent of the two lowest nodes to be the one we just created.
			firstLowest.setParent(parentNode);
			secondLowest.setParent(parentNode);

			// Place the parentNode back into the queue in replacement of the two we took
			// out.
			frequencyTable.enqueue(parentNode);
		}

		// Return the final root node of the tree.
		return (TreeNode) frequencyTable.dequeue();
	}

	/**
	 * Populates a QueueReferenceBased list.
	 * 
	 * @return QueueReferenceBased The queue containing all the frequencies.
	 */
	static QueueReferenceBased getPopulatedList() {
		QueueReferenceBased list = new QueueReferenceBased(); // Create a new list to contain the tree.

		list.enqueue(new TreeNode(new Frequency(128, 'Z')));
		list.enqueue(new TreeNode(new Frequency(188, 'J')));
		list.enqueue(new TreeNode(new Frequency(205, 'Q')));
		list.enqueue(new TreeNode(new Frequency(315, 'X')));
		list.enqueue(new TreeNode(new Frequency(1257, 'K')));
		list.enqueue(new TreeNode(new Frequency(2019, 'V')));
		list.enqueue(new TreeNode(new Frequency(2715, 'B')));
		list.enqueue(new TreeNode(new Frequency(3316, 'P')));
		list.enqueue(new TreeNode(new Frequency(3693, 'G')));
		list.enqueue(new TreeNode(new Frequency(3819, 'W')));
		list.enqueue(new TreeNode(new Frequency(3853, 'Y')));
		list.enqueue(new TreeNode(new Frequency(4200, 'F')));
		list.enqueue(new TreeNode(new Frequency(4761, 'M')));
		list.enqueue(new TreeNode(new Frequency(4943, 'C')));
		list.enqueue(new TreeNode(new Frequency(5426, 'U')));
		list.enqueue(new TreeNode(new Frequency(7253, 'L')));
		list.enqueue(new TreeNode(new Frequency(7874, 'D')));
		list.enqueue(new TreeNode(new Frequency(10795, 'H')));
		list.enqueue(new TreeNode(new Frequency(10977, 'R')));
		list.enqueue(new TreeNode(new Frequency(11450, 'S')));
		list.enqueue(new TreeNode(new Frequency(12666, 'N')));
		list.enqueue(new TreeNode(new Frequency(13318, 'I')));
		list.enqueue(new TreeNode(new Frequency(14003, 'O')));
		list.enqueue(new TreeNode(new Frequency(14810, 'A')));
		list.enqueue(new TreeNode(new Frequency(16587, 'T')));
		list.enqueue(new TreeNode(new Frequency(21912, 'E')));

		return list;
	}
}

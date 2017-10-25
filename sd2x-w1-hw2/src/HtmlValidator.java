import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {

		/* IMPLEMENT THIS METHOD! */
		/*
		while queue is not empty
			1. get a tag from the queue
			2. 
				2.1 if it is self close: continue
				2.2 if it open: push to stask				
				2.3 if it close:
				 	if stack is not empty
						pop from stack
					    - if match: continue
					    - if not: return stack
					else 
						return null;
		Return stack	
		*/
		
		Stack<HtmlTag> stack = new Stack<>();
		while (!tags.isEmpty()) {
			HtmlTag tag = tags.remove();
			if (tag.isSelfClosing()) {
				continue;
			}
			if (tag.isOpenTag()) {
				stack.push(tag);
			} else {
				if (stack.isEmpty()) {
					return null;
				} else {
					HtmlTag savedTag = stack.peek();
					if (tag.matches(savedTag)) {
						stack.pop();
						continue;
					} else {
						return stack;
					}
				}
			}
		}
		
		return stack; // this line is here only so this code will compile if you don't modify it
	}
	

}


// Time Complexity : next - O(1), hasNext - amortized O(1) worst case O(n)
// Space Complexity : amortized O(1), worst case O(n) //for stack
// Did this code successfully run on Leetcode : yes
// Three line explanation of solution in plain english: We try to do dfs on the input nestedlist of nestedintegers, with 
/// external stack. We maintain a nextEle variable, which will be the next integer. If next element in nestedList is integer, we can 
/// directly assign to nextEle, else push list to stack, repea until we find next integer in list

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    List<NestedInteger> nestedList;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextEl = st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
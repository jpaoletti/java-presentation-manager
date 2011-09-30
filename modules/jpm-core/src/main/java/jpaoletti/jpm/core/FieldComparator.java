package jpaoletti.jpm.core;

import java.util.Comparator;

/**This class is used to sort fields within the order attribute of Entity
 * @author yero
 * @see Entity#order
 * 
 * */
public class FieldComparator implements Comparator<Field> {
    /**A space separated string with the fields id */
    private String order;
    
    /**Constructor with the specified order
     *@param order The order */
    public FieldComparator(String order){
        this.order = order;
    }

    /**Compare method.
     * @param o1 First field to compare
     * @param o2 Second field to compare
     * @return The lesser looking at order property
     * */
    @Override
    public int compare(Field o1, Field o2) {
        int i = order.indexOf(o1.getId());
        int j = order.indexOf(o2.getId());
        if(i==j) return 0;
        if(i==-1 && j >= 0) return 1;
        if(j==-1 && i >= 0) return -1;
        return i - j;
    }

    /**Setter for order
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**Getter for order
     * @return the order
     */
    public String getOrder() {
        return order;
    }

}

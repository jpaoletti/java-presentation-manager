package jpaoletti.jpm.core;

/**EntityOwner is the representation of the Entity Owner in weak entities. Programmer must define
 * the owner id, the owner (normally a collection) property that points to and, if exists, the property
 * of the local entity that points to the owner.
 * <pre>
 * {@code
 *  <owner>
 *     <entityId>owner_entity_id</entityId>
 *     <entityProperty>owner_property</entityProperty>
 *     <localProperty>local_pointer_to_owner</localProperty>
 *     <entityCollectionClass></entityCollectionClass>
 *  </owner>
 * }
 * 
 * @author jpaoletti
 * @see Entity#owner
 * */
public class EntityOwner extends PMCoreObject{
    /**The id of the owner entity*/
    private String entityId;
    /**The owner's entity property that contains the weak entity*/
    private String entityProperty;
    /**The property of the local entity that points to the owner (optional)*/
    private String localProperty;
    /**The collection class*/
    private String entityCollectionClass;
    /**Optional list position on ordered lists */
    private String localPosition;
    
    /**
     * @return the entityId
     */
    public String getEntityId() {
        return entityId;
    }
    /**
     * @param entityId the entityId to set
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
    /**
     * @return the entityProperty
     */
    public String getEntityProperty() {
        return entityProperty;
    }
    /**
     * @param entityProperty the entityProperty to set
     */
    public void setEntityProperty(String entityProperty) {
        this.entityProperty = entityProperty;
    }
    /**
     * @return the localProperty
     */
    public String getLocalProperty() {
        return localProperty;
    }
    /**
     * @param localProperty the localProperty to set
     */
    public void setLocalProperty(String localProperty) {
        this.localProperty = localProperty;
    }
    /**
     * @return the entityCollectionClass
     */
    public String getEntityCollectionClass() {
        return entityCollectionClass;
    }
    /**
     * @param entityCollectionClass the entityCollectionClass to set
     */
    public void setEntityCollectionClass(String entityCollectionClass) {
        this.entityCollectionClass = entityCollectionClass;
    }

    /**
     * Getter for localPosition
     * @return The localPosition
     */
    public String getLocalPosition() {
        return localPosition;
    }

    /**
     *
     * @param localPosition
     */
    public void setLocalPosition(String localPosition) {
        this.localPosition = localPosition;
    }

    
}

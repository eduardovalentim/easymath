package br.com.easymath.processor.mathematical.operation.operand;

/**
 * @author eduardo.valentim
 */
public class AbstractOperand implements Operand {

    private String id;
    private String type;
    private String value;
    
    /**
     * 
     * @param id
     * @param type
     * @param value
     */
    public AbstractOperand(String id, String type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return value;
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractOperand)) {
            return false;
        }
        AbstractOperand other = (AbstractOperand) obj;
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + "]";
    }

}

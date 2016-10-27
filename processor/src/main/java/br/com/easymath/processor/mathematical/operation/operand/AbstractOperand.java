package br.com.easymath.processor.mathematical.operation.operand;

/**
 * A abstract operand definition to be specialized
 * 
 * @author eduardo.valentim
 */
public abstract class AbstractOperand implements Operand {

    private String id;
    private String type;
    private String value;
    
    /**
     * Public constructor
     * 
     * @param id The identifier
     * @param type The type
     * @param value The value
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
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

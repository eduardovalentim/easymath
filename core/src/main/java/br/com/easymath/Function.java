package br.com.easymath;

public interface Function<T> {

	/**
	 * Define the name of the function to be used in the catalog
	 * 
	 * @return
	 */
    public String name();
    
    /**
     * Execute the calculation
     * 
     * @param inputs
     * @return
     */
    public T perform(Number... inputs);
}

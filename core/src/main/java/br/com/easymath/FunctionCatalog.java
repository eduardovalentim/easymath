package br.com.easymath;

import static java.text.MessageFormat.format;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eduardo.valentim
 *
 */
public class FunctionCatalog {

	/**
	 * The name of this catalog
	 */
	private String name;

	/**
	 * A collection of correlated functions
	 */
	private Map<String, Function<?>> functions;

	/**
	 * Default public constructor
	 */
	public FunctionCatalog() {
		super();
		/*
		 * Define the name of this catalog
		 */
		this.name = "default";
		/*
		 * Prepare the collection
		 */
		this.functions = new HashMap<>();
	}

	/**
	 * 
	 * @param functions
	 */
	public FunctionCatalog(String name, Function<?>... functions) {
		this();
		/*
		 * Method protection
		 */
		if (functions == null)
			throw new IllegalArgumentException("Argument 'functions' cannot be null. ");
		if (name == null)
			throw new IllegalArgumentException("Argument 'name' cannot be null. ");
		if (name.isEmpty())
			throw new IllegalArgumentException("Argument 'name' cannot be empty. ");
		/*
		 * Define the name of this catalog
		 */
		this.name = name;
		/*
		 * For each function
		 */
		for (int index = 0; index < functions.length; index++) {
			/*
			 * Get a reference of the function
			 */
			Function<?> function = functions[index];
			/*
			 * Validate
			 */
			if (function == null)
				throw new IllegalArgumentException(format("Argument ''functions[{0}]'' cannot be null.", index));
			/*
			 * Add
			 */
			addFunction(function);
		}
	}

	/**
	 * 
	 * @param name
	 * @param functions
	 */
	public FunctionCatalog(String name, Collection<Function<?>> functions) {
		this();
		/*
		 * Method protection
		 */
		if (functions == null)
			throw new IllegalArgumentException("Argument 'functions' cannot be null. ");
		if (name == null)
			throw new IllegalArgumentException("Argument 'name' cannot be null. ");
		if (name.isEmpty())
			throw new IllegalArgumentException("Argument 'name' cannot be empty. ");
		/*
		 * Define the name of this catalog
		 */
		this.name = name;
		/*
		 * Add all
		 */
		addAllFunctions(functions);
	}

	/**
	 * Return the name of this catalog
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param function
	 * @return
	 */
	public boolean addFunction(Function<?> function) {
		/*
		 * Method protection
		 */
		if (function == null)
			throw new IllegalArgumentException("Argument 'function' cannot be null.");
		if (function.name() == null)
			throw new IllegalArgumentException("Argument 'function.name()' cannot be null.");
		if (function.name().isEmpty())
			throw new IllegalArgumentException("Argument 'function.name()' cannot be empty.");

		return functions.putIfAbsent(function.name(), function) == null;
	}

	/**
	 * 
	 * @param functions
	 */
	public void addAllFunctions(Collection<Function<?>> functions) {
		/*
		 * Method protection
		 */
		if (functions == null)
			throw new IllegalArgumentException("Argument 'functions' cannot be null.");

		functions.forEach(this::addFunction);
	}

	/**
	 * @param name
	 * @param inputs
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Number> T peform(String name, Number... inputs) {
		/*
		 * Method protection
		 */
		if (name == null)
			throw new IllegalArgumentException("Argument 'name' cannot be null.");
		if (name.isEmpty())
			throw new IllegalArgumentException("Argument 'name' cannot be empty.");
		if (inputs == null)
			throw new IllegalArgumentException("Argument 'inputs' cannot be null.");
		/*
		 * Get a reference to the function by name
		 */
		Function<T> function = (Function<T>) functions.get(name);
		/*
		 * Validate if the function exist in catalog
		 */
		if (function == null) {
			throw new IllegalStateException(format("Function name ''{0}'' not found in this catalog.", name));
		}
		/*
		 * Excute the calculation
		 */
		return function.perform(inputs);
	}
}

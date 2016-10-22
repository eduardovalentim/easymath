package br.com.easymath;

import static java.text.MessageFormat.format;

import java.math.MathContext;
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
	 * @param name
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
	 * 
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
	public void addFunction(Function<?> function) {
		/*
		 * Method protection
		 */
		if (function == null)
			throw new IllegalArgumentException("Argument 'function' cannot be null.");
		if (function.name() == null)
			throw new IllegalArgumentException("Argument 'function.name()' cannot be null.");
		if (function.name().isEmpty())
			throw new IllegalArgumentException("Argument 'function.name()' cannot be empty.");

		functions.put(function.name(), function);
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
		/*
		 * Add all function one-by-one
		 */
		functions.forEach(this::addFunction);
	}

	/**
	 * @param name
	 * @param mc
	 * @param inputs
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Number> T solve(String name, MathContext mc, Number... inputs) {
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
		if (function == null) 
			throw new IllegalStateException(format("Function name ''{0}'' not found in this catalog.", name));
		/*
		 * Execute the calculation
		 */
		return function.perform(mc, inputs);
	}

	/**
	 * 
	 * @param catalogs
	 * @return
	 */
	public final FunctionCatalog join(FunctionCatalog... catalogs) {
		/*
		 * Method protection
		 */
		if (catalogs == null)
			throw new IllegalArgumentException("Argument 'catalogs' cannot be null.");
		/*
		 * For each catalog
		 */
		for (int index = 0; index < catalogs.length; index++) {
			/*
			 * Validate if the catalog is valid
			 */
			if (catalogs[index] == null) 
				throw new IllegalArgumentException(format("Argument ''catalogs[{0}]'' cannot be null.", index));
			/*
			 * Get a reference for all function in catalog
			 */
			this.addAllFunctions(catalogs[index].functions.values());
		}
		/*
		 * Return the result
		 */
		return this;
	}
	
	public static FunctionCatalog valueOf(Function<?>...functions) {
		/*
		 * Method protection
		 */
		if (functions == null)
			throw new IllegalArgumentException("Argument 'functions' cannot be null.");
		/*
		 * Default result
		 */
		FunctionCatalog result = new FunctionCatalog();
		/*
		 * For each catalog
		 */
		for (int index = 0; index < functions.length; index++) {
			/*
			 * Validate if the catalog is valid
			 */
			if (functions[index] == null) 
				throw new IllegalArgumentException(format("Argument ''functions[{0}]'' cannot be null.", index));
			/*
			 * Get a reference for all functions
			 */
			result.addFunction(functions[index]);
		}
		/*
		 * Result
		 */
		return result;
	}
}

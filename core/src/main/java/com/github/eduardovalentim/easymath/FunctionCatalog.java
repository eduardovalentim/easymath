package com.github.eduardovalentim.easymath;

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
	private Map<String, Function<? extends Number>> functions;

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
	 * Public constructor
	 * 
	 * @param name
	 *            The name of this catalog
	 * @param functions
	 *            A 'list'of functions
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
	 * Public constructor
	 * 
	 * @param name
	 *            The name of this catalog
	 * @param functions
	 *            A 'list'of functions
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
	 * @return The name of this catalog
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Add a function to this catalog
	 * 
	 * @param function
	 *            The function to be added
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
	 * A 'list' of functions to be added
	 * 
	 * @param functions
	 *            Functions to be added
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
	 * Find a function to solve
	 * 
	 * @param name The name of the function
	 * @param mc The mathematical context to use
	 * @param inputs The formula inputs
	 * @param <T> The return type
	 * 
	 * @return The result of the calculation
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
		Function<? extends Number> function = functions.get(name);
		/*
		 * Validate if the function exist in catalog
		 */
		if (function == null)
			throw new IllegalStateException(format("Function name ''{0}'' not found in this catalog.", name));
		/*
		 * Execute the calculation
		 */
		return (T) function.perform(mc, inputs);
	}

	/**
	 * Join this catalog with others
	 * 
	 * @param catalogs
	 *            Others catalog to join
	 * @return A new catalog
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

	/**
	 * Create a new catalog based on the functions
	 * 
	 * @param functions
	 *            A 'list' of functions to create a new catalog
	 * @return A new catalog
	 */
	public static FunctionCatalog valueOf(String name, Function<?>... functions) {
		/*
		 * Result
		 */
		return new FunctionCatalog(name, functions);
	}
}

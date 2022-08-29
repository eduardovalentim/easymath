package com.github.eduardovalentim.easymath.processor.mathematical.utils;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.github.eduardovalentim.easymath.annotations.Formula;

/**
 * Utility class to handle reflection
 * 
 * @author eduardo.valentim
 */
public class ReflectionUtils {

	private static final String ARGUMENT_ELEMENT_CANNOT_BE_NULL = "Argument 'element' cannot be null.";
	private static final String ARGUMENT_ELEMENT_GET_KIND_CANNOT_BE_NULL = "Argument 'element.getKind()' cannot be null.";
	
	private Types types;
	private Elements elements;

	private TypeMirror numberType;

	/**
	 * Default public constructor
	 * 
	 * @param types
	 *            The types
	 * @param elements
	 *            The elements
	 */
	public ReflectionUtils(Types types, Elements elements) {
		super();
		this.types = requireNonNull(types);
		this.elements = requireNonNull(elements);

		numberType = this.elements.getTypeElement(Number.class.getTypeName()).asType();
	}

	/**
	 * Check if the element is a method
	 * 
	 * @param element
	 *            The element
	 * @return True if the elements is a method
	 */
	public boolean isMethod(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		requireNonNull(element.getKind(), ARGUMENT_ELEMENT_GET_KIND_CANNOT_BE_NULL);
		/*
		 * Result
		 */
		return element.getKind() == ElementKind.METHOD;
	}

	/**
	 * Check if the element is a class
	 * 
	 * @param element
	 *            The element
	 * @return True if the element is a class
	 */
	public boolean isClass(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		requireNonNull(element.getKind(), ARGUMENT_ELEMENT_GET_KIND_CANNOT_BE_NULL);
		/*
		 * Result
		 */
		return element.getKind().isClass();
	}

	/**
	 * Check if the element has the modifier abstract
	 * 
	 * @param element
	 *            The element
	 * @return True if the element is abstract
	 */
	public boolean isAbstract(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		requireNonNull(element.getModifiers(), "Argument 'element.getModifiers()' cannot be null.");
		/*
		 * Result
		 */
		return element.getModifiers().contains(Modifier.ABSTRACT);
	}

	/**
	 * Check if the element is a class and has the modifier abstract
	 * 
	 * @param element
	 *            The element
	 * @return True if the element is abstract and is a class
	 */
	public boolean isAbstractClass(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Result
		 */
		return isAbstract(element) && isClass(element);
	}

	/**
	 * Check if the element is a abstract method
	 * 
	 * @param element
	 *            The element
	 * @return True if the element is a abstract method
	 */
	public boolean isAbstractMethod(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Result
		 */
		return isMethod(element) && isAbstract(element);
	}

	/**
	 * Check if the element has a varargs argument
	 * 
	 * @param element
	 *            The element
	 * @return True if the element has a varargs argument
	 */
	public boolean isVarArgsMethod(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Default result
		 */
		boolean result = false;
		/*
		 * Check if the element is a method
		 */
		if (isMethod(element)) {
			/*
			 * Check the var args
			 */
			result = ((ExecutableElement) element).isVarArgs();
		}
		/*
		 * Result
		 */
		return result;
	}

	/**
	 * Check if the method return a number
	 * 
	 * @param element
	 *            The element
	 * @return True if the method return a number
	 */
	public boolean isMethodReturningNumber(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Default result
		 */
		boolean result = false;
		/*
		 * Check if the element is a method
		 */
		if (isMethod(element)) {
			/*
			 * Check the var args
			 */
			TypeMirror returnType = ((ExecutableElement) element).getReturnType();
			result = types.isAssignable(returnType, numberType);
		}
		/*
		 * Result
		 */
		return result;
	}

	/**
	 * Get number of arguments of a method
	 * 
	 * @param element The element
	 * @return The number of arguments of a method
	 */
	public int getMethodParametersCount(Element element) {
		/*
		 * Method protection
		 */
		Objects.requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Default result
		 */
		int result = 0;
		/*
		 * Check if the element is a method
		 */
		if (isMethod(element)) {
			/*
			 * Get the numbers of parameters
			 */
			result = ((ExecutableElement) element).getParameters().size();
		}
		/*
		 * Result
		 */
		return result;
	}

	/**
	 * Get the returning type of a method
	 * @param element The element
	 * @return The string representation of a type. Ex: java.lang.Double
	 */
	public String getMethodReturningType(ExecutableElement element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Default result
		 */
		String result = null;
		/*
		 * Check if the element is a method
		 */
		if (isMethodReturningNumber(element)) {
			/*
			 * Get return type
			 */
			result = element.getReturnType().toString();
		}
		/*
		 * Result
		 */
		return result;
	}

	/**
	 * The name of a element
	 * @param element The element
	 * @return The name
	 */
	public String getName(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Result
		 */
		return element.getSimpleName().toString();
	}

	/**
	 * Returning the annotation @Formula
	 * @param element The element
	 * @return The formula
	 */
	public Formula getMethodFormula(ExecutableElement element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, ARGUMENT_ELEMENT_CANNOT_BE_NULL);
		/*
		 * Result
		 */
		return element.getAnnotation(Formula.class);
	}
}

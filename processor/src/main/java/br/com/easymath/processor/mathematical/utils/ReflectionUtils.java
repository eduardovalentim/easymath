package br.com.easymath.processor.mathematical.utils;

import java.util.Objects;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

public class ReflectionUtils {

	/**
	 * Check if the element is a class
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isClass(Element element) {
		/*
		 * Method protection
		 */
		Objects.requireNonNull(element, "Argument 'element' cannot be null.");
		Objects.requireNonNull(element.getKind(), "Argument 'element.getKind()' cannot be null.");
		/*
		 * Result
		 */
		return element.getKind().isClass();
	}

	/**
	 * Check if the element has the modifier abstract
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isAbstract(Element element) {
		/*
		 * Method protection
		 */
		Objects.requireNonNull(element, "Argument 'element' cannot be null.");
		Objects.requireNonNull(element.getModifiers(), "Argument 'element.getModifiers()' cannot be null.");
		/*
		 * Result
		 */
		return element.getModifiers().contains(Modifier.ABSTRACT);
	}
	
	/**
	 * Check if the element is a class and has the modifier abstract
	 * @param element
	 * @return
	 */
	public static boolean isAbstractClass(Element element) {
		/*
		 * Method protection
		 */
		Objects.requireNonNull(element, "Argument 'element' cannot be null.");
		/*
		 * Result
		 */
		return isAbstract(element) && isClass(element);
	}
}

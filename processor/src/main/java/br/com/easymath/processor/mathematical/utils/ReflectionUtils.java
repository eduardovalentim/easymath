package br.com.easymath.processor.mathematical.utils;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import br.com.easymath.annotations.Formula;

/**
 * @author eduardo.valentim
 */
public class ReflectionUtils {

    private Types types;
    private Elements elements;

    private TypeMirror numberType;

    /**
     * Default public constructor
     * 
     * @param types
     * @param elements
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
     * @return
     */
    public boolean isMethod(Element element) {
        /*
         * Method protection
         */
        requireNonNull(element, "Argument 'element' cannot be null.   ");
        requireNonNull(element.getKind(), "Argument 'element.getKind()' cannot be null.");
        /*
         * Result
         */
        return element.getKind() == ElementKind.METHOD;
    }
    
    /**
	 * Check if the element is a class
	 * 
	 * @param element
	 * @return
	 */
	public boolean isClass(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, "Argument 'element' cannot be null.   ");
		requireNonNull(element.getKind(), "Argument 'element.getKind()' cannot be null.");
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
	public boolean isAbstract(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, "Argument 'element' cannot be null.  ");
		requireNonNull(element.getModifiers(), "Argument 'element.getModifiers()' cannot be null.");
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
	public boolean isAbstractClass(Element element) {
		/*
		 * Method protection
		 */
		requireNonNull(element, "Argument 'element' cannot be null.  ");
		/*
		 * Result
		 */
		return isAbstract(element) && isClass(element);
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
    public boolean isAbstractMethod(Element element) {
        /*
         * Method protection
         */
        requireNonNull(element, "Argument 'element' cannot be null. ");
        /*
         * Result
         */
        return isMethod(element) && isAbstract(element);
    }

    /**
     * 
     * @param element
     * @return
     */
    public boolean isVarArgsMethod(Element element) {
        /*
         * Method protection
         */
        requireNonNull(element, "Argument 'element' cannot be null. ");
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
            result = ((ExecutableElement)element).isVarArgs();
        }
        /*
         * Result
         */
        return result;
    }
    
    /**
     * 
     * @param element
     * @return
     */
    public boolean isMethodReturningNumber(Element element) {
        /*
         * Method protection
         */
        requireNonNull(element, "Argument 'element' cannot be null.");
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
            TypeMirror returnType = ((ExecutableElement)element).getReturnType();
            result = types.isAssignable(returnType, numberType);
        }
        /*
         * Result
         */
        return result;
    }
    
    /**
     * 
     * @param element
     * @return
     */
    public int getMethodParametersCount(Element element) {
        /*
         * Method protection
         */
        Objects.requireNonNull(element, "Argument 'element' cannot be null.");
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
            result = ((ExecutableElement)element).getParameters().size();
        }
        /*
         * Result
         */
        return result;
    }    

    /**
     * 
     * @param element
     * @return
     */
    public String getMethodReturningType(ExecutableElement element) {
        /*
         * Method protection
         */
        requireNonNull(element, "Argument 'element' cannot be null.");
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
     * 
     * @param element
     * @return
     */
    public String getName(Element element) {
        /*
         * Method protection
         */
        requireNonNull(element, "Argument 'element' cannot be null.");
        /*
         * Result
         */
        return element.getSimpleName().toString();
    }

    /**
     * @param element
     * @return
     */
    public Formula getMethodFormula(ExecutableElement element) {
        /*
         * Method protection
         */
        requireNonNull(element, "Argument 'element' cannot be null.      ");
        /*
         * Result
         */
        return element.getAnnotation(Formula.class);
    }
}

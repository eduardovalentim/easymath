package com.github.eduardovalentim.easymath.processor.mathematical.generators;

import static java.util.Objects.requireNonNull;

import javax.annotation.processing.Filer;

import com.github.eduardovalentim.easymath.annotations.GenerationMode;
import com.github.eduardovalentim.easymath.processor.mathematical.GeneratorStrategy;

public class GeneratorFactory {
	
	private Filer filer;

	public GeneratorFactory(Filer filer) {
		this.filer = requireNonNull(filer, "Argument 'filer' cannot be null.");
	}

	public GeneratorStrategy createGenerator(GenerationMode mode) {
		requireNonNull(mode, "Argument 'mode' cannot be null.");
		
		switch (mode) {
		case JAVA:
			return new JavaGenerator(filer);
		case NATIVE:
			return new NativeGenerator(filer);
		default:
			throw new IllegalStateException("Unknown generation mode: " + mode);
		}
	}
}

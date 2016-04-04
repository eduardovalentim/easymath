package org.bitbucket.easymath.processor.mathematical.resolution;

public class TagGenerator {

	private int count;
	private int precision;

	public TagGenerator(int precision) {
		this.precision = precision;
		this.count = 0;
	}

	public String generate() {
		return "%" + ++count + "$." + precision + "f";
	}

}

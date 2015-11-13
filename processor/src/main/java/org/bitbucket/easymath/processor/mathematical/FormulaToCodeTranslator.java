package org.bitbucket.easymath.processor.mathematical;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bitbucket.easymath.annotations.Function.Type;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaBaseListener;

public class FormulaToCodeTranslator extends FormulaBaseListener {

    private static final Logger LOGGER = LogManager.getLogger(FormulaToCodeTranslator.class);

    private JavaCode context;
    
	public FormulaToCodeTranslator(String name, String expression, Type aType) {
	    String type = aType == Type.BIGDECIMAL ? "BigDecimal" : "double";
	    context = new JavaCode(name, expression, type);
	}

	@Override
	public void visitTerminal(TerminalNode node) {
        LOGGER.info("visitTerminal: {}", node);
	}
	
	public JavaCode translate() {
		return context;
	}

}

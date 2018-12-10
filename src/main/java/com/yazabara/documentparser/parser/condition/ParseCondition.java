package com.yazabara.documentparser.parser.condition;

import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.function.Function;

public interface ParseCondition extends Function<IBodyElement, Boolean> {
}

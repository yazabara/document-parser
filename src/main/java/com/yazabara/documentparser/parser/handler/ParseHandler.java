package com.yazabara.documentparser.parser.handler;

import org.apache.poi.xwpf.usermodel.IBodyElement;

import java.util.function.Consumer;

public interface ParseHandler extends Consumer<IBodyElement> {
}

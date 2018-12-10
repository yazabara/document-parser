package com.yazabara.documentparser.parser;

import com.yazabara.documentparser.parser.condition.ParseCondition;
import com.yazabara.documentparser.parser.condition.ParseRangeCondition;
import com.yazabara.documentparser.parser.handler.ParseHandler;
import com.yazabara.documentparser.parser.handler.ParseRangeHandler;

public interface Parser {

    Parser addHandler(ParseCondition condition, ParseHandler handler);

    Parser addPairHandler(ParseRangeCondition condition, ParseRangeHandler handler);

    void parse();
}

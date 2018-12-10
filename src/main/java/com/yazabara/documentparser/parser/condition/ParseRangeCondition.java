package com.yazabara.documentparser.parser.condition;

import lombok.Data;

@Data
public class ParseRangeCondition {

    private ParseCondition start;
    private ParseCondition end;
    private boolean active = false;

    public ParseRangeCondition withStart(ParseCondition start) {
        this.start = start;
        return this;
    }

    public ParseRangeCondition withEnd(ParseCondition end) {
        this.end = end;
        return this;
    }
}

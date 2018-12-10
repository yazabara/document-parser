package com.yazabara.documentparser.parser.handler;

import lombok.Data;

@Data
public class ParseRangeHandler {

    private ParseHandler first;
    private ParseHandler common;
    private ParseHandler last;

    public ParseRangeHandler withFirst(ParseHandler first) {
        this.first = first;
        return this;
    }

    public ParseRangeHandler withCommon(ParseHandler common) {
        this.common = common;
        return this;
    }

    public ParseRangeHandler withLast(ParseHandler last) {
        this.last = last;
        return this;
    }
}

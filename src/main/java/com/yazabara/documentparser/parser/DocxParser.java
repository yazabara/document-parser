package com.yazabara.documentparser.parser;

import com.yazabara.documentparser.parser.condition.ParseCondition;
import com.yazabara.documentparser.parser.condition.ParseRangeCondition;
import com.yazabara.documentparser.parser.handler.ParseHandler;
import com.yazabara.documentparser.parser.handler.ParseRangeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class DocxParser implements Parser {

    private XWPFDocument document;
    private Map<ParseCondition, ParseHandler> listeners = new HashMap<>();
    private Map<ParseRangeCondition, ParseRangeHandler> rangeListeners = new HashMap<>();

    public DocxParser(String filePath) throws IOException, InvalidFormatException {
        this.document = new XWPFDocument(OPCPackage.open(new FileInputStream(filePath)));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.document != null) {
            this.document.close();
        }
    }

    @Override
    public DocxParser addHandler(ParseCondition condition, ParseHandler handler) {
        if (condition != null && handler != null) {
            this.listeners.put(condition, handler);
        }
        return this;
    }

    @Override
    public DocxParser addRangeHandler(ParseRangeCondition condition, ParseRangeHandler handler) {
        if (condition != null && handler != null) {
            this.rangeListeners.put(condition, handler);
        }
        return this;
    }

    @Override
    public void parse() {
        if (MapUtils.isEmpty(listeners) && MapUtils.isEmpty(rangeListeners)) {
            log.warn("No listeners detected: parsing won't work");
            return;
        }
        Iterator<IBodyElement> iter = document.getBodyElementsIterator();
        while (iter.hasNext()) {
            IBodyElement elem = iter.next();
            //find single objects
            listeners.forEach((condition, handler) -> {
                if (condition.apply(elem) && handler != null) {
                    handler.accept(elem);
                }
            });
            // multiply objects handler
            rangeListeners.forEach((condition, handler) -> {
                if (condition == null || handler == null) {
                    return;
                }
                //find end
                if (condition.getEnd() != null
                        && condition.isActive()
                        && condition.getEnd().apply(elem)
                        && handler.getLast() != null) {
                    condition.setActive(false);
                    handler.getLast().accept(elem);
                }
                //find common
                if (condition.isActive() && handler.getCommon() != null) {
                    handler.getCommon().accept(elem);
                }
                //find starts
                if (condition.getStart() != null
                        && condition.getStart().apply(elem)
                        && handler.getFirst() != null) {
                    handler.getFirst().accept(elem);
                    condition.setActive(true);
                }
            });
        }
        //finish all active ranges
        rangeListeners.forEach((condition, handler) -> {
            if (condition.isActive() && handler.getLast() != null) {
                handler.getLast().accept(null);
            }
        });
    }
}

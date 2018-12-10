# document-parser

Application parse docx file:
- there are 2 type handlers: single object and multy object (from-to).

To add some handler use class: ```com.yazabara.documentparser.parser.DocxParser```

Example:
```$xslt
new DocxParser(config.getPath())
                .addHandler(
                    new ParseCondition(),
                    new ParseHandler()
                ),
                .addPairHandler(
                     new ParseRangeCondition()
                        .withStart(...)
                        .withEnd(...),
                     new ParseRangeHandler()
                         .withFirst(...)
                         .withCommon(...)
                         .withLast(...)
                 ),
                 ...
                 .parse()
```

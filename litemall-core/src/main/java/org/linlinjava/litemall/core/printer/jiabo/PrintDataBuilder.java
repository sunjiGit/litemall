package org.linlinjava.litemall.core.printer.jiabo;

public class PrintDataBuilder {

    private StringBuilder stringBuilder;

    private String titleFormat = "<gpWord Align=1 Bold=0 Wsize=1 Hsize=1 Reverse=0 Underline=0>%s</gpWord>\n";
    private String normalWordFormat = "<gpWord Align=0 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>%s</gpWord>\n";
    private String boldWordFormat = "<gpWord Align=0 Bold=1 Wsize=0 Hsize=0 Reverse=0 Underline=0>%s</gpWord>\n";
    private String tableTr4Format = "<gpTR4 Type=0><td>%s</td><td>%s</td><td>%s</td><td>%s</td></gpTR4>\n";
    private String tableTr2Format = "<gpTR2 Type=0><td>%s</td><td>%s</td></gpTR2>\n";

    private String separator = String.format(normalWordFormat, "--------------------------------");
    private String blank = "<gpBr/>\n";
    private String goodsTitle = String.format(tableTr4Format,"商品名称","","数量","金额");

    PrintDataBuilder() {
        this.stringBuilder = new StringBuilder();
        this.stringBuilder.append(separator);
    }

    public PrintDataBuilder addTitle(String title) {
        this.stringBuilder.append(String.format(titleFormat, title));
        return this;
    }
    public PrintDataBuilder addGoodsTitle() {
        this.stringBuilder.append(goodsTitle);
        return this;
    }
    public PrintDataBuilder addWord(String word) {
        this.stringBuilder.append(String.format(normalWordFormat, word));
        return this;
    }
    public PrintDataBuilder addBoldWord(String word) {
        this.stringBuilder.append(String.format(boldWordFormat, word));
        return this;
    }
    public PrintDataBuilder addTable2(String td1, String td2) {
        this.stringBuilder.append(String.format(tableTr2Format,td1, td2));
        return this;
    }
    public PrintDataBuilder addTable4(String td1, String td2, String td3, String td4) {
        this.stringBuilder.append(String.format(tableTr4Format,td1, td2, td3, td4));
        return this;
    }
    public PrintDataBuilder addBR() {
        this.stringBuilder.append(blank);
        return this;
    }

    public String toPrintString() {
        return this.stringBuilder.append(separator).append(blank).toString();
    }

}

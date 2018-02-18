package books.model;

import org.springframework.stereotype.Repository;

@Repository
public class Filter {

    public enum ReadAlready {
        ALL,
        READ,
        NOREAD
    }

    public enum BeforeAfter {
        BEFORE,
        AFTER
    }

    private ReadAlready readAlready = ReadAlready.ALL;
    private BeforeAfter beforeAfter = BeforeAfter.BEFORE;
    private int printYear = 0;
    private String title = "";

    private int firstResult;
    private int maxResult = 10;

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public boolean isTitleFilter() {
        return title != null && !title.isEmpty();
    }

    public boolean isReadAlreadyFilter() {
        return readAlready != ReadAlready.ALL;
    }

    public boolean isReadAlready() {
        return readAlready == ReadAlready.READ;
    }

    public boolean isNotReadAlready() {
        return readAlready == ReadAlready.NOREAD;
    }

    public int getPrintYear() {
        return printYear;
    }

    public boolean isBeforePrintYearFilter() {
        return (printYear>0) && (beforeAfter == BeforeAfter.BEFORE);
    }

    public boolean isAfterPrintYearFilter() {
        return (printYear>0) && (beforeAfter == BeforeAfter.AFTER);
    }

    public String getTitle() {
        return title == null? "": title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReadAlready(ReadAlready readAlready) {
        this.readAlready = readAlready;
    }

    public void setPrintYear(int printYear, BeforeAfter beforeAfter) {
        this.printYear = printYear;
        this.beforeAfter = beforeAfter;
    }

    public ReadAlready getReadAlready() {
        return readAlready;
    }

    public BeforeAfter getBeforeAfter() {
        return beforeAfter;
    }

    public void clearAll() {
        setPrintYear(0, BeforeAfter.BEFORE);
        setTitle("");
        setReadAlready(ReadAlready.ALL);
    }
}

package books.forms;

import books.model.Filter;

import java.util.LinkedHashMap;
import java.util.Map;

public class FilterForm {
    private String title = "";
    private String readAlready = "";
    private String printYear = "";
    private String beforeAfter = "";

    public FilterForm() {
    }

    public FilterForm(Filter filter) {
        title = filter.getTitle();
        readAlready = filter.getReadAlready().name();
        printYear = String.valueOf(filter.getPrintYear());
        beforeAfter = filter.getBeforeAfter().name();
    }

    public static Map<String, String> makeMapPrintYearValues() {
        Map<String, String> result = new LinkedHashMap<String, String>();
        result.put(Filter.BeforeAfter.BEFORE.name(), "До выбранного года (включительно)");
        result.put(Filter.BeforeAfter.AFTER.name(), "После выбранного года (включительно)");
        return result;
    }

    public static Map<String, String> makeMapReadAlreadyValues() {
        Map<String, String> result = new LinkedHashMap<String, String>();
        result.put(Filter.ReadAlready.ALL.name(), "Все");
        result.put(Filter.ReadAlready.READ.name(), "Прочитанные");
        result.put(Filter.ReadAlready.NOREAD.name(), "Не прочитанные");
        return result;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReadAlready() {
        return readAlready;
    }

    public void setReadAlready(String readAlready) {
        this.readAlready = readAlready;
    }

    public String getPrintYear() {
        return printYear;
    }

    public void setPrintYear(String printYear) {
        this.printYear = printYear;
    }

    public String getBeforeAfter() {
        return beforeAfter;
    }

    public void setBeforeAfter(String beforeAfter) {
        this.beforeAfter = beforeAfter;
    }



}

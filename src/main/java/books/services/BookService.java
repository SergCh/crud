package books.services;

import books.model.Book;
import books.model.BookDAO;
import books.model.Filter;
import books.forms.FilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private Filter filter;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Transactional
    public void addBook(Book book) {
        bookDAO.addBook(book.getTitle(), book.getDescription(), book.getAuthor(), book.getIsbn(), book.getPrintYear());
    }

    @Transactional
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    @Transactional
    public void updateBook(Book book) {
        bookDAO.updateBook(book.getId(), book.getTitle(), book.getDescription(), book.getIsbn(), book.getPrintYear());
    }

    @Transactional
    public void readBook(int id) {
        bookDAO.readBook(id);
    }

    @Transactional
    public Book getBook(int id) {
        return bookDAO.getBook(id);
    }

    @Transactional
    public List<Book> getBooks() {
        int currFirstResult = filter.getFirstResult();
        int maxRecords = bookDAO.getCount(filter);

        if (currFirstResult >= maxRecords) {
            currFirstResult = maxRecords - 10;
        }
        if (currFirstResult < 0) {
            currFirstResult = 0;
        }

        filter.setFirstResult(currFirstResult);
        return bookDAO.getBooks(filter);
    }

    public FilterForm getFilterForm() {
        return new FilterForm(filter);
    }

    public void clearFilterForm() {
        filter.clearAll();
    }

    public void changeFirstResult(int delta) {
        filter.setFirstResult(filter.getFirstResult() + delta);
    }

    public void parseFilterForm(FilterForm filterForm) {
        parseFilterFormPrintYear(filterForm.getPrintYear(), filterForm.getBeforeAfter());
        parseFilterFormReadAlread(filterForm.getReadAlready());
        parseFilterFormTitle(filterForm.getTitle());
    }

    private void parseFilterFormPrintYear(String printYear, String beforeAfter) {
        int year = 0;

        if (printYear == null || beforeAfter == null) {
            filter.setPrintYear(0, Filter.BeforeAfter.BEFORE);
            return;
        }

        try {
            year = Integer.parseInt(printYear);
        } catch (Exception e) {
            year = 0;
        }

        if (year <= 0) {
            filter.setPrintYear(0, Filter.BeforeAfter.BEFORE);
            return;
        }

        if (Filter.BeforeAfter.BEFORE.name().equalsIgnoreCase(beforeAfter)) {
            filter.setPrintYear(year, Filter.BeforeAfter.BEFORE);
            return;
        }

        if (Filter.BeforeAfter.AFTER.name().equalsIgnoreCase(beforeAfter)) {
            filter.setPrintYear(year, Filter.BeforeAfter.AFTER);
            return;
        }
        filter.setPrintYear(0, Filter.BeforeAfter.BEFORE);
    }

    private void parseFilterFormReadAlread(String readAlready) {
        if (readAlready != null) {
            if (Filter.ReadAlready.READ.name().equalsIgnoreCase(readAlready)) {
                filter.setReadAlready(Filter.ReadAlready.READ);
                return;
            }

            if (Filter.ReadAlready.NOREAD.name().equalsIgnoreCase(readAlready)) {
                filter.setReadAlready(Filter.ReadAlready.NOREAD);
                return;
            }
        }
        filter.setReadAlready(Filter.ReadAlready.ALL);
    }

    private void parseFilterFormTitle(String title) {
        if (title != null)
            filter.setTitle(title);
        else
            filter.setTitle("");
    }
}
package books.controllers;

import books.model.Book;
import books.forms.FilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import books.services.BookService;

@Controller
//@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list")
    public String listContacts(ModelMap map) {
        map.put("bookList", bookService.getBooks());
        map.put("filterForm", bookService.getFilterForm());
        map.put("printYearValues" , FilterForm.makeMapPrintYearValues());
        map.put("readAlreadyValues" , FilterForm.makeMapReadAlreadyValues());
//        map.put("book", new Book());

        return "list";
    }

    @RequestMapping("/next")
    public String next() {
        bookService.changeFirstResult(10);
        return "redirect:/list";
    }

    @RequestMapping("/prev")
    public String prev() {
        bookService.changeFirstResult(-10);
        return "redirect:/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap map){
        map.put("title", "Добавление новой книги");
        map.put("authorReadonly", "false");
        map.put("action", "/addBook");
        map.put("book", new Book());
        return "bookform";
    }

    @RequestMapping(value = "/update/{bookId}")
    public String update(@PathVariable("bookId") Integer id, ModelMap map){
        map.put("title", "Новое издание книги");
        map.put("authorReadonly", "true");
        map.put("action", "/updateBook");
        map.put("book", bookService.getBook(id));
        return "bookform";
    }


    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/list";
    }

    @RequestMapping("/read/{id}")
    public String readBook(@PathVariable("id") Integer id) {
        bookService.readBook(id);
        return "redirect:/list";
    }

    @RequestMapping(value = "/addBook", params = "set", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String addBook(@ModelAttribute("book") Book book, BindingResult result) {
        bookService.addBook(book);
        return "redirect:/list";
    }

    @RequestMapping(value = "/addBook", params = "cansel", method = RequestMethod.POST)
    public String canselAddBook() {
        return "redirect:/list";
    }


    @RequestMapping(value = "/updateBook", params = "set", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String updateBook(@ModelAttribute("book") Book book, BindingResult result) {
        bookService.updateBook(book);
        return "redirect:/list";
    }

    @RequestMapping(value = "/updateBook", params = "cansel", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String canselUpdateBook(@ModelAttribute("book") Book book, BindingResult result) {
        return "redirect:/list";
    }

    @RequestMapping(value = "/setFilter", params = "set", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String setFilter(
            @ModelAttribute("filterForm") FilterForm filterForm
            ) {
        bookService.parseFilterForm(filterForm);
        return "redirect:/list";
    }

    @RequestMapping(value = "/setFilter", params = "clear", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String clearFilter(
            @ModelAttribute("filterForm") FilterForm filterForm
            ) {
        bookService.clearFilterForm();

        return "redirect:/list";
    }

}

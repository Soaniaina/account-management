package mg.unidev.app.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementExceptionHandler() {
        return new ModelAndView("layout/not-found")
                .addObject("status", HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler() {
        return new ModelAndView("layout/not-found")
                .addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
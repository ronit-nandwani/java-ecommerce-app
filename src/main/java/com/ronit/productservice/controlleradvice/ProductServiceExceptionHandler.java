package com.ronit.productservice.controlleradvice;

import com.ronit.productservice.dtos.ExceptionDto;
import com.ronit.productservice.dtos.ProductNotFoundExceptionDto;
import com.ronit.productservice.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ProductServiceExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong!");
        exceptionDto.setResolutionDetails("You need to pay more money to get it resolved from us. Thanks!");
        log.error("Unhandled RuntimeException occurred", ex);
        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException ex) {
        ProductNotFoundExceptionDto exceptionDto = new ProductNotFoundExceptionDto();
        exceptionDto.setProductId(ex.getProductId());

        exceptionDto.setMessage("Product not found!");
        exceptionDto.setResolution("Please try again with a valid product id");

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

}

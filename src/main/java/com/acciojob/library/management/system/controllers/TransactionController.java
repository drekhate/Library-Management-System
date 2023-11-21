package com.acciojob.library.management.system.controllers;

import com.acciojob.library.management.system.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/issueBook/{bookId}")
    public ResponseEntity issueBook(@PathVariable("bookId") Integer bookId,
                                    @RequestParam("cardNo") Integer cardNo) {
        String result;
        try {
            result = transactionService.issueBook(bookId, cardNo);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

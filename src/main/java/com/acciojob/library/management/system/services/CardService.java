package com.acciojob.library.management.system.services;

import com.acciojob.library.management.system.entitys.LibraryCard;
import com.acciojob.library.management.system.entitys.Student;
import com.acciojob.library.management.system.enums.CardStatus;
import com.acciojob.library.management.system.repositorys.CardRepository;
import com.acciojob.library.management.system.repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StudentRepository studentRepository;
    public LibraryCard generatePlainCard() {
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card = cardRepository.save(card);
        return card;
    }
    public String associateWithStudent(Integer studentId, Integer cardNo) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Student student = studentOptional.get();
        Optional<LibraryCard> libraryCardOptional = cardRepository.findById(cardNo);
        LibraryCard libraryCard = libraryCardOptional.get();
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNameOnCard(student.getName());
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);
        studentRepository.save(student);
        return "card with cardNo: " + cardNo + " has been associate to student with studentId: " + studentId;
    }
}

package com.example.demo.idcard;

import com.example.demo.student.Student;

import javax.persistence.*;

@Table(name = "id_card",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "student_id_card_unique",
                columnNames = "card_number"
        )
    }
)
@Entity(name = "IdCard")
public class StudentIdCard {

    @Id
    @SequenceGenerator(
            name = "student_card_id_sequence",
            sequenceName = "student_card_id_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_card_id_sequence"
    )
    private Long id;

    @Column(name = "card_number", nullable = false, length = 15)
    private String cardNumber;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_fk"
            )
    )
    private Student student;

    public StudentIdCard() {
    }

    public StudentIdCard(Long studentId, String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(Long id, Long studentId, String cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}

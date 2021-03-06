package de.hhu.abschlussprojektverleihplattform.testdummys;

import lombok.Data;

@Data
public class ReservationDummy {

    private Long id;
    private int amount;
    private String fromUsername;
    private String toUsername;

    public ReservationDummy(String payer, String reciver, int amount, Long id) {
        this.amount = amount;
        this.fromUsername = payer;
        this.toUsername = reciver;
        this.id = id;
    }

}


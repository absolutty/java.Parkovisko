package sk.uniza;

import sk.uniza.fri.Rozdelenie;

import java.awt.*;
import java.util.Scanner;

public class Parkovisko {
    private int aktualStav = 0;
    private Color farba = Color.GREEN;
    private static final String[] POVOLENE_STAVY = {"0,0", "0,1", "1,0", "1,1"};

    private static final String UKONCENIE = "koniec";
    public void spusti() {
        String volba = "";

        while (!volba.equals(UKONCENIE)) {
            volba = Parkovisko.menu();
            this.vypisAktual();
            this.zmenaStavu(volba);

            this.farba = (this.aktualStav < 2) ? Color.GREEN : Color.RED;
        }
    }

    private void zmenaStavu(String zadanyStav) {
        switch (this.aktualStav) {
            case 0:
                if (zadanyStav.equals(POVOLENE_STAVY[1])) {
                    this.aktualStav = 1;
                    return;
                } else break;

            case 1:
                if (zadanyStav.equals(POVOLENE_STAVY[2])) {
                    this.aktualStav = 2;
                    return;
                } else if (zadanyStav.equals(POVOLENE_STAVY[3])) {
                    this.aktualStav = 0;
                    return;
                } else break;

            case 2:
                if (zadanyStav.equals(POVOLENE_STAVY[1])) {
                    this.aktualStav = 3;
                    return;
                } else if (zadanyStav.equals(POVOLENE_STAVY[0])) {
                    this.aktualStav = 1;
                    return;
                } else break;

            case 3:
                if (zadanyStav.equals(POVOLENE_STAVY[3])) {
                    this.aktualStav = 2;
                    return;
                } else break;
            default:
                throw new IllegalArgumentException(String.format("Aktualny stav {%d} NEEXISTUJE", this.aktualStav));
        }
        throw new IllegalArgumentException(String.format("Ako nasledujuci zadany stav {%s} NEEXISTUJE", zadanyStav));
    }

    private void vypisAktual() {
        Rozdelenie.vypis(30);
        System.out.println("Farba ba semafor: " + ((this.farba==Color.RED) ? "červená" : "zelená"));
        System.out.println("Aktualny stav: " + Parkovisko.POVOLENE_STAVY[aktualStav]);
    }

    private static String menu() {
        Rozdelenie.vypis(50);
        System.out.println("Pre ukoncenie zadaj " + UKONCENIE);
        System.out.print("Zadaj vstup vo forme jednotlivych moznosti: | ");
        for (String stav : POVOLENE_STAVY) {
            System.out.printf("%s | ", stav);
        } System.out.println();
        System.out.print("Tvoja volba: ");

        return new Scanner(System.in).nextLine();
    }
}

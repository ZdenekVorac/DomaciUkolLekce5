import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        PlantList list = new PlantList();
        try {
            list.loadPlantList(Data.getPATH() + Data.getFilenameCorrect());
            list.getPlantList().sort(Comparator.comparing(Plant::getName));
            System.out.println(list);
            list.getPlantList().sort(Comparator.comparing(Plant::getWatering));
            System.out.println(list);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }
        try {
            list.addPlant(new Plant("Bazalka v kuchyni", "", LocalDate.parse("2021-09-04"),
                    LocalDate.parse("2021-09-04"), 3));
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            list.removePlant(2);
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            list.savePlantList(Data.getPATH() + Data.getFilenameOut());
        } catch (IOException e) {
            System.err.println("Chyba v souboru:" + Data.getPATH() + Data.getFilenameOut() + " " + e.getMessage());
        }
        try {
            list.loadPlantList(Data.getPATH() + Data.getFilenameWrongFrequency());
        } catch (PlantException e) {
            System.err.println("Chyba v souboru:" + Data.getPATH() + Data.getFilenameWrongFrequency() + " " + e.getLocalizedMessage());
        }
        try {
            list.loadPlantList(Data.getPATH() + Data.getFilenameWrongDate());
        } catch (PlantException e) {
            System.err.println("Chyba v souboru:" + Data.getPATH() + Data.getFilenameWrongDate() + " " + e.getLocalizedMessage());
        }

    }

}
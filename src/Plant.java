import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private Integer frequencyOfWatering;

    //region constructors

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, Integer frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
          if(watering.isBefore(planted)) throw new PlantException("Datum posledního zalití nesmí být starší než datum zasazení!");
        this.watering = watering;
          if (frequencyOfWatering < 1) throw new PlantException("Frekvence zalévání nesmí být menší než 1!");
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name, LocalDate planted, Integer frequencyOfWatering) throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name, LocalDate planted) throws PlantException {
        this(name, "", planted, LocalDate.now(), 7);
    }

    //endregion

    //region getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if(watering.isBefore(planted)) throw new PlantException("Datum posledního zalití nesmí být starší než datum zasazení!");
        this.watering = watering;
    }

    public Integer getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(Integer frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering < 1) throw new PlantException("Frekvence zalévání nesmí být menší než 1!");
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //endregion

    public String getWatertingInfo()
    {
        String info = name + " " + watering.toString() + " "
                + getWatering().plusDays(frequencyOfWatering).toString();
        return info;
    }


    public static Plant parsePlant(String data, int lineNumber) throws PlantException {
        String[] parsedData = data.split("\t");
        if (parsedData.length < 5) throw new PlantException("Špatný počet položek na řádku č:" + lineNumber + " " + data);
        if (parsedData[0].isEmpty()) throw new PlantException("Prázdné jméno květiny na řádku č:" + lineNumber + " " + data);
        LocalDate plantedData;
        try {
            plantedData = LocalDate.parse(parsedData[4]);
        } catch (Exception e) {
            throw new PlantException("Špatný formát datumu zasazení na řádku č:" + lineNumber + " " + parsedData[4]);
        }
        LocalDate wateredData;
        try {
            wateredData = LocalDate.parse(parsedData[3]);
        } catch (Exception e) {
            throw new PlantException("Špatný formát datumu posledního zalití na řádku č:" + lineNumber + " " + parsedData[3]);
        }
        int frequencyOfWateringData;
        try {
            frequencyOfWateringData=Integer.parseInt(parsedData[2]);
        } catch (Exception e)
        {
            throw new PlantException("Špatný formát frekvence zalévání na řádku č:" + lineNumber + " " + parsedData[2]);
        }
        return new Plant(parsedData[0], parsedData[1], plantedData, wateredData, frequencyOfWateringData);
    }

    @Override
    public String toString() {
        return name + "\t" + notes + "\t" + frequencyOfWatering + "\t" + watering + "\t" + planted;
    }
}

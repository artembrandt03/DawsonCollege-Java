package maxiso.datalayer.merch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * FileImporter is responsible for loading merchandise data from specified files.
 * It implements the IMerchImporter interface, providing methods to load different
 * types of merchandise such as figurines, plushies, and electronics.
 */
public class FileImporter implements IMerchImporter {
    private String figurinesFile;
    private String plushiesFile;
    private String electronicsFile;

    /**
     * Constructor
     *
     * @param figurinesFile   Path to the file containing figurine data
     * @param plushiesFile    Path to the file containing plushie data
     * @param electronicsFile Path to the file containing electronics data
     */
    public FileImporter(String figurinesFile, String plushiesFile, String electronicsFile) {
        this.figurinesFile = figurinesFile;
        this.plushiesFile = plushiesFile;
        this.electronicsFile = electronicsFile;
    }

    //Temporary constructor for me to test
    public FileImporter(String figurinesFile) {
        this.figurinesFile = figurinesFile;
    }

    /**
     * Loads figurine data from the specified file
     *
     * @return A list of Merch objects representing figurines
     * @throws IllegalArgumentException if an I/O error occurs while reading the file
     */
    @Override
    public List<Merch> loadFigurines() {
        Path p = Paths.get(figurinesFile);
        List<Merch> figurines = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(p);
            for (String line : allLines) {
                String[] pieces = line.split(",");
                Figurine figurine = new Figurine(pieces[0], pieces[1], pieces[2], Double.parseDouble(pieces[3]), pieces[4], Integer.parseInt(pieces[5]), Boolean.parseBoolean(pieces[6]));
                figurines.add(figurine);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return figurines;
    }

    /**
     * Loads plushie data from the specified file
     *
     * @return A list of Merch objects representing plushies
     * @throws IllegalArgumentException if an I/O error occurs while reading the file
     */
    @Override
    public List<Merch> loadPlushies() {
        Path p = Paths.get(plushiesFile);
        List<Merch> plushies = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(p);
            for (String line : allLines) {
                String[] pieces = line.split(",");
                Plushie plushie = new Plushie(pieces[0], pieces[1], Double.parseDouble(pieces[2]), pieces[3], pieces[4], Integer.parseInt(pieces[5]), pieces[6], pieces[7], Boolean.parseBoolean(pieces[8]));
                plushies.add(plushie);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return plushies;
    }

    @Override
    public List<Merch> loadElectronics() {
        Path p = Paths.get(electronicsFile);
        List<Merch> electronics = new ArrayList<>();

        try {
            List<String> allLines = Files.readAllLines(p);
            for (String line : allLines) {
                String[] pieces = line.split(",");
                Electronic electronic = new Electronic(electronicProduct.valueOf(pieces[0].toUpperCase()), pieces[1], Integer.parseInt(pieces[2]), Double.parseDouble(pieces[3]), Boolean.parseBoolean(pieces[4]), Boolean.parseBoolean(pieces[5]), pieces[6], pieces[7], Double.parseDouble(pieces[8]), pieces[9]);
                electronics.add(electronic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return electronics;
    }

    /**
     * Loads all merchandise data by combining figurines, plushies & electronics
     *
     * @return A list of all Merch objects
     */
    @Override
    public List<Merch> loadAllMerch() {
        List<Merch> allMerch = new ArrayList<>();

        allMerch.addAll(loadFigurines());
        allMerch.addAll(loadPlushies());
        allMerch.addAll(loadElectronics());

        return allMerch;
    }

    public String getFigurineFile() {
        return this.figurinesFile;
    }

    public String getPlushieFile() {
        return this.plushiesFile;
    }

    public String getElectronicFile() {
        return this.electronicsFile;
    }
}

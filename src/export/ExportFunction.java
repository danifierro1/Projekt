package export;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportFunction {

    /**
     * Exportiert die Daten der Tabelle in eine CSV-Datei.
     *
     * @param tableData    Die Daten der Tabelle als Liste von Objekten.
     * @param columnNames  Die Spaltennamen der Tabelle.
     * @param parentFrame  Das übergeordnete Fenster für Dialoge.
     */
    public static void exportToCSV(List<Object[]> tableData, String[] columnNames, JFrame parentFrame) {
        // Überprüfen, ob es Daten gibt
        if (tableData == null || tableData.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "Keine Daten zum Exportieren verfügbar.", "Fehler", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Dateiauswahl-Dialog anzeigen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportieren als CSV");
        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile() + ".csv")) {
                // Schreibe die Spaltennamen
                for (String column : columnNames) {
                    writer.append(column).append(";");
                }
                writer.append("\n");

                // Schreibe die Daten
                for (Object[] row : tableData) {
                    for (Object cell : row) {
                        writer.append(cell != null ? cell.toString() : "").append(";");
                    }
                    writer.append("\n");
                }

                JOptionPane.showMessageDialog(parentFrame, "Daten erfolgreich exportiert!", "Exportieren", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parentFrame, "Fehler beim Exportieren der Datei: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame, "Export abgebrochen.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

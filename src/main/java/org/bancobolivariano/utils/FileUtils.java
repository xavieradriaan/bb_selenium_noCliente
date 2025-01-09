//src/test/java/org/bancobolivariano/tests/ExtentReportTest.java
//src/test/java/org/bancobolivariano/tests/ExtentReportTest.java
package org.bancobolivariano.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
    public static Map<Integer, Map<String, String>> readFileXls(String nombreArchivo, int numeroHoja) {
        Map<Integer, Map<String, String>> datos = new HashMap<>();
        try {
            FileInputStream archivo = new FileInputStream(new File(nombreArchivo));
            Workbook libroExcel = new XSSFWorkbook(archivo);
            Sheet hoja = libroExcel.getSheetAt(numeroHoja);
            Row filaTitulos = hoja.getRow(0);
            Map<Integer, String> columnaTitulos = new HashMap<>();
            for (Cell celdaTitulo : filaTitulos) {
                int indiceColumna = celdaTitulo.getColumnIndex();
                String tituloColumna = celdaTitulo.toString();
                columnaTitulos.put(indiceColumna, tituloColumna);
            }
            for (Row fila : hoja) {
                if (fila.getRowNum() == 0) {
                    continue;
                }
                Map<String, String> filaDatos = new HashMap<>();
                for (Cell celda : fila) {
                    int indiceColumna = celda.getColumnIndex();
                    String tituloColumna = columnaTitulos.get(indiceColumna);
                    String valorCelda = celda.toString();
                    filaDatos.put(tituloColumna, valorCelda);
                }
                datos.put(fila.getRowNum(), filaDatos);
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public static Map<String, String> getRowFromStore( Map<Integer, Map<String, String>> datos, int id) {
        Map<String, String> filaDatos = datos.get(id);
        if (filaDatos != null) {
            return filaDatos;
        } else {
            return null;
        }
    }
}
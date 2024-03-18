package Reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modelo.Conexion;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    public static void reporte() {

        // Crear un nuevo libro de Excel
        Workbook book = new XSSFWorkbook();
        // Crear una hoja de trabajo en el libro
        Sheet sheet = book.createSheet("Productos");

        try {
            // Leer la imagen del logo desde el archivo
            InputStream is = new FileInputStream("src/Imagenes/Logo.png");
            byte[] bytes = IOUtils.toByteArray(is);
            // Agregar la imagen al libro de Excel
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();

            // Obtener el asistente de creación y el área de dibujo
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            // Crear un ancla para la imagen y establecer su posición en la hoja
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 1);

            // Establecer el estilo para el título
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);

            // Crear la fila del título y establecer el estilo del título
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Productos");

            // Fusionar las celdas para el título
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

            // Definir las cabeceras de las columnas
            String[] cabecera = new String[]{"Código", "Nombre", "Precio", "Existencia"};

            // Establecer el estilo para las cabeceras
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            // Crear la fila de encabezados y establecer el estilo de las cabeceras
            Row filaEncabezados = sheet.createRow(4);
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEncabezado = filaEncabezados.createCell(i);
                celdaEncabezado.setCellStyle(headerStyle);
                celdaEncabezado.setCellValue(cabecera[i]);
            }

            // Establecer la conexión a la base de datos
            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.getConnection();

            // Inicializar el número de fila para los datos
            int numFilaDatos = 5;

            // Establecer el estilo para los datos
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            // Ejecutar la consulta SQL para obtener los productos
            ps = conn.prepareStatement("SELECT codigo, nombre, precio, stock FROM productos");
            rs = ps.executeQuery();

            // Obtener el número de columnas en el resultado de la consulta
            int numCol = rs.getMetaData().getColumnCount();

            // Iterar sobre los resultados y crear filas y celdas para los datos
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
                for (int a = 0; a < numCol; a++) {
                    Cell celdaDatos = filaDatos.createCell(a);
                    celdaDatos.setCellStyle(datosEstilo);
                    celdaDatos.setCellValue(rs.getString(a + 1));
                }
                numFilaDatos++;
            }

            // Ajustar el ancho de las columnas automáticamente
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            // Establecer el zoom de la hoja
            sheet.setZoom(150);

            // Definir el nombre del archivo y la ubicación de guardado
            String fileName = "productos";
            String home = System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + fileName + ".xlsx");

            // Escribir el libro en un archivo y abrirlo
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "Reporte Generado");

        } catch (FileNotFoundException ex) {
            // Manejar la excepción si no se encuentra el archivo
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            // Manejar la excepción si hay errores de E/S o SQL
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

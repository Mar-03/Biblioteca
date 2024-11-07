/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeneradorPDF;




import Controlador.LibroControlador;
import Controlador.UsuarioControlador;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import modelos.Usuario;
import modelos.libro;
import modelos.prestamo;

public class PDFGenerador {

    public static void generarFactura(prestamo prestamo) {
        Document document = new Document();
        try {
            String filePath = "C:\\Users\\nebra\\Downloads\\Factura_Prestamo_" + prestamo.getIdPrestamo() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            UsuarioControlador usuarioController = new UsuarioControlador();
            LibroControlador libroController = new LibroControlador();

            Usuario usuario = usuarioController.obtenerUsuarioPorId(prestamo.getIdUsuario());
            libro libro = libroController.obtenerLibroPorId(prestamo.getIdLibro());

            document.add(new Paragraph("Factura de Préstamo"));
            document.add(new Paragraph("ID del Préstamo: " + prestamo.getIdPrestamo()));
            document.add(new Paragraph("Usuario: " + usuario.getNombre() + " " + usuario.getApellidos()));
            document.add(new Paragraph("Libro: " + libro.getNombre_libro()));
            document.add(new Paragraph("Fecha de Préstamo: " + prestamo.getFechaPrestamo()));
            document.add(new Paragraph("Fecha de Devolución: " + prestamo.getFechaDevolucion()));
            document.add(new Paragraph("Monto Pagado: $" + prestamo.getMontoPagado()));

            document.close();
            System.out.println("PDF generado correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró la ruta especificada o no se tiene permiso para escribir en ella.");
            e.printStackTrace();
        } catch (DocumentException e) {
            System.out.println("Error al generar el documento PDF.");
            e.printStackTrace();
        }
    }
}




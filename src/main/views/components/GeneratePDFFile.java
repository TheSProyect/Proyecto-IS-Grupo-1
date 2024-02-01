package main.views.components;


import lib.*;
import com.itextpdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class GeneratePDFFile {
    String nombre, apellido, fecha;
    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;

    public GeneratePDFFile(String nombre1, String nombre2){
        this.nombre=nombre1;
        this.apellido=nombre2;

        documento=new Document();
        titulo = new Paragraph("plantila personalizada");
    }
    public void crearPlantilla(){
        try{
            archivo=new FileOutputStream(nombre + ".pdf");
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            titulo.setAlignment(1);

            documento.add(titulo);
            documento.add(new Paragraph("Nombre: "+nombre));
            documento.add(new Paragraph("Apellido: "+apellido));
            documento.add(Chunk.NEWLINE);

            Paragraph texto= new Paragraph("yo solo espero que esta monda este funcionando");
            texto.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(texto);
            documento.close();

        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch(DocumentException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        GeneratePDFFile createPDF= new GeneratePDFFile("Ivan", "Jimena");
        createPDF.crearPlantilla();
    }
    
}

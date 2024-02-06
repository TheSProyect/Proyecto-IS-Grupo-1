package main.models;


//import lib.*;
import main.models.Course;
import main.utils.Directory;
import main.utils.UserData;
import main.models.Certificate;
import main.controllers.RequestCertificateController;
import com.itextpdf.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.MalformedURLException;
import java.net.URL;

//curso tiene teacher y nombre

import java.io.*;
import java.security.Signature;

public class GeneratePDFFile {
    Document documento;
    FileOutputStream archivo;
    Paragraph titulo, text, course, name, teacher;
    String nombre, curso, profesor;
    Font fuenteTitulo= new Font();
    Font fuenteSmall = new Font();
    Font fuenteName = new Font();
    Image image ;
    FileInputStream imageStream;
    RequestCertificateController RCController= new RequestCertificateController();
    //Certificate currenCertificate;
    //Course currentCourse;

    public GeneratePDFFile(){
        
        fuenteTitulo.setSize(36);
        documento=new Document();
        titulo = new Paragraph("Certificado de participación",fuenteTitulo);
        this.crearPlantilla();
        
        
    }

    public void crearPlantilla(){
        Directory currentDirectory = Directory.instance();
        String directorio = currentDirectory.getDirectoryTeachers();
        //File file=new File(directorio + File.separator+RCController.getNameTeacherController()+File.separator+"Signature.png");
        //File file=new File(directorio+File.separator+"Valeria Ciccolella"+File.separator +"Signature.png");
        try {
            image = Image.getInstance(directorio+File.separator+"Valeria Ciccolella"+File.separator +"Signature.png");

        } catch (Exception e) {
            // TODO: handle exception
        }

        try{
            String nombre=RCController.getNameStudentController();
            String directory = currentDirectory.getDirectoryStudents() +File.separator+ nombre + ".pdf";
            //archivo=new FileOutputStream(nombre + ".pdf");
            PdfWriter.getInstance(documento, new FileOutputStream(directory));
            //PdfWriter.getInstance(documento, archivo);
            //fuenteTitulo.setSize(28);
            fuenteSmall.setSize(16);
            fuenteName.setSize(48);
            documento.open();
            

            titulo.setAlignment(1);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            

            text= new Paragraph("Se otorga el presente a:", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);

            name= new Paragraph(nombre,fuenteName);
            name.setAlignment(1);
            documento.add(name);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            //titulo = new Paragraph("Certificado de participacion",fuenteTitulo);
            text= new Paragraph("Por su participación en el examen para aspirar a", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
            
            //curso=RCController.getCourseController();
            course= new Paragraph("Java SE - Java Associate Programmer", fuenteSmall);
            //course= new Paragraph(curso, fuenteSmall);
            course.setAlignment(1);
            documento.add(course);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);

            //
            image.setAlignment(Element.ALIGN_CENTER);
            documento.add(image);
            text= new Paragraph("Examen realizado por:", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
            
            //profesor=RCController.getNameTeacherController();
            //teacher= new Paragraph("Profesor/a: "+ profesor, fuenteSmall);
            teacher= new Paragraph("Profesor/a: Paula Herrero", fuenteSmall);
            teacher.setAlignment(1);
            documento.add(teacher);
            
            

            documento.close();

        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch(DocumentException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        GeneratePDFFile createPDF= new GeneratePDFFile();
        createPDF.crearPlantilla();
        
    }
    
}

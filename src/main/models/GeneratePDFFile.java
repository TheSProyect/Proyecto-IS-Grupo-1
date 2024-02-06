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
import java.util.List;
import com.itextpdf.text.pdf.PdfPTable;

//curso tiene teacher y nombre

import java.io.*;
import java.security.Signature;
import java.util.ArrayList;

public class GeneratePDFFile {
    Document documento;
    FileOutputStream archivo;
    Paragraph titulo, text, course, name, teacher;
    String nombre, curso, profesor, scoreAnswers, numQuestions;
    Font fuenteTitulo= new Font();
    Font fuenteSmall = new Font();
    Font fuenteName = new Font();
    Image imageSignature, imageScore ;
    FileInputStream imageStream;
    List<String> informationToPDF = new ArrayList<String>();
    RequestCertificateController RCController= new RequestCertificateController();
    //Certificate currenCertificate;
    //Course currentCourse;

    public GeneratePDFFile(List<String> informationList){
        fuenteTitulo.setSize(36);
        documento=new Document();
        titulo = new Paragraph("Certificado de participación",fuenteTitulo);
        this.informationToPDF=informationList;
        this.crearPlantilla();
    }
    //quitar esto
    public GeneratePDFFile(){
        fuenteTitulo.setSize(36);
        documento=new Document();
        titulo = new Paragraph("Certificado de participación",fuenteTitulo);
        this.crearPlantilla();
    }

    public void crearPlantilla(){
        //List<String> informationToPDF = new ArrayList<String>();
        Directory currentDirectory = Directory.instance();
        String directorio = currentDirectory.getDirectoryTeachers();
        //int contador=0;
        name=new Paragraph(informationToPDF.get(0), fuenteName);
        scoreAnswers=informationToPDF.get(1);
        numQuestions=informationToPDF.get(2);
        //score=new Paragraph(scoreAnswers+"/"+numQuestions, fuenteName);
        course=new Paragraph(informationToPDF.get(3),fuenteSmall);
        String teacherName=informationToPDF.get(4);
        teacher= new Paragraph(teacherName, fuenteSmall);

        //teacher= new Paragraph("Profesor/a: Paula Herrero", fuenteSmall);
        try {
            imageSignature = Image.getInstance(directorio+File.separator+teacherName+File.separator +"Signature.png");
            imageScore=Image.getInstance("src\\assets\\BlueFolder_Icon.png");

        } catch (Exception e) {
            // TODO: handle exception
        }

        try{
            String nombre=RCController.getNameStudentController();
            String directory = currentDirectory.getDirectoryStudents() +File.separator+ nombre + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(directory));
            fuenteSmall.setSize(16);
            fuenteName.setSize(48);
            documento.open();

            titulo.setAlignment(1);
            documento.add(titulo);
            documento.add(Chunk.NEWLINE);

            text= new Paragraph("Se otorga el presente a:", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
            documento.add(Chunk.NEWLINE);

            //name= new Paragraph(nombre,fuenteName);
            //name= new Paragraph("Usuario",fuenteName);
            name.setAlignment(1);
            documento.add(name);
            documento.add(Chunk.NEWLINE);
            //titulo = new Paragraph("Certificado de participacion",fuenteTitulo);
            text= new Paragraph("Por su participación en el examen para aspirar a", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
            
            //curso=RCController.getCourseController();
            //course= new Paragraph("Java SE - Java Associate Programmer", fuenteSmall);
            //course= new Paragraph(curso, fuenteSmall);
            course.setAlignment(1);
            documento.add(course);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            
            imageSignature.setAlignment(Element.ALIGN_CENTER);
            
            //table.setHeaderRows(1);
            //table.addCell(course);
            //imageScore.setAlignment(Element.ALIGN_CENTER);
            imageScore.scaleToFit(40, 40);
            documento.add(imageSignature);
            documento.add(Chunk.NEWLINE);

            Paragraph scoreParagraph = new Paragraph();
            //score= new Paragraph("1/5", fuenteName);
            scoreParagraph.add(new Chunk(imageScore,0,0,true));
            scoreParagraph.add(new Chunk(scoreAnswers+"/"+numQuestions, fuenteTitulo));
            scoreParagraph.setAlignment(Element.ALIGN_CENTER);
            documento.add(scoreParagraph);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            //documento.add(imageScore);
            text= new Paragraph("Examen realizado por:", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
            
            //profesor=RCController.getNameTeacherController();
            //teacher= new Paragraph("Profesor/a: "+ profesor, fuenteSmall);
            //teacher= new Paragraph("Profesor/a: Paula Herrero", fuenteSmall);
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
        //createPDF.crearPlantilla();
        
    }
    
}

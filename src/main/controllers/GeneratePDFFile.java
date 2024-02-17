package main.controllers;

import main.utils.Directory;
import main.utils.UserData;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class GeneratePDFFile {
    Document documento;
    FileOutputStream archivo;
    Paragraph titulo, text, course, name, teacher;
    String scoreAnswers, numQuestions;
    Font fuenteTitulo= new Font();
    Font fuenteSmall = new Font();
    Font fuenteName = new Font();
    Image imageSignature, imageScore;
    List<String> informationToPDF = new ArrayList<String>();
    RequestCertificateController RCController= new RequestCertificateController();

    public GeneratePDFFile(List<String> informationList){
        fuenteTitulo.setSize(36);
        documento=new Document();
        titulo = new Paragraph("Certificado de participación",fuenteTitulo);
        this.informationToPDF=informationList;
        this.crearPlantilla();
    }

    public void crearPlantilla(){
        Directory currentDirectory = Directory.instance();
        String directorio = currentDirectory.getDirectoryTeachers();
        name=new Paragraph(informationToPDF.get(0), fuenteName);
        scoreAnswers=informationToPDF.get(1);
        numQuestions=informationToPDF.get(2);
        course=new Paragraph(informationToPDF.get(3),fuenteSmall);
        String teacherName=informationToPDF.get(4);
        String usernameTeacher = informationToPDF.get(5);
        teacher= new Paragraph(teacherName, fuenteSmall);
        try {
            imageSignature = Image.getInstance(directorio + File.separator + usernameTeacher + File.separator + "Signature.png");
            imageScore=Image.getInstance("src\\assets\\BlueFolder_Icon.png");

        } catch (Exception e) {
            // TODO: handle exception
        }

        try{
            UserData currentUser = UserData.instance();
            String directory;
            if(currentUser.isAdmin()){
                directory = (currentDirectory.getDirectoryTeachers())+ File.separator + currentUser.getUsername() + File.separator +  currentUser.getUsername() + ".pdf";
                } else {
                    directory = (currentDirectory.getDirectoryStudents())+ File.separator + currentUser.getUsername() + File.separator +  currentUser.getUsername() + ".pdf";
            }   
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
            name.setAlignment(1);
            documento.add(name);
            documento.add(Chunk.NEWLINE);
            text= new Paragraph("Por su participación en el examen para aspirar a", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
            course.setAlignment(1);
            documento.add(course);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            imageSignature.setAlignment(Element.ALIGN_CENTER);
            imageScore.scaleToFit(40, 40);
            documento.add(imageSignature);
            documento.add(Chunk.NEWLINE);
            Paragraph scoreParagraph = new Paragraph();
            scoreParagraph.add(new Chunk(imageScore,0,0,true));
            scoreParagraph.add(new Chunk(scoreAnswers+"/"+numQuestions, fuenteTitulo));
            scoreParagraph.setAlignment(Element.ALIGN_CENTER);
            documento.add(scoreParagraph);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            text= new Paragraph("Examen realizado por:", fuenteSmall);
            text.setAlignment(1);
            documento.add(text);
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
        
    }
    
}

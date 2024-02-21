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
    String scoreAnswers, numQuestions, usernameTeacher,teacherName;
    Font fuenteTitulo= new Font();
    Font fuenteSmall = new Font();
    Font fuenteName = new Font();
    Image imageSignature, imageScore;
    List<String> informationToPDF = new ArrayList<String>();
    RequestCertificateController RCController= new RequestCertificateController();
    Directory currentDirectory = Directory.instance();

    public GeneratePDFFile(List<String> informationList){
        fuenteTitulo.setSize(36);
        fuenteSmall.setSize(16);
        fuenteName.setSize(48);
        documento=new Document();
        titulo = new Paragraph("Certificado de participación",fuenteTitulo);
        this.informationToPDF=informationList;
        this.readInformation();
        this.createImage();
        this.crearPlantilla();
    }
    public void readInformation(){
        name=new Paragraph(informationToPDF.get(0), fuenteName);
        scoreAnswers=informationToPDF.get(1);
        numQuestions=informationToPDF.get(2);
        course=new Paragraph(informationToPDF.get(3),fuenteSmall);
        teacherName=informationToPDF.get(4);
        usernameTeacher = informationToPDF.get(5);
        teacher= new Paragraph(teacherName, fuenteSmall);
    }
    public void createImage(){
        String directorio = currentDirectory.getDirectoryTeachers();
        try {
            imageSignature = Image.getInstance(directorio + File.separator + usernameTeacher + File.separator + "Signature.png");
            imageScore=Image.getInstance("src\\assets\\BlueFolder_Icon.png");
            imageSignature.setAlignment(Element.ALIGN_CENTER);
            imageScore.scaleToFit(40, 40);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String searchDirectoryStudent(){
        UserData currentUser = UserData.instance();
            String directory;
            if(currentUser.isAdmin()){
                directory = (currentDirectory.getDirectoryTeachers())+ File.separator + currentUser.getUsername() + File.separator +  currentUser.getUsername() + ".pdf";
                } else {
                directory = (currentDirectory.getDirectoryStudents())+ File.separator + currentUser.getUsername() + File.separator +  currentUser.getUsername() + ".pdf";
            } 
            return directory;
    }
    public void crearPlantilla(){ 
        try{  
            String directory=searchDirectoryStudent();
            PdfWriter.getInstance(documento, new FileOutputStream(directory));
            documento.open();
            addDocument(documento, titulo,1);
            text= new Paragraph("Se otorga el presente a:", fuenteSmall);
            addDocument(documento, text,1);
            addDocument(documento, name,1);
            text= new Paragraph("Por su participación en el examen para aspirar a", fuenteSmall);
            addDocument(documento, text,0);
            addDocument(documento, course,2); 
            documento.add(imageSignature);
            documento.add(Chunk.NEWLINE);
            Paragraph scoreParagraph = new Paragraph();
            scoreParagraph.add(new Chunk(imageScore,0,0,true));
            scoreParagraph.add(new Chunk(scoreAnswers+"/"+numQuestions, fuenteTitulo));
            addDocument(documento, scoreParagraph,2);
            text= new Paragraph("Examen realizado por:", fuenteSmall);
            addDocument(documento, text,0);
            addDocument(documento, teacher,0);
            documento.close();
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch(DocumentException e){
            System.err.println(e.getMessage());
        }
    }
    public void addDocument(Document document, Paragraph text, int numChunk){
        try {
            text.setAlignment(1);
            document.add(text);
            for(int i=0; i<numChunk; i++){
                documento.add(Chunk.NEWLINE);
            }   
        } catch (DocumentException e) {
            System.err.println(e.getMessage());
        }
    }
}

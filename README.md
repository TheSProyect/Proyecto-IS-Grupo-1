# Proyecto-IS-Grupo-1
## Integrantes
* Valeria Ciccolella
* Jesús Cova
* Samuel Flores
* Lisangely Goncalves
* Sofía Marcano
* Gabriel Valero
## Modelado de Dominio : 
### Diagrama de Clases
![Modelado de Dominio : Diagrama de Clases](out/docs/scenariosView/DomainClassDiagram/Project-ClassDiagram.png)
### Diagrama de Estados
![Modelado de Dominio : Diagrama de Estados](out/docs/scenariosView/StateDiagram/Project-StateDiagram.png)
## Modelado de Casos de Uso : 
### Diagrama de Casos de Uso
![Modelado de Casos de Uso : Diagrama de Casos de Uso](out/docs/scenariosView/UseCaseDiagram/Project-UseCaseDiagram.png)
### Diagrama de Estados de los Casos de Uso
![Modelado de Casos de Uso : Diagrama de Estado de los Casos de Uso](out/docs/scenariosView/UseCaseStateDiagram/Project-UseCaseStateDiagram.png)
### Especificaciones de los Casos de Uso
- [CrearExamen](/docs/scenariosView/useCases/CUCrearExamen.pdf)
- [CrearPregunta](/docs/scenariosView/useCases/CUCrearPregunta.pdf)
- [PresentarExamen](/docs/scenariosView/useCases/CUPresentarExamen.pdf)
- [SolicitarCertificado](/docs/scenariosView/useCases/CUSolicitarCertificado.pdf)
## Prototipos de pantallas
### Flujo de Usuario
![Prototipos de Pantallas : Flujo Usuario](out/docs/prototypeModel/FlujoUser.png)
### Flujo de Administrador
![Prototipos de Pantallas : Flujo Usuario](out/docs/prototypeModel/FlujoAdmin.png)      
### Prototipado Interactivo
- [Prototipado en Figma](https://www.figma.com/proto/t3JwDM1Ml5MX22OzwAK5At/Modelo-de-Prototipos-IS---Grupo-1?page-id=0%3A1&type=design&node-id=1-2&viewport=-156%2C482%2C0.15&t=mA4RJ4fKFYQQwOEy-1&scaling=min-zoom&starting-point-node-id=1%3A2&show-proto-sidebar=1&mode=design)
- [Pantallas](/out/docs/PROTOTYPES.md)

## Modelado de Análisis
### Diagrama de clases de analisis
![Modelado de Análisis : Diagrama de clases de analisis](out/docs/logicalView/analysisView/Analysis-ClassDiagram/Analysis-ClassDiagram.png)

### Diagrama de clases de análisis y de colaboración de casos de uso prioritarios
#### UC1-Create Exam
![Modelado de Análisis : Diagrama de clases de analisis](out/docs/logicalView/analysisView/UC-CreateExam-analysisClassDiagram/UC-CreateExam-analysisClassDiagram.png)
![Modelado de Análisis : Diagrama de colaboracion](out/docs/logicalView/analysisView/UC-CreateExam-comunicationDiagram/UC-CreateExam-comunicationDiagram.png)

#### UC2-Present Exam
![Modelado de Análisis : Diagrama de clases de analisis](out/docs/logicalView/analysisView/UC-presentExam-analysisClassDiagram/Clasesdeanalisis.png)
![Modelado de Análisis : Diagrama de colaboracion](out/docs/logicalView/analysisView/UC-presentExam-comunicationDiagram/Clasesdeanalisis.png)

#### UC3-Request Certificate
![Modelado de Análisis : Diagrama de clases de analisis](out/docs/logicalView/analysisView/UC-requestCetificate-analysisClassDiagram/UC-requestCertificate-analysisClassDiagram.png)
![Modelado de Análisis : Diagrama de colaboracion](out/docs/logicalView/analysisView/UC-requestCertificate-analysisCommunicationDiagram/UC-requestCertificate-analysisCommunicationDiagram.png)

### Diagrama de paquetes de análisis
![Modelado de Análisis : Diagrama de paquetes](out/docs/logicalView/analysisView/Analysis-PackageDiagram/Analysis-PackageDiagram.png)

## Modelo de Diseño

### Diagrama de clases
![Modelado de Diseño : Diagrama de clases](out/docs/logicalView/desingView/classDesign/Design-CreateExamClassDiagram/Design-ClassDiagram.png)

### Diagrama de secuencia
![Modelado de Diseño : Diagrama de secuencia-CreateExam](out/docs/logicalView/desingView/classDesign/UC-CreateExam-SequenceDiagram/UC-CreateExam-SequenceDiagram.png)
![Modelado de Diseño : Diagrama de secuencia-CreateQuestion](out/docs/logicalView/desingView/classDesign/UC-CreateQuestion-SequenceDiagram¡/UC-CreateQuestion-SequenceDiagram¡.png)

### Diagrama de Traza
![Modelado de Diseño : Diagrama de traza](out/docs/logicalView/desingView/packageDesign/Design-TraceDiagram/Design-Analysis-TraceDiagram.png)

## Modelo de Despliegue
![Modelado de Despliegue](out/docs/logicalView/desingView/architectureDesign/DeployDiagram/deployDiagram.png)

## Modelo de Implementacion
#### Utilizamos la libreria externa iTextPDF version 5.5.12, se encuentra en la carpeta lib dentro de src. La llama  GeneratePDFFile que se encuentra en la carpeta de controladores

## Modelo de pruebas
* PresentExamControllerTest
  - testReadIndformation()
* RequestCertificateControllerTest
  - readStudentDataTest()
  - showCertificatesTest()
* RegisterUserControllerTest
  - writeFileTest()
  - searchUserTest()
  - RegisterNewUserTest()
* ExamTest
  - testExam()
  - testExamIsEmpty()

## Historias de Usuario
### Cambios de requisitos
#### Requisito: Añadir imagen a una pregunta
![Insertar imagen](out/docs/Historias_de_Usuario/Historia_de_Usuario_Insertar_Imagen.png)

#### Requisito: Crear preguntas de seleccion multiple
![Seleccion multiple](out/docs/Historias_de_Usuario/Historia_de_Usuario_Selección_Multiple.png)

## Iniciar el programa
#### El programa se corre desde la clase Main
#### Para hacer Login 
* Como usuario corriente:
  - user = Usuario
  - password = Contrasenia
* Como administrador:
  - user = Profesor
  - password = contrasenia

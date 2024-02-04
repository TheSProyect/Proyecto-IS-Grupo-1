package main.views.pages;

import main.views.components.ExamMenu;
import main.views.components.QuestionPanel;
import main.views.components.ResultsBlock;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import main.controllers.PresentExamController;

public class ExamEndedView extends ExamTemplateView {
    PresentExamController presentController;

    ExamEndedView(List<QuestionPanel> questions, PresentExamController presentController) {
        this.questions = questions;
        this.presentController = presentController;
        index = 0;

        buildFrame("ExamEndedView");
        paintBorders();
        paintContentPanel();
        inicializeQuestions();

        addActionListener();
    }
    
    protected void paintMenuPanel() {
        ResultsBlock results = new ResultsBlock();
        menuPanel = new ExamMenu(results, questions.size());
        menuPanel.setCurrentQuestion(index);

        results.paintResults(showScore(), questions.size());
        
        contentPanel.add(menuPanel, menuPanelConstraints());
    }

    protected void inicializeQuestions() {
        List<List<String>> explication = new ArrayList<List<String>>(); 
        // definitivamente borrar
        explication.add(new ArrayList<String>());
        explication.get(0).add("Para entender la historia de Five Nights at Freddy's hay que olvidarse que estos son juegos y quiero que tomen realmente a esta saga como lo que es. ¿Terror? Sí, pero sobre todo, ciencia ficción.");
        explication.get(0).add("Antes de comenzar, quiero decir que esta cronología la realizamos entre 3 youtubers conocidos de Five Nights at Freddy's y yo. Por lo tanto, agradecería que si les gusta el contenido de este juego vayan a visitar sus canales.");
        explication.get(0).add("Ahora sí, empecemos. <b>¿Qué pasaría si dos amigos se abren una pizzería?</b> Esa es la primera pregunta que hay que plantearnos. Lo normal sería que todo vaya medianamente bien con algún tipo de problemas, pero nada saldría más allá de eso.");
        explication.get(0).add("La pregunta cambia completamente si nos preguntamos ¿Qué pasaría si Henry y William abren una pizzería? ¿Quiénes son estos personajes? En un principio, grandes amigos.");
        explication.add(new ArrayList<String>());
        explication.get(1).add("Henry, por un lado, era un ferviente y talentoso mecánico que cuidaba a su única hija, Charlie. No sabemos nada de su esposa, ni siquiera si tiene a alguien más en su familia.");
        explication.get(1).add("Y por el otro lado, <b>William Afton. </b>La familia de Afton estaba compuesta por 5 miembros. William, una persona con mucho dinero y con buena capacidad para la mecánica. Su hija menor, Elizabeth. Este pendejo que no sabemos el nombre, pero llora todo el tiempo, así que vamos a ponerle Crying Child.<b> Michael Afton</b>, su hijo mayor y su esposa, de quien no se sabe nada.");
        explication.get(1).add("Estos dos personajes unieron sus capacidades de mecánicos y con el buen capital que tenía William ahorrado, entre los dos abrieron un restaurante. Así fue como entre los años 1980 a 1982, supuestamente, Fredbear Family Dinner abrió sus puertas.");
        explication.get(1).add("La principal atracción de este lugar eran los animatrónicos. ¿Qué son? Bueno, básicamente eran robots que podrían ser controlados tanto por ellos mismos como por personas o por almas. Estos animatrónicos habían sido desarrollados por los dueños del restaurante, pero Henry destacó un poco más debido a que hizo un complejo sistema de resortes que permitía a la persona usar estos trajes.");
        explication.add(new ArrayList<String>());
        explication.get(2).add("Solamente que tenía que ser extremadamente cuidadosa, ya que de lo contrario el mecanismo del mismo se activaría y la persona que esté dentro seguramente quedaría lastimada.");
        explication.get(2).add("Estos trajes híbridos darían a luz en un principio a su principal éxito, Fredbear y Spring Bonnie. Dos animatrónicos que durante esos años 80s habían hecho furor y tan bien les estaba yendo a estos dos amigos que la competencia empezó a llegar.");
        explication.get(2).add("Y es por eso que a unos pocos meses de la salida de Fredbear Family Dinner llegaría su competencia, Fazbear Entertainment, pero que esta no sería relevante hasta en un futuro.");
        explication.get(2).add(" En paralelo a estos hechos, empezaban a haber roces entre la dupla principal, ya que William no solamente había abierto el restaurante para comer, sino que detrás de sus intenciones había algo mucho más oscuro, matar gente. Es por eso que en una fecha que desconocemos, William creó un nuevo local, Circus Baby Pizza World, y es en este donde presentaría sus nuevos animatrónicos, los Funtime. Estos animatrónicos estarían hechos bajo la empresa Afton Robotics, que como podrán imaginar, esta empresa era de William. Aunque los Funtime no eran animatrónicos normales, si bien tenían características muy innovadoras con respecto a los primeros trajes híbridos, estos Funtime estarían creados específicamente para matar. Una inteligencia artificial muy avanzada, poder abrir diferentes partes de su cuerpo y la posibilidad de hablar. Claramente no tenían una buena intención, pero a William se le volvería todo en contra cuando el mismo día de la inauguración de su local, a pesar de sus advertencias a Elizabeth, esta entró igual al cuarto donde estaban los animatrónicos para ver si estaba su robot favorito, Baby. Y luego de que este animatrónico le ofreciera un helado para hacer que se acercara a ella, la mata. O bueno, no tanto. Mientras a todo esto, recordemos que William pensaba que ya todos los niños estaban capturados dentro de los animatrónicos, debido a que la apertura de su local había sido completamente exitosa. Entonces alerta a toda la gente de una fuga de gas para que así tengan que evacuar el local y él poder ir a ver su recompensa. Cuando William va a ver si sus animatrónicos habían capturado niños, sí, así es, habían capturado niños. Que eso lo sabemos debido a que en los planos de los animatrónicos aparecen cuerpo dentro de estos robots. Pero también William se daría cuenta de que su animatrónico principal había matado a Elizabeth. O en realidad, su hija estaba tomando el control de Baby debido a que los ojos del animatrónico pasarían de ser azules a como los tenía su hijita, verdes. Por supuesto que William al enterarse de todo esto no sabe qué hacer y es por eso que decide encerrarla en Circus Baby Entertainment, un lugar ubicado debajo de Circus Baby. Tras el cierre de Circus Baby y la incertidumbre de lo ocurrido con su hija menor, estas cosas empezarían a afectar a William Afton, dando comienzo a su declive. Por eso, luego del fracaso de Circus Baby, éste vuelve a pedirle ayuda y trabajo a Henry, que a pesar de todos los problemas que había tenido con su anterior socio, le da trabajo de administrador o mecánico, por eso se lo puede ver colocándole la cabeza de Fredbear a uno de los empleados de Fredbear Family Dinner. Durante estos meses, de un año que suponemos que es 1883, Henry creó y anunció otros animatrónicos por la televisión, que serían Freddy, Foxy, Chica y Bonnie. Por supuesto que William, al ver que había creado más animatrónicos, haría crecer la tensión con su nuevo jefe, pero lo que realmente llevaría a William a ponerse de un tono violeta sería la muerte de su hijo menor, el pendejo que llora, Crying Child. ¿Se acuerdan de Mike, el hijo mayor de William? Bueno, este personaje asustaba de manera sobre medida a Crying Child y mientras ésta atormentaba a su único hermano chico, William protegía de sobre manera a su hijo menor, poniendo cámaras por toda la casa y dándole un peluche creado por él mismo para que pueda hablarle y sentirse cómodo. Todo esto, a pesar del comportamiento psicópata de William, serviría para vigilar a su hijo menor y así que no se escapara a ver a los animatrónicos debido a que a Crying Child le fascinaban. Pero William, al haber creado con Henry los dos primeros trajes sabían lo que podían hacer y lo danino que eran, por eso las medidas de sobreprotección. Pero ahora vamos a remontarnos a una teoría entre Five Nights at Freddy's 4 y The Twisted Ones, el primer libro. Supuestamente, Five Nights at Freddy's 4 ocurriría en las pesadillas de Crying Child, pero la verdad es que no, las pesadillas esas que ve son reales y no un mal sueño de este niño, ya que son parte de un plan muy macabro de su padre. Verán, en la novela de The Twisted Ones, William crea un disco que hace tener alucinaciones con animatrónicos, exagerando su forma, su tamaño, etc. Algo así como la película de Batman donde el espantapájaros tiene un spray que te hace sobredimensionar tus miedos. ¿Y cómo se relaciona esto con el juego? El tema de las alucinaciones, no Batman, no tiene nada que ver Batman acá. Bueno, tenemos que remontarnos a Five Nights at Freddy's Ultimate Custom Night, en donde los animatrónicos Nightmares aparecen en este juego, pero en este juego controlamos a William, entonces es imposible que William logre saber con exactitud cómo son estos animatrónicos si es que en realidad son las pesadillas de su hijo menor. En otras palabras, ¿cómo sabes exactamente las pesadillas de otras personas? Con lo cual, si volvemos al primer libro, nos introducen que William creó discos ilusorios para hacer creer a la gente cosas que realmente no hay, y esto lo utilizaría con Crying Child para hacer que se aleje definitivamente de los animatrónicos. Por eso es que tampoco nunca lo vemos regañar a su hijo mayor por maltratar a su hermanito, debido a que este le estaba generando un trauma con los animatrónicos, cosa que a William le servía, aunque el error de William fue confiar demasiado en Michael, porque este no sabía dónde estaba el límite de la broma, ya que Mike asustaba a su hermano solamente por diversión, y el problema se desataría en ese año 83, en el lugar donde había comenzado y terminado todo, Fredbear Family Dinner. Mike y sus amigos llevan a Crying Child por la fuerza al restaurante para seguir molestándolos con los animatrónicos en el día de su cumpleaños, y siguiendo con la broma, lo ponen en la boca de Fredbear simulando que se lo iba a comer, y desgraciadamente no solo simuló eso. Como había dicho en un principio, el sistema de resorte de Henry era sensible, por lo que al introducir un niño dentro de la boca, el traje se cerró en la cabeza de Crying Child, que luego de eso, el mini Afton entra en un estado de coma donde están todos los animatrónicos que él conocía y el peluche que le había regalado William, donde en esta pantalla se da a entender como que su padre le está dedicando las últimas palabras a su hijo, pidiéndole que lo perdone, y diciendo dos frases que quedarían para muchísimas teorías. Vos estás roto, yo te reconstruiré. Por supuesto que esto lo dice debido a que a partir de la muerte de Elizabeth, él sabía que de alguna forma los animatrónicos lograban tomar el alma de la persona y adaptarla a su cuerpo, o por lo menos ahí alma y animatrónico convivían en un solo cuerpo. Una curiosidad de esta parte de la historia es que como estamos en 1983, si recorremos la casa de los Afton, nos vamos a encontrar con un cuarto que da a entender que es de una niña, y quién era la única niña que tenía la familia Afton, Elizabeth Afton. Por lo tanto, antes de ese 1983, la hija de William ya estaba dentro del cuerpo de Baby.");
        // BORRAR
        
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).disableOptions();
            questions.get(i).setExplicationButtonVisible();
            questions.get(i).paintExplicationPanel(explication.get(i));
        }
    }

    private int showScore(){
        int numCorrectQuestions = 0;

        for (int i = 0; i < questions.size(); i++) {
            int selectedOption = questions.get(i).getSelectedOption();
            if (selectedOption == -1) {
                menuPanel.getQuestionListItems().get(i).setIcons("Wrong_Unselected_Icon", "Wrong_Selected_Icon");
             } else if (presentController.isCorrect(i, selectedOption)) {
                 numCorrectQuestions++;
                 menuPanel.getQuestionListItems().get(i).setIcons("Correct_Unselected_Icon", "Correct_Selected_Icon");
            } else {
                menuPanel.getQuestionListItems().get(i).setIcons("Wrong_Unselected_Icon", "Wrong_Selected_Icon");
            }
        }
        presentController.setResultExamC(numCorrectQuestions);
        return numCorrectQuestions;
    }

    protected void actionEventInBottomLeftButton(ActionEvent e) {
        if(e.getSource() == finishExamButton) {
            Frame.instance().setView(ExamsView.instance());
            Frame.instance().setTitle("ExamsView");
        } 
    }
}

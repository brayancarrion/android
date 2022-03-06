package com.carrion.frasesdeanimo;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    //Attributes
    protected TextView box1;
    protected Button button1;

    //ArrayList
    private ArrayList<String> phrases = new ArrayList<String>();

    //Data base inside MainActivity
    protected DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* <------------------- REFERENCES --------------------> */
        box1 = (TextView) findViewById (R.id.box1_main);
        button1 = (Button) findViewById (R.id.button1_main);


        /* <------------------- DATA BASE STRUCTURE --------------------> */
        //Content (activity)
        db = new DataBase(this);
        //Phrases content
        db.insertPhrases("No se trata de si van a derribarte, se trata de si vas a levantarte cuando lo hagan.");
        db.insertPhrases("Nadie puede hacerte sentir inferior sin tu consentimiento.");
        db.insertPhrases("Qué maravilloso es que nadie tenga que esperar ni un segundo para empezar a mejorar el mundo.");
        db.insertPhrases("El pesimista ve dificultades en cada oportunidad. El optimista ve oportunidades en cada dificultad.");
        db.insertPhrases("Muchos piensan en cambiar el mundo, pero casi nadie piensa en cambiarse a sí mismo.");
        db.insertPhrases("Si estás trabajando en algo que te importa de verdad, nadie tiene que empujarte: tu visión te empuja.");
        db.insertPhrases("Podemos sufrir muchas derrotas pero no debemos ser derrotados.");
        db.insertPhrases("No esperes. Nunca va a ser el momento adecuado.");
        db.insertPhrases("La creatividad es la inteligencia divirtiéndose.");
        db.insertPhrases("Rodéate de personas que crean en tus sueños, animen tus ideas, apoyen tus ambiciones, y saquen lo mejor de ti.");
        db.insertPhrases("No importa lo que te diga la gente, las palabras y las ideas pueden cambiar el mundo.");
        db.insertPhrases("La excelencia no es un acto, es un hábito.");
        //Another options with methods
         // System.out.println("Numero de frases: " + db.numberOfPhrases());
         // db.deleteAllPhrases();


        /* <--------------------- EVENTS ----------------------> */
        //Event: button 1
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Fault protection
                try
                {
                    /* I keep all the sentences in the ArrayList "phrases" */
                    phrases = db.getAllPhrases();

                    /* I create a random method to display the phrases */
                    int extension =  phrases.size();
                    Random random = new Random();
                    int position = random.nextInt(extension-1);

                    /* I will show the phrases in the toast and textView */
                    Toast.makeText(MainActivity.this, phrases.get(position), Toast.LENGTH_SHORT).show();
                    box1.setText(phrases.get(position));
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, "Application crash", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //Close data base "frasesanimo"
        db.close();
    }
}
package com.example.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     try{                                           //eu quero que apenas meu app tenha acesso ao BD
         SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

         //criar tabela se não existir
         //Id AUTO INCREMENT - coloca o número do ID automaticamente
         bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

         //inserir dados    INSIRA DENTRO/EM pessoas
         //cada vez que eu executar continuará executando os inserts
         bancoDados.execSQL("INSERT INTO pessoas(id, nome, idade) VALUES('Gabriel', 25)");
         bancoDados.execSQL("INSERT INTO pessoas(id, nome, idade) VALUES('Amanda', 24)");
         bancoDados.execSQL("INSERT INTO pessoas(id, nome, idade) VALUES('Susy', 18)");
         bancoDados.execSQL("INSERT INTO pessoas(id, nome, idade) VALUES('Tereza', 74)");

         //recuperar pessoas/registros como uma listagem de um cursor (que lembra um Array)
         //ONDE nome = Gabriel E idade = 25

         /*String consulta = "SELECT nome, idade " +
                 "FROM pessoas " +
                 "WHERE nome = 'Gabriel' AND idade=25";*/ //posso apertar o entre e ele vai concatenando para nós

         String consulta = "SELECT id, nome, idade " +
                 "FROM pessoas " +
                 "WHERE idade >= 25 OR idade = 18";


      /*  FAIXA ETÁRIA BETWEEN(ENTRE) OU WHERE idade IN(35,50)

       String consulta = "SELECT nome, idade " +
                 "FROM pessoas " +
                 "WHERE idade BETWEEN 35 AND 50";*/



         /*LIKE = COMO, % qualquer coisa depois do Gab

          String consulta = "SELECT nome, idade " +
                 "FROM pessoas " +
                 "WHERE nome LIKE 'Gab%';*/



         //Consigo consultar pelo que o usuário digitou:

         /*String filtro = "mar"

         String consulta = "SELECT nome, idade " +
                 "FROM pessoas " +
                 "WHERE nome LIKE '%" + filtro + "%' ";*/


            //ORDER BY = ordena por  ASC = do menor para o maior
         //DESC = do maior para o menor

        /* String consulta = "SELECT nome, idade " +
                 "FROM pessoas " +
                 "WHERE 1=1 ORDER BY nome ASC";*/

         //ORDER BY nome DESC LIMIT 3 (limitando)




         //ATUALIZANDO - UPDATE

         //bancoDados.execSQL("UPDATE pessoas SET idade = 23 WHERE nome = 'Amanda' ");

         //APAGANDO TABELA INTEIRA

         //bancoDados.execSQL("DROP TABLE pessoas");

         //APAGANDO PESSOAS

         // bancoDados.execSQL("DELETE FROM pessoas WHERE id = 2");



         Cursor cursor = bancoDados.rawQuery(consulta, null);

         //indices da tabela //o próprio cursor já vai retornar em qual indice esta essa coluna, quando informarmos a coluna.
         int indiceId = cursor.getColumnIndex("id");
         int indiceNome = cursor.getColumnIndex("nome");
         int indiceIdade = cursor.getColumnIndex("idade");

         //iniciando o cursor, sempre voltando para o primeiro item da lista por conta do while.
         cursor.moveToFirst();

         //enquanto o cursos for diferente de nulo //getString recupera o valor da coluna como String
         while (cursor != null){

             String id = cursor.getString(indiceId);
             String nome = cursor.getString(indiceNome);
             String idade = cursor.getString(indiceIdade);

             Log.i("RESULTADO - id", id + " / nome: " + nome + "/ idade: " + idade);
             //Log.i("RESULTADO - idade: ", cursor.getString(indice Idade));
             cursor.moveToNext(); //vai para o próximo.
         }

     }
     catch(Exception e)
     {
         e.printStackTrace();

     }

    }
}
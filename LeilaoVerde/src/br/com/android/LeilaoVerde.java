package br.com.android;

import android.app.Activity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.Handler;
import android.widget.*;
import android.view.*;
import android.app.*;

public class LeilaoVerde extends Activity
{
    private EditText edtLogin, edtSenha, edtRazao, edtCnpj, edtCreditos, edtEndereco;
    private ProgressDialog progressDialog;
    private Handler handler;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        CarregaTelaPrincipal();
        
    }
    
    private void CarregaTelaPrincipal () 
    {   
        setContentView(R.layout.main);
        edtLogin = (EditText)findViewById(R.id.edtLogin);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        Button btnCadastro= (Button) findViewById(R.id.btnCadastrar);
        btnCadastro.setOnClickListener(new View.OnClickListener(){
        
            public void onClick(View arg0){
                
                handler = new Handler();
                Thread thread = new Inner();
                thread.start();

                CarregaTelaCadastro();
                }});
    
    }
    private void CarregaTelaCadastro() 
    {
       setContentView(R.layout.cadastro);
       Button btnConfirma = (Button) findViewById(R.id.btnConfirma);
       Button btnRetorna = (Button) findViewById(R.id.btnRetorna);

       btnConfirma.setOnClickListener(new View.OnClickListener(){

           public void onClick(View arg0){

               try {

                   edtRazao = (EditText)findViewById(R.id.edtRazao);
                   edtCnpj = (EditText)findViewById(R.id.edtCnpj);
                   edtCreditos = (EditText)findViewById(R.id.edtCreditos);
                   edtEndereco = (EditText)findViewById(R.id.edtEndereco);

                   exibeMsg("Cadastro efetuado com sucesso", "Aviso");

                   CarregaTelaPrincipal();
       
               }
        
               catch(Exception e) {

                   exibeMsg("Erro ao cadastrar", "Erro");

               }}

       });

       btnRetorna.setOnClickListener(new View.OnClickListener(){
           
           public void onClick(View arg0){
               
               handler = new Handler();
               Thread thread = new Inner();
               thread.start();

               CarregaTelaPrincipal();

           }

       });
    }
    private class Inner extends Thread{

	public void run(){

	handler.post(new Runnable() {

	@Override
	public void run() 
	{

            progressDialog = ProgressDialog.show(LeilaoVerde.this, "", "Por favor, aguarde...");
	
        }});

	try 
        {
            Thread.sleep(1000);
        
        } catch (InterruptedException e) {}    		

            progressDialog.cancel();  		

	}
    }
    
    private void exibeMsg(String Caption, String Title) 
    {
           AlertDialog.Builder dialogo = new AlertDialog.Builder(LeilaoVerde.this);
           dialogo.setTitle(Title);
           dialogo.setMessage(Caption);
           dialogo.setNeutralButton("OK", null);
           dialogo.show();
    }
    
}
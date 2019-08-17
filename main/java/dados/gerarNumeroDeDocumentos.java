package dados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class gerarNumeroDeDocumentos {
	
	private static String calcDigVerif(String num) {  
        
		Integer primDig, segDig;  
        int soma = 0, peso = 10;  
        for (int i = 0; i < num.length(); i++)  
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;  
        if (soma % 11 == 0 | soma % 11 == 1)  
            primDig = new Integer(0);  
        else  
            primDig = new Integer(11 - (soma % 11));  
        soma = 0;  
        peso = 11;  
        for (int i = 0; i < num.length(); i++)  
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;  
        soma += primDig.intValue() * 2;  
        if (soma % 11 == 0 | soma % 11 == 1)  
            segDig = new Integer(0);  
        else  
            segDig = new Integer(11 - (soma % 11));  
        return primDig.toString() + segDig.toString();
        
    }  
    public static String geraCPF() {  
        
    	String iniciais = "";  
        Integer numero;  
        for (int i = 0; i < 9; i++) {  
            numero = new Integer((int) (Math.random() * 10));  
            iniciais += numero.toString();  
        }  
        return iniciais + calcDigVerif(iniciais);
        
    }  
    public static boolean validaCPF(String cpf) {
    	
        if (cpf.length() != 11)  
            return false;  
        String numDig = cpf.substring(0, 9);  
        return calcDigVerif(numDig).equals(cpf.substring(9, 11));
        
    }
    
    public static String geraCNPJ() {
    	
        int digito1 = 0, digito2 = 0, resto = 0;
        String  nDigResult;
        String numerosContatenados;
        String numeroGerado;
        Random numeroAleatorio = new Random();
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);
        int n10 = numeroAleatorio.nextInt(10); 
        int n11 = numeroAleatorio.nextInt(10); 
        int n12 = numeroAleatorio.nextInt(10); 
        int soma = n12*2 + n11*3 + n10*4 + n9*5 + n8*6 + n7*7 + n6*8 + n5*9 + n4*2 + n3*3 + n2*4 + n1*5;
        int valor = (soma / 11)*11;
        digito1 = soma-valor;
        resto = (digito1 % 11);
        if(digito1 < 2){
         digito1 = 0;
        }
        else {
          digito1 = 11-resto;
        }
        int soma2 =  digito1*2 + n12*3 + n11*4 + n10*5 + n9*6 + n8*7 + n7*8 + n6*9 + n5*2 + n4*3 + n3*4 + n2*5 + n1*6 ;
        int valor2 = (soma2 / 11)*11;
        digito2 = soma2-valor2;
        resto = (digito2 % 11);
        if(digito2 < 2){
         digito2 = 0;
        }
        else {
          digito2 = 11-resto;
        }
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) +"."+ String.valueOf(n3) + String.valueOf(n4) +
                              String.valueOf(n5) +"."+ String.valueOf(n6) + String.valueOf(n7) +String.valueOf(n8)+"/"+
                              String.valueOf(n9) + String.valueOf(n10) + String.valueOf(n11) +
                              String.valueOf(n12)+"-";
         nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
         numeroGerado = numerosContatenados+nDigResult;
         System.out.println("Digito 2 ->"+digito2);
         System.out.println("CNPJ Gerado " + numeroGerado);
         return numeroGerado;
         
       }
    
    public static String geraRG() {
    	
        String numerosContatenados;
        String numeroGerado;
        Random numeroAleatorio = new Random();
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3)  + String.valueOf(n4) +
                              String.valueOf(n5) + String.valueOf(n6) + String.valueOf(n7) +String.valueOf(n8)  +
                              String.valueOf(n9);
        numeroGerado = numerosContatenados;
        return numeroGerado;
        
       }
    
    public static String gerarPDF() {
    	Document document = new Document();
    	File convenioPDF = null;
    	
    	try {
    		convenioPDF = new File("convenio.pdf");
    		FileOutputStream criarPDF = new FileOutputStream(convenioPDF.getAbsoluteFile());
    		PdfWriter.getInstance(document, criarPDF);
    		
    		document.open();
    		document.add(new Paragraph("Gerando PDF para teste em convenio"));
    		document.close();
    		criarPDF.close();
    		
    	} catch (DocumentException de) {
    		System.err.println(de.getMessage());
    	} catch (IOException ioe) {
    		System.err.println(ioe.getMessage());
    	}
    	document.close();
    	
    	
    	return convenioPDF.getAbsolutePath();
    }
}

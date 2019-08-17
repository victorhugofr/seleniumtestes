package formularios;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.ufrn.imd.sigsaude.dominio.OfertaEspecialidade;
import utils.AbstractTestSigSaude;

public class FormularioCadastroOfertaDeEspecialidade extends AbstractTestSigSaude {
	
	public void inserirOfertaDeEspecialidade(OfertaEspecialidade ofertaEspecialidade) {
		driver.findElement(By.linkText("Oferta de especialidade")).click();
	    driver.findElement(By.linkText("Nova Oferta de Especialidade")).click();
	    
	    //CLIQUE EM DATA INICIO
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Aguarde...'])[1]/following::input[1]")).click();
        Random numeroAleatorio = new Random();
        int n1 = numeroAleatorio.nextInt(16);
	    for(int i=0;i<n1;i++) {
		    driver.findElement(By.cssSelector("button.v-btn:nth-child(3) > div:nth-child(1) > i:nth-child(1)")).click();
		    
	    }
	    
	    // DATA INICIO
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 17 +"]")));
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 17 +"]")).click();
	    
	    // DATA FIM 
	     driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::input[1]")).click();
	     for(int i=0;i<n1+6;i++) {
			    driver.findElement(By.cssSelector(".menuable__content__active > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(3) > div:nth-child(1) > i:nth-child(1)")).click();
		 }

	     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::div[" + 17 +"]")));
		    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::div[" + 17 +"]")).click();
		
		//HORA INICIO
		 driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::input[1]")).click();
		 driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='--'])[1]/following::span[18]")).click();
		 driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(1)")).click();
		 driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(3)")).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")));
		 driver.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")).click();
		 
		//HORA FIM 
		 driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[6]/following::input[2]")).click();
		 driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='--'])[1]/following::span[36]")).click();
		 driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(1)")).click();
		 driver.findElement(By.cssSelector("div.v-picker__title__btn:nth-child(3)")).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")));
		 driver.findElement(By.cssSelector("span.v-time-picker-clock__item:nth-child(2) > span:nth-child(1)")).click();
		 
		 //TIPO ATENDIMENTO
		 driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Hora Marcada'])[1]/following::div[4]")).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".v-list > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > div:nth-child(1)")));
		 driver.findElement(By.cssSelector(".v-list > div:nth-child(1) > a:nth-child(1) > div:nth-child(1) > div:nth-child(1)")).click();
		 
		 WebElement slider = driver.findElement(By.cssSelector(".v-slider__thumb-container"));

		//TEMPO MEDIO
		Actions move = new Actions(driver);
		Action action = move.dragAndDropBy(slider, 270, 0).build();
		action.perform();

		 driver.findElement(By.cssSelector("button.v-btn:nth-child(2) > div:nth-child(1)")).click();
	}
}

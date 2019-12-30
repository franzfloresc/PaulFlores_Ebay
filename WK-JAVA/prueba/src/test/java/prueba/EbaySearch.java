package prueba;

import java.awt.print.Printable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class EbaySearch {
	
	public WebDriver driver;
	By resultadoLocator = By.linkText("Guardar esta b�squeda");
	By buscadormarcaLocator= By.id("w4-w12-0[0]");
	By marcaPumaLocator = By.xpath("//input[@type='checkbox' and (contains(@aria-label,'PUMA'))]");
	By tallaLocator= By.xpath("//input[@type='checkbox' and (contains(@aria-label,'10.5'))]");
	By cantResultadosLocator =By.xpath("//*[@id=\"mainContent\"]/div[1]/div/div[2]/div/div[1]/h1/span[1]");
	By controlResultado = By.xpath("//div//div[@class='srp-controls--selected-value']");
	By preciobajoPrimero = By.xpath("//span[contains(text(),'Precio + Env�o: m�s bajo primero')]");
	By precioaltoPrimero = By.xpath("//span[contains(text(),'Precio + Env�o: m�s alto primero')]");

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver2/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
	}
	
	@Test
	public void testEbayPage() {
		WebElement searchbox = driver.findElement(By.name("_nkw"));
		searchbox.clear();
		searchbox.sendKeys("Zapatos");
		searchbox.submit();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Escribiendo en Caja de Marca
		System.out.print("|Escribiendo en Caja de Marca | ");
		WebElement searchboxMarca = driver.findElement(buscadormarcaLocator);
		searchboxMarca.clear();
		searchboxMarca.sendKeys("PUMA");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		//Seleccionando CheckBox Marca Puma
		System.out.print("Seleccionando CheckBox Marca Puma | ");
		WebElement checkboxMarca = driver.findElement(marcaPumaLocator);
		checkboxMarca.click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		//Seleccionando CheckBox Talla 10.5
		System.out.print("Seleccionando Talla 10.5 | ");
		WebElement checkboxTalla = driver.findElement(tallaLocator);
		checkboxTalla.click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		//Imprimiendo el n�mero de resultados
		WebElement cant = driver.findElement(cantResultadosLocator);
		System.out.print("Cant Resultados: "+cant.getText());
		
		//Ordenando por Precio Ascendente
		System.out.print("| Precio Ascendente | ");
		Actions actions=new Actions(driver);
		WebElement controlador= driver.findElement(controlResultado);
		actions.moveToElement(controlador).perform();
		
		WebElement precioAscendente = driver.findElement(preciobajoPrimero);
		precioAscendente.click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		/*//Ordenando por Precio Descendente
		System.out.print("| Precio Descendente | ");
		actions.moveToElement(controlador).perform();
		WebElement precioDescendente = driver.findElement(precioaltoPrimero);
		precioDescendente.click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);*/
		
	}
	
	@After
	public void tearDown() {
		//driver.quit();

	}
}

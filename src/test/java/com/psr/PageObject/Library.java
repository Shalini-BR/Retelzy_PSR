package com.psr.PageObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
*This class extends Base Class, it contains all generic/reusbale methods used for test cases
* 
* @author  Shalini
* @version PSR 
*/

public class Library extends BaseClass {
	public static class Interaction {
		static WebDriverWait wait;

		// ***[ TO CAPTURE SCREENSHOT ]***
		protected static Logger log;

		public static String captureScreen(WebDriver driver, String tname) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destination = 
					System.getProperty("user.dir") + "/Screenshots/" + (tname + "_" + Library.getDateTimeAsString())
							.replace("-", "_").replace(":", "_").replace(" ", "_") + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			System.out.println("Screenshot taken");
			return destination;
		}

		// ****[ CLEARDATA To Clear TextBox fields ]***
		public static void ClearData(String xpath) throws IOException {
			try {
				WebElement clearField = driver.findElement(By.xpath(xpath));
				clearField.clear();
			} catch (Exception e) {
				captureScreen(driver, "setTextBoxByXpath");
				Assert.assertFalse(false);
				log.info("User not able to send data in Text Box 'Test Failed'");
			}
		}

		// ****[ SENDKEYS Send Data in Text Box by Xpath ]***
		public static void sendkeys(String xpath, String data) throws IOException {
			try {
				driver.findElement(By.xpath(xpath)).sendKeys(data.trim());
			} catch (Exception e) {
				captureScreen(driver, "setTextBoxByXpath");
				Assert.assertFalse(false);
				log.info("User not able to send data in Text Box 'Test Failed'");
			}
		}

		// *****[ WAIT FOR VISIBILITY OF ELEMENT ]****
		public static void wait_ElementToBeVisible(String xpath) throws IOException {
			wait = new WebDriverWait(driver, 60);
			try {
				// WebDriverWait wait = new WebDriverWait(driver,100);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			} catch (Exception e) {
				captureScreen(driver, "wait_ElementToBeVisible");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}
		}

		// *****[ WAIT FOR INVISIBILITY OF ELEMENT ]****
		public static void wait_ElementToBeInVisible(String xpath) throws IOException {
			wait = new WebDriverWait(driver, 60);
			try {

				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
			} catch (Exception e) {
				captureScreen(driver, "wait_ElementToBeVisible");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}
		}

		// ****[ WAIT FOR CLICKABLE ]****
		public static String wait_ElementToBeClicakable(String xpath) throws IOException {
			wait = new WebDriverWait(driver, 60);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			} catch (Exception e) {
				captureScreen(driver, "wait_ElementToBeclickable");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}
			return xpath;
		}

		// *****[ GET UNIQUE ID OF THE REQUIRED GRID ]****
		public static String GetJqxID(String xpath) throws IOException {
			wait = new WebDriverWait(driver, 60);
			String jqxid = null;
			try {

				WebElement id = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				jqxid = id.getAttribute("id");

			} catch (Exception e) {
				captureScreen(driver, "GetJqxID");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}
			return jqxid;
		}

		// ****[ GET LIST AND CLCIK ON THAT VALUE ]****
		public static void GetListAndSelectValue(String xpath, String Value) throws IOException {
			try {
				List<WebElement> text = driver.findElements(By.xpath(xpath));
				for (WebElement v : text) {
					String v1 = v.getText();

					if (v1.contains(Value)) {
						v.click();
						System.out.println(v1);
						break;
					}
				}
			} catch (Exception e) {
				captureScreen(driver, "GetListAndSelectValue");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}

		}

		// ****[ GET LIST AND VERIFY THAT VALUE ]****
		public static void GetListAndCheckValue(String xpath, String Value) throws IOException {
			String v1 = null;
			try {
				List<WebElement> text = driver.findElements(By.xpath(xpath));
				for (WebElement v : text) {
					v1 = v.getText();
					System.out.println(v1);
					if (v1.contains(Value)) {
						Assert.assertTrue(true);
					}

				}
			} catch (Exception e) {
				captureScreen(driver, "GetListAndSelectValue");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}

		}

		// ***[ MOVE TO PARTICULAR ELEMENT AND CLCIK ON IT ]******
		public static void MoveToEle_AndClick(String xpath) throws IOException {
			wait = new WebDriverWait(driver, 60);
			try {
				Actions a = new Actions(driver);
				WebElement value2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				a.moveToElement(value2);
				a.click();
				a.release().perform();
			} catch (Exception e) {
				captureScreen(driver, "MoveToEle_AndClick");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}
		}

		// ****[ MOVE TO PARTICULAR ELEMENT AND ENTER DATA ]***
		public static void MoveToEle_AndEnterData(String xpath, String Value) throws IOException {
			wait = new WebDriverWait(driver, 60);
			try {
				Actions a = new Actions(driver);
				WebElement value3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				a.sendKeys(value3, Value);
				a.release().perform();

			} catch (Exception e) {
				captureScreen(driver, "MoveToEle_AndEnterData");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}
		}

		// ***[ SELECT DROPDOWN VALUES ONLY IF SELECT IS PRESENT ]****
		public static void SelectDropDownValues(String xpath, String Value) throws IOException {
			try {
				Select s = new Select(driver.findElement(By.xpath(xpath)));
				s.selectByVisibleText(Value);

			} catch (Exception e) {
				captureScreen(driver, "MoveToEle_AndEnterData");
				Assert.assertFalse(false);
				log.info("Element is not Visible 'Test Failed'" + e.getMessage());

			}
		}

		// ****[ CLICK ON ELEMENT ]***
		public static void click(String xpath) throws IOException {
			try {
				WebElement ele = driver.findElement(By.xpath(xpath));
				ele.click();
			} catch (Exception e) {
				captureScreen(driver, "click");
				Assert.assertFalse(false);
				System.out.println(e.getMessage());
				log.info("User not able to Click to element 'Test Failed'");
			}
		}

		// ***[ CLICK ENTER FROM KEYBOARD ]***
		public static void clickEnter(String xpath) throws IOException {
			try {
				WebElement textbox = driver.findElement(By.xpath(xpath));
				textbox.sendKeys(Keys.RETURN);
			} catch (Exception e) {
				captureScreen(driver, "click");
				Assert.assertFalse(false);
				System.out.println(e.getMessage());
				log.info("User not able to Click to element 'Test Failed'");
			}
		}

		

		// ***[ GET TOASTER MESSAGE ]****
		public static String ToasterMessage() throws IOException {
			wait = new WebDriverWait(driver, 100);
			String Text = null;
			try {

				WebElement s = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@id='toastr-container']/div/div/span")));
				Text = s.getText();
				System.out.println(Text);
			} catch (Throwable e) {
				System.err.println("Error while waiting for the notification to appear: " + e.getMessage());
			}
			return Text;

		}

		// *****[ COPY PASTE ]****
		public static void PSRGridFields(String jqxGridId, String string, Object object, String header)
				throws IOException {
			List<String> sendKeysList = new ArrayList();
			try {
				for (int i = 0; i < 1; i++) { // int i = 0; i < 3; i++
					Actions a = new Actions(driver);
					WebElement ndc = wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.xpath("//*[@id='row" + i + jqxGridId + "']/div[" + string + "]")));
					a.doubleClick(ndc);
					if (checkHeader(header, "Tech Pack Received Date") || checkHeader(header, "packaging ready date")
							|| checkHeader(header, "fabric pp date") || checkHeader(header, "fabric pp date")
							|| checkHeader(header, "cpo acc date by vendor") || checkHeader(header, "sample merch eta")
							|| checkHeader(header, "sample floor set eta") || checkHeader(header, "sample dcom eta")
							|| checkHeader(header, "sample mailer eta")
							|| checkHeader(header, "Photo/Merchant Sample Send date")
							|| checkHeader(header, "Photo/Merchant Sample ETA date")
							|| checkHeader(header, "Photo/Merchant Sample Send date")
							|| checkHeader(header, "Marketing Sample Send date")
							|| checkHeader(header, "Marketing Sample ETA date")
							|| checkHeader(header, "Visual Sample Send date")
							|| checkHeader(header, "Visual Sample ETA date")
							|| checkHeader(header, "Copyright Sample Send date")
							|| checkHeader(header, "Copyright Sample ETA date")
							|| checkHeader(header, "Additional Bulk Lot Approve")
							|| checkHeader(header, "TOP sample ETA Date"))

					{
						a.sendKeys(ndc, object.toString());
					} else {
						a.sendKeys(ndc, object.toString() + i + "1");
					}
					a.release();
					a.perform();
					sendKeysList.add(object.toString());
					Thread.sleep(1000);
				}
				driver.findElement(By.xpath("//*[@id='save-changes-psr']")).click();
				Thread.sleep(10000);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		public static boolean checkHeader(String header, String value) {
			return header.trim().equalsIgnoreCase(value);
		}

		// Send Data in Text Box by Name
		/*
		 * public static void setTextBoxByName(String Name, String data) throws
		 * IOException { try { driver.findElement(By.name(Name)).sendKeys(data);
		 * } catch (Exception e) { captureScreen(driver, "setTextBoxByName");
		 * Assert.assertFalse(false);
		 * log.info("User not able to send data in Text Box 'Test Failed'"); } }
		 * 
		 * // Send Data in Text Box by ID public static void
		 * setTextBoxByID(String id, String data) throws IOException { try {
		 * driver.findElement(By.id(id)).sendKeys(data); } catch (Exception e) {
		 * captureScreen(driver, "setTextBoxByID"); Assert.assertFalse(false);
		 * log.info("User not able to send data in Text Box 'Test Failed'"); } }
		 * 
		 * // Navigate Element public static void moveToElement(String xpath)
		 * throws IOException { try { Actions act=new Actions(driver);
		 * WebElement ele=driver.findElement(By.xpath(xpath));
		 * act.moveToElement(ele).build().perform(); } catch (Exception e) {
		 * captureScreen(driver, "moveToElement"); Assert.assertFalse(false);
		 * log.info("User not able to navigate to element 'Test Failed'"); } }
		 * 
		 * 
		 * //Search and Select element from Drop down public static void
		 * selectAndSerachEle(String xpath1,String xpath2, String xpath3, String
		 * eleText) throws IOException { try {
		 * driver.findElement(By.xpath(xpath1)).click();
		 * driver.findElement(By.xpath(xpath2)).sendKeys(eleText);
		 * driver.findElement(By.xpath(xpath3)).click(); } catch (Exception e) {
		 * captureScreen(driver, "loginTest"); Assert.assertFalse(false);
		 * log.info("User not able to Search and Select element 'Test Failed'");
		 * } }
		 * 
		 * //Select Element by Text public static void selectEleByText(String
		 * xpath, String eleText) throws IOException { try {
		 * driver.findElement(By.xpath(xpath)).click(); WebElement
		 * selectEle=driver.findElement(By.xpath(xpath)); Select sel=new
		 * Select(selectEle); sel.selectByVisibleText(eleText); } catch
		 * (Exception e) { captureScreen(driver, "moveToElement");
		 * Assert.assertFalse(false);
		 * log.info("User not able to Select element 'Test Failed'"); } }
		 * 
		 * //Select Element by Index public static void selectEleByIndex(String
		 * xpath, int IndText) throws IOException { try { WebElement
		 * selectEle=driver.findElement(By.xpath(xpath)); Select sel=new
		 * Select(selectEle); sel.selectByIndex(IndText); } catch (Exception e)
		 * { captureScreen(driver, "moveToElement"); Assert.assertFalse(false);
		 * log.info("User not able to Select element 'Test Failed'"); } }
		 * 
		 * //Select Element by Value public static void selectEleByValue(String
		 * xpath, String ValText) throws IOException { try { WebElement
		 * selectEle=driver.findElement(By.xpath(xpath)); Select sel=new
		 * Select(selectEle); sel.selectByValue(ValText); } catch (Exception e)
		 * { captureScreen(driver, "moveToElement"); Assert.assertFalse(false);
		 * log.info("User not able to Select element 'Test Failed'"); } }
		 * 
		 * //User defined wait public static boolean userDefinedWait(WebElement
		 * element) throws InterruptedException { int maxWait=1000; int
		 * intervalWait= 2; int counter=0; while(counter>maxWait) {
		 * Thread.sleep(intervalWait*1000); counter+=intervalWait;
		 * System.out.println("counter....... "+counter); try {
		 * if(element.isDisplayed()) {
		 * System.out.println("Element found...."+element.getText()); return
		 * true; } }catch(NoSuchElementException noelememt) {
		 * noelememt.getMessage(); }
		 * 
		 * } return false; } //User defined wait public static boolean
		 * userWait() throws InterruptedException { int maxWait=2000; int
		 * intervalWait= 2; int counter=0; while(counter>maxWait) {
		 * Thread.sleep(intervalWait*20000); counter+=intervalWait;
		 * //System.out.println("counter....... "+counter); } return true; }
		 * 
		 */
	}

	public static class helper {

	}

	public static String getDateTimeAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		return sdf.format(new Date());
	}

	public static boolean checkNullOrEmpty(String paramString) {
		return (paramString != null && !paramString.equalsIgnoreCase("") && paramString.trim().length() != 0);
	}
	
	// ****[ PRINT OUTPUT ON CONSOLE ]****
			public static void print(String printText) throws IOException {
				log.info(printText);
			}
}

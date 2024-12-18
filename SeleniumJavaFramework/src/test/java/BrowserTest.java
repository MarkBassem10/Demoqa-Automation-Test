import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserTest {
    public static void main(String[] args) {
      
	String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath+"\\Drivers\\ChromeDriver\\chromedriver.exe" );
		WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser window and set implicit wait
            driver.manage().window().maximize();

            // Step 1: Navigate to the URL
            driver.get("https://demoqa.com/");

            // Step 2: Navigate to Forms > Practice Form
            driver.findElement(By.xpath("//h5[text()='Forms']")).click();
            driver.findElement(By.xpath("//span[text()='Practice Form']")).click();

            // Step 3: Fill out the Practice Form
            driver.findElement(By.id("firstName")).sendKeys("Mark");
            driver.findElement(By.id("lastName")).sendKeys("Nassif");
            driver.findElement(By.id("userEmail")).sendKeys("mark.nassif@test.com");
            driver.findElement(By.xpath("//label[text()='Male']")).click(); // Select gender
            driver.findElement(By.id("userNumber")).sendKeys("1000360639");

            // Set Date of Birth
            driver.findElement(By.id("dateOfBirthInput")).click();
            driver.findElement(By.className("react-datepicker__year-select")).sendKeys("1999");
            driver.findElement(By.className("react-datepicker__month-select")).sendKeys("June");
            driver.findElement(By.xpath("//div[text()='4']")).click();

            // Subjects
            driver.findElement(By.id("subjectsInput")).sendKeys("Maths");
            driver.findElement(By.id("subjectsInput")).sendKeys("\n");

            // Hobbies
            driver.findElement(By.xpath("//label[text()='Sports']")).click();


            // Upload Picture
            WebElement uploadElement = driver.findElement(By.id("uploadPicture"));
            uploadElement.sendKeys(System.getProperty("user.dir") + "/Personal-Image.jpg"); // Replace 'sample.jpg' with a valid image path

            // Address
            driver.findElement(By.id("currentAddress")).sendKeys("5 Korayesh Street, Nasr City");

            // Select State and City
            driver.findElement(By.id("react-select-3-input")).sendKeys("NCR\n");
            driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi\n");

            // Submit Form
            driver.findElement(By.id("submit")).click();

            // Validate Submitted Data
            validateSubmittedData(driver, "Student Name", "Mark Nassif");
            validateSubmittedData(driver, "Student Email", "mark.nassif@test.com");
            validateSubmittedData(driver, "Gender", "Male");
            validateSubmittedData(driver, "Mobile", "1000360639");
            validateSubmittedData(driver, "Date of Birth", "04 June,1999");
            validateSubmittedData(driver, "Subjects", "Maths");
            validateSubmittedData(driver, "Hobbies", "Sports");
            validateSubmittedData(driver, "Picture", "Personal-Image.jpg");
            validateSubmittedData(driver, "Address", "5 Korayesh Street, Nasr City");
            validateSubmittedData(driver, "State and City", "NCR Delhi");

            System.out.println("Test Passed: All data validated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
            // Quit browser
        	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
            driver.quit();
        }
    }

    // Helper method to validate submitted data
    public static void validateSubmittedData(WebDriver driver, String label, String expectedValue) {
        String actualValue = driver.findElement(By.xpath("//td[text()='" + label + "']/following-sibling::td")).getText();
        if (actualValue.equals(expectedValue)) {
            System.out.println(label + " is correctly submitted: " + actualValue);
        } else {
            System.err.println("Validation failed for " + label + ". Expected: " + expectedValue + ", but got: " + actualValue);
        }
    }
}


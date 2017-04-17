/*@author : Hemant Choudhari
 * Project : avenue_code
 * App: todo_app_testing
 * Testsuit:1 
 * Total Test case 7 - [ 6 test cases are running. There is one bug need to to be fix for test case no 6]
 * 
 * */
package todo_app_testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class ToDoAppTest {
	WebDriver driver;
	String baseUrl;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ToDoAppTest object = new ToDoAppTest();
		object.setup(); // to set the driver
		object.signIn(); // to sign in with credentials
		object.mytask_link_present_test(); // to verify my task link is present
		object.greetingtext_test();// Verify the greeting text
		object.createTask();// to verify user should be able to create task
		object.removeTask();// to verify user should be able to remove task
		object.manageSubtask(); // to verify ManageSubTasks button is present
		// object.updateSubtask(); //user should be able to update the subtasks
		object.emptySubtask(); // verify user is should not be able to create
								// empty task

	}

	// initial setup
	private void setup() throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"/Users/Hemantc09/Documents/SeleniumFiles/chromedriver");
		driver = new ChromeDriver();
		baseUrl = "http://qa-test.avenuecode.com"; // todo app test URL
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// sign in to the - Only to sign in - Test case focus on todo app
	private void signIn() throws Exception {
		// TODO Auto-generated method stub
		driver.findElement(By.partialLinkText("Sign In")).click();
		driver.findElement(By.id("user_email")).sendKeys("hemantc09@gmail.com");
		driver.findElement(By.id("user_password")).sendKeys("Sanoje97@");
		driver.findElement(By.name("commit")).click();
		System.out.println("Login in successful");
	}

	// test 1 - to verify my task link is present
	private void mytask_link_present_test() throws Exception {
		System.out.println("test 1");
		if (driver.findElement(By.partialLinkText("My Tasks")) != null) {
			System.out.println("Pass: Element is Present");
		} else {
			System.out.println("Fail: Element is Absent");
		}

		// to verify navigate URL is correct
		// "http://qa-test.avenuecode.com/tasks"
		String mytaskUrl = "http://qa-test.avenuecode.com/tasks";
		driver.findElement(By.partialLinkText("My Tasks")).click();
		String currentUrl = driver.getCurrentUrl();
		// System.out.println(mytaskUrl +" "+currentUrl);
		if (currentUrl.equals(mytaskUrl)) {
			System.out.println("Pass: correct navigation");
		} else {

			System.out.println("Fail: Incorrect navigation");
		}
	}

	// test 2 - verify the greeting text is correct based on user looged in name
	private void greetingtext_test() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("test 2");
		String looged_in_username = driver
				.findElement(
						By.xpath("//div[@class='navbar-collapse collapse']/ul[2]/li[1]"))
				.getText();

		String[] splitstring = looged_in_username.trim().split(" ");
		String firstName = splitstring[1];// get the logged in user first name
											// to verify the greeting text
											// format
		// System.out.println(firstName);
		String expectedgreetingText = "Hey " + firstName
				+ ", this is your todo list for today:";
		String currentgreetingText = driver.findElement(
				By.xpath("/html/body/div[1]/h1")).getText(); // get the current
																// greeting text

		if (currentgreetingText.equals(expectedgreetingText)) {
			System.out.println("Pass: Greeting text is correct");
		} else {

			System.out.println("Fail: Greeting text is Incorrect");
		}

	}

	// test 3 -Verify user is able to create task by hitting enter key or add
	// task button
	private void createTask() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("test 3");
		// create task by pressing ENTER Key
		driver.findElement(By.id("new_task")).sendKeys("Task 1");
		driver.findElement(By.id("new_task")).sendKeys(Keys.ENTER);
		if (driver.findElement(By.partialLinkText("Task 1")) != null) {
			System.out.println("Pass: Task 1 is Created using ENTER key");
		} else {
			System.out.println("Fail: Task 1 is not Created using ENTER Key");
		}

		// create task by selecting Add button
		driver.findElement(By.id("new_task")).sendKeys("Task 2");
		driver.findElement(By.xpath("//div[@class='input-group']/span"))
				.click();
		;

		if (driver.findElement(By.partialLinkText("Task 2")) != null) {
			System.out.println("Pass: Task 2 is Created using Add button");
		} else {
			System.out.println("Fail: Task 2 is not Created using Add button");
		}

	}

	// test - 4 Verify user should be able to remove the task and remove button
	// is present
	private void removeTask() throws Exception {
		// TODO Auto-generated method stub
		// verify the remove button is present for task 2
		System.out.println("test 4");
		if (driver.findElement(By
				.xpath("//table[@class='table']/tbody/tr[1]/td[5]")) != null) {
			System.out
					.println("Pass: Task 2 Remove button is  present to remove it ");
		} else {
			System.out
					.println("Fail: Task 2 Remove button is not present to remove it");
		}

		driver.findElement(
				By.xpath("//table[@class='table']/tbody/tr[1]/td[5]")).click();
		// verify task is deleted
		if (driver.getPageSource().contains("Task 2")) {
			System.out.println("Fail: Task 2 is not removed");
		} else {
			System.out.println("Pass: Task 2 is removed");
		}
	}

	// test - 5 User should be able create SubTasks form Manage SubTasks button

	private void manageSubtask() throws Exception {
		System.out.println("test 5");
		// verify the Manage SubTasks button is present to add subtask for Task
		// 1
		if (driver.findElement(By
				.xpath("//table[@class='table']/tbody/tr[1]/td[4]")) != null) {
			System.out
					.println("Pass: Task 1 'Manage SubTasks' button is  present to add subtask ");
		} else {
			System.out
					.println("Fail: Task 1 'Manage SubTasks' button is not  present to add subtask ");
		}
		driver.findElement(
				By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).click();
		driver.findElement(By.id("new_sub_task")).clear();
		driver.findElement(By.id("new_sub_task")).sendKeys("SubTask 1");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy "); // decide
																		// the
																		// format
		Date date = new Date();
		// Now format the date
		String todaysDate = dateFormat.format(date);
		// Print the Date
		System.out.println(todaysDate);
		driver.findElement(By.id("dueDate")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("dueDate")).sendKeys(todaysDate);
		Thread.sleep(2000);
		driver.findElement(By.id("add-subtask")).click();

		// verify the SubTasks 1 is created

		if (driver.findElement(By.partialLinkText("SubTask 1")) != null) {
			System.out.println("Pass: SubTask 1 is Created using Add button");
		} else {
			System.out
					.println("Fail: SubTask 1 is not Created using Add button");
		}

		driver.findElement(
				By.xpath("//div[@class='modal-footer ng-scope']/button"))
				.click();
	}

	// test case -7 User should not be able to create empty task . he should
	// provide all the deails

	private void emptySubtask() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("test 7");
		driver.findElement(
				By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).click();
		driver.findElement(By.id("new_sub_task")).clear();
		String subtask1 = "";
		driver.findElement(By.id("new_sub_task")).sendKeys(subtask1); // send
																		// empty
																		// subtask
																		// input

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy "); // decide
																		// the
																		// format
		Date date = new Date();
		// Now format the date
		String todaysDate = dateFormat.format(date);
		// Print the Date
		// System.out.println(todaysDate);
		driver.findElement(By.id("dueDate")).clear();
		Thread.sleep(2000);

		driver.findElement(By.id("dueDate")).sendKeys(" "); // send empty date
															// input
		Thread.sleep(2000);

		// verify the empty input here.

		if ((driver.findElement(By.id("dueDate")).getText().equals("") || driver
				.findElement(By.id("new_sub_task")).getText().equals(""))) {
			System.out
					.println("Please enter Subtask details e.g. Due date and Subtask Description");
			System.out.println("Pass: Empty subtask test case passed");

		} else {
			System.out.println("Fail: user is able to create empty subtask ");
		}

		driver.findElement(By.id("dueDate")).sendKeys(" 04/17/2017");
		driver.findElement(By.id("new_sub_task")).sendKeys("Subtask 2");
		driver.findElement(By.id("add-subtask")).click();
		driver.findElement(
				By.xpath("//div[@class='modal-footer ng-scope']/button"))
				.click();

	}

	/*
	 * This issue needs to be fixed in order to run this test case. There is bug
	 * already logged //test - 6 user should be able to update the Subtasks
	 * 
	 * 
	 * private void updateSubtask() throws Exception { // TODO Auto-generated
	 * method stub
	 * driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]"
	 * )).click(); Thread.sleep(2000);
	 * driver.findElement(By.partialLinkText("SubTask 1")).click();
	 * driver.findElement(By.id("dueDate")).clear();
	 * driver.findElement(By.id("dueDate")).sendKeys("04/06/2017");
	 * 
	 * driver.findElement(By.xpath(
	 * "//div[@class='modal-body ng-scope']/div[2]/table/tbody/tr/td[2]/form/div/input"
	 * )).clear(); driver.findElement(By.xpath(
	 * "//div[@class='modal-body ng-scope']/div[2]/table/tbody/tr/td[2]/form/div/input"
	 * )).sendKeys("SubTask 1 updated"); driver.findElement(By.xpath(
	 * "//div[@class='modal-body ng-scope']/div[2]/table/tbody/tr/td/form/div/span/button[1]"
	 * )).click();
	 * 
	 * if(driver.findElement(By.partialLinkText("SubTask 1 updated")) != null )
	 * { System.out.println("Pass: subtask updated"); } else {
	 * System.out.println("Fail: subtask not  updated");
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

}

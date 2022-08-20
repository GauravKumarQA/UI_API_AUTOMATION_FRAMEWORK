package pageObjects.API;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pageObjects.PageObjectInterfaces.IHome;
import pageObjects.Web.BoardDetailsPage;
import reporting.ExtentReportManager;
import utils.Constants;

public class Home implements IHome{
	
	//Logger
	public static Logger log = Logger.getLogger(Home.class.getName());
	
	//Reporting Objects
	ExtentTest reporter;
	ExtentReports report;
	ExtentReportManager rp= new ExtentReportManager();
	
	public Home(ExtentTest reporter) {
		this.reporter = reporter;
		report = ExtentReportManager.getExtentReports();
	}

	@Override
	public boolean validateUserIsLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean creatBorad(String boardName) {
		log.info("Creating boad using api");
		Response res = RestAssured.given()
				.baseUri(Constants.TRELLO_BASE_URI)
				.basePath("1/boards/")
				.queryParam("name", boardName)
				.queryParam("key", Constants.TRELLO_APIKEY)
				.queryParam("token", Constants.TRELLO_Token)
				.contentType("application/json")
				.when().post();
		log.info("Status is" +res.getStatusCode());
		if(String.valueOf(res.getStatusCode()).contains(Constants.SUCCESS_CODE)) {
			log.info("Board created successfully via API");
			return true;
		}
		else {
			log.error("Board not created via API");
			rp.fail("Board not created via API Expected:" + Constants.SUCCESS_CODE + " Actual: " +  res.getStatusCode() + " Response: " + res.getBody().asPrettyString() , report, reporter);
			return false;
		}
	}

	@Override
	public boolean deleteBoard(String boardID) {
		log.info("Deleting board : " +  boardID);
		Response res = RestAssured.given()
				.baseUri(Constants.TRELLO_BASE_URI)
				.basePath("1/boards/{id}")
				.queryParam("key", Constants.TRELLO_APIKEY)
				.queryParam("token", Constants.TRELLO_Token)
				.pathParam("id", boardID)
				.when().delete();
		log.info("Status is" +res.getStatusCode());
		if(String.valueOf(res.getStatusCode()).contains(Constants.SUCCESS_CODE)) {
			log.info("Board deleted successfully via API: " + boardID);
			return true;
		}
		else {
			log.error("Board not able to delete via API" + boardID);
			rp.fail("Board not created via API Expected:" + Constants.SUCCESS_CODE + " Actual: " +  res.getStatusCode() + " Response: " + res.getBody().asPrettyString() , report, reporter);
			return false;
		}
	}

	@Override
	public boolean addList(String listName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeList(String listName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCard(String cardName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCard(String cardName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean moveAcard(String from, String to, String cardName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAllBoards() {
		log.info("Getting all boards via API");
		Response res = RestAssured.given()
		.baseUri(Constants.TRELLO_BASE_URI)
		.basePath("1/members/me/boards")
		.queryParam("key", Constants.TRELLO_APIKEY)
		.queryParam("token", Constants.TRELLO_Token).when().get();
		if(!String.valueOf(res.getStatusCode()).contains(Constants.SUCCESS_CODE)) {
			log.error("Not able to get boards details");
			rp.fail("Board not created via API Expected:" + Constants.SUCCESS_CODE + " Actual: " +  res.getStatusCode() + " Response: " + res.getBody().asPrettyString() , report, reporter);
			return false;
		}
		JSONArray JSONResponseBody = new   JSONArray(res.body().asString());
		int size = JSONResponseBody.length();
		if(size<1) {
			log.info("No board is present in your trello instances");
		}
		log.info("Total Number of board present: " + size);
		for(int i=0 ; i<size; i++) {
			JSONObject  obj = JSONResponseBody.getJSONObject(i);
			log.info("Board " + i  + ": "  + obj.get("name"));
		}
		return true;	
	}

	@Override
	public boolean deleteAllBoard() {
		log.info("Deleting all boards via API");
		Response res = RestAssured.given()
		.baseUri(Constants.TRELLO_BASE_URI)
		.basePath("1/members/me/boards")
		.queryParam("key", Constants.TRELLO_APIKEY)
		.queryParam("token", Constants.TRELLO_Token).when().get();
		if(!String.valueOf(res.getStatusCode()).contains(Constants.SUCCESS_CODE)) {
			log.error("Not able to get boards details");
			rp.fail("Board not created via API Expected:" + Constants.SUCCESS_CODE + " Actual: " +  res.getStatusCode() + " Response: " + res.getBody().asPrettyString() , report, reporter);
			return false;
		}
		JSONArray JSONResponseBody = new   JSONArray(res.body().asString());
		int size = JSONResponseBody.length();
		if(size<1) {
			log.info("No board is present in your trello instances");
			return true;
		}
		log.info("Total Number of board present: " + size);
		for(int i=0 ; i<size; i++) {
			JSONObject  obj = JSONResponseBody.getJSONObject(i);
			deleteBoard(obj.get("id").toString());
		}
		return true;
	}

}

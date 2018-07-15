package madbarsoft.com.computermcqqa.selftest;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.HashMap;

public class SelfTestService {

    private SharedPreferences userPref;
    private SharedPreferences.Editor editor;
    private Context context;
    private int categoryId;
    private int PRIVATE_MODE = 0;

    // Self Test result holder shared file name
    private static final String SHP_FILE_CATEGORY_ID_10="shPrefFileCategoryId_10";
    private static final String SHP_FILE_CATEGORY_ID_11="shPrefFileCategoryId_11";

    // user email (make variable public to access from outside)
    public static final String CATEGORY_ID = "categoryId";
    public static final String TAKEN_QUESTIONS = "takenQuestions";
    public static final String NUMBER_OF_CORRECT_ANS = "numberOfCorrectAns";
    public static final String DATE_OF_TEST = "testDate";

    //constructor
    public SelfTestService(Context context, int categoryId){
        this.context = context;
        this.categoryId = categoryId;
    }
    public void initSharedPrefFile(){
        if(categoryId==10){
            userPref = context.getSharedPreferences(SHP_FILE_CATEGORY_ID_10, PRIVATE_MODE);
        }else{
            userPref = context.getSharedPreferences(SHP_FILE_CATEGORY_ID_11, PRIVATE_MODE);
        }
        editor = userPref.edit();
    }
    // Get Stored Session User data
    public SelfTestModel getSelfTestResultFromShp(){
        initSharedPrefFile();
        SelfTestModel selfTestModel = new SelfTestModel();
        selfTestModel.setCategoryId(userPref.getInt(CATEGORY_ID, -1));
        selfTestModel.setTakenQuestions(userPref.getInt(TAKEN_QUESTIONS, -1));
        selfTestModel.setNumberOfCorrectAns(userPref.getInt(NUMBER_OF_CORRECT_ANS, -1));
        selfTestModel.setTestDate(new Date());
        return selfTestModel;
    }
    // Storing result data in Pref
    public void createSelfTestResultToShp(int categoryId, int takenQuestions, int numberofCurrectAns, Date testDate){
        initSharedPrefFile();
        editor.putString(CATEGORY_ID, String.valueOf(categoryId));
        editor.putString(TAKEN_QUESTIONS, String.valueOf(takenQuestions));
        editor.putString(NUMBER_OF_CORRECT_ANS, String.valueOf(numberofCurrectAns));
        editor.putString(DATE_OF_TEST, testDate.toString());
        // commit change
        editor.commit();
    }
    // Clearing all user data from Shared Preferences registeredUserDataFile
    public void clearAllData(){
        editor.clear();
        editor.commit();
    }




}

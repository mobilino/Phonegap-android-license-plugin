package de.mobilino.phonegap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.provider.Settings.Secure;

import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;

/**
 * @author Christof Lange
 *
 */
public class AndroidLicensePlugin extends CordovaPlugin {
	public CallbackContext callbackContext;


    private LicenseCheckerCallback mLicenseCheckerCallback;
    private LicenseChecker mChecker;
    
    
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
		if ("check".equals(action)) {
            //callbackContext.success("hi ho");
			startCheck();
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.

	}
	
	private void startCheck() {
		Context context=this.cordova.getActivity().getApplicationContext();
		
        // Try to use more data here. ANDROID_ID is a single point of attack.
        String deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

        // Library calls this when it's done.
        mLicenseCheckerCallback = new MyLicenseCheckerCallback();
        // Construct the LicenseChecker with a policy.
        mChecker = new LicenseChecker(
        		context, null, null);
        doCheck();

	}
	
	private void doCheck() {
        mChecker.checkAccess(mLicenseCheckerCallback);
    }


    public class MyLicenseCheckerCallback implements LicenseCheckerCallback {
    	
    	public void rawData(final int responseCode, final String signedData, final String signature) {
    		JSONObject jo = new JSONObject();
    		try {
				jo.put("responseCode", responseCode);
	    		jo.put("signedData", signedData);
	    		jo.put("signature", signature);
		    	callbackContext.success(jo);
			} catch (JSONException e) {
		    	callbackContext.success("error building JSONObject");
			}
        }
    	
        public void allow(int policyReason) {

	    	callbackContext.success("sccess: " + policyReason );
        }

        public void dontAllow(int policyReason) {

	    	callbackContext.error("do not allow: " + policyReason );
        }

        public void applicationError(int errorCode) {
	    	callbackContext.error("applicationError: " + errorCode );
        }
    }

	
}

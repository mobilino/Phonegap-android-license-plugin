var AndroidLicensePlugin = {
    check: function(successCallback, errorCallback) {
        cordova.exec(
             successCallback, 
             errorCallback, 
	         "AndroidLicensePlugin",
	         "check",
	         []);
     }
}
module.exports = AndroidLicensePlugin;
Cordova Plugin to extract Android License Information
===============================

Phonegap (build) plugin for android server based license validation. Available on npm: https://www.npmjs.com/package/cordova-plugin-androidlicense

This plugin uses the Google Play License Verification Library to request the paid apps license information from the play store. You can use this information to send it to your server for license verification and only grand those users access whose license information is valid.

Please see [this article] (http://android-developers.blogspot.de/2010/09/securing-android-lvl-applications.html) for why you should do that.

You can use the information retrieved by this plugin to feed [this php license verification library] (https://code.google.com/p/android-market-license-verification/).

Please note that you do NOT need to include you Public Key in the app since you will validate the license response on your server.

## Installation

### index.html

Add js import after your cordova.js / phonegap.js import
```html
<script type="text/javascript" src="AndroidLicensePlugin.js"></script>
```

### main.js

In your onDeviceReady you may start the license check with the following code.

```js
AndroidLicensePlugin.check(
        function(data) { alert( JSON.stringify(data));},
        function(errorString) { alert("error: " + errorString);}
        );
```

## Return Values

If the retrieval of the license information was successful the success callback will be called. Please note that a successful retrieval does NOT always mean that the app has a valid license! A List of LVL response codes can be found in [Googles LVL Library] (https://android.googlesource.com/platform/development.git/+/b8168f27f6f5b6b0ced5ef3210c0e89e8d3a5ae2/samples/MarketLicensing/src/com/android/vending/licensing/LicenseValidator.java). They are explained in [the Google docs] (http://developer.android.com/google/play/licensing/licensing-reference.html).
```js
data = {
        responseCode: 0,
        signedData: "0|-123456798|de.mobilino....", // 6 fields of | delimitered data
        signature: "" // the BASE64 encoded signature from Google
        } 
```
You can use signedData and signature to directly feed the mentioned php script.


If the retrieval was not successful the error callback will be called. This will happen in emulator, when the market app can not be reached or there is not internet connection. Usually this means that you what to retry the request after a certain amount of time.

## Installation
This plugin is based on [plugman](https://github.com/apache/cordova-plugman). To install it to your app,
simply execute plugman as follows;

```sh
plugman install --platform [PLATFORM] --project [TARGET-PATH] --plugin [PLUGIN-PATH]

where
        [PLATFORM] = ios or android
        [TARGET-PATH] = path to folder containing your phonegap project
        [PLUGIN-PATH] = path to folder containing this plugin
```

Alternatively this plugin can be installed using the Cordova/Phonegap CLI.


```sh
cordova plugin add https://github.com/mobilino/Phonegap-android-license-plugin.git
```

## Additional Resources

[Securing Android LVL Applications](http://android-developers.blogspot.de/2010/09/securing-android-lvl-applications.html)

[Android Market License Verification in PHP] (https://code.google.com/p/android-market-license-verification/)

## Authors ##

Christof Lange

## License ##

The MIT License

Copyright (c) 2014 Christof Lange

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

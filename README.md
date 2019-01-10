# Face_Analyzer
The purpose of this Android app is to use the Microsoft Face API to not only detect individual faces in an image, but also for each face provide information such as emotions, the estimated age, gender, and more.


## Using the Face API in the app:
### Android Studio
To use the app, you can clone it from this GitHub repository as a zip file, extract the contents of the file, and open it as a project in Android Studio. Once you have done so, it can be run on your Android device.
### Making the Azure Account
**Please note that this app requires the use of the Microsoft Azure's Face API**. Therefore, in order to run the face dectection and analysis, you must get an API Subscription Key from the Azure Portal. [This page](https://azure.microsoft.com/en-us/services/cognitive-services/face/) by Microsoft provides the features and capabilities of the Face API. **You can create a free Azure account that doesn't expire at [this link here] by clicking on the "Get API Key" button and choosing the option to create an Azure account(https://azure.microsoft.com/en-us/try/cognitive-services/?api=face-api)**. 
### Getting the Face API Key from Azure Portal
Once you have created your account, head to the [Azure Portal](https://portal.azure.com/#home). Follow these steps:
1. Click on "Create a resource" on the left side of the portal.
2. Underneath "Azure Marketplace", click on the "AI + Machine Learning" section. 
3. Now, under "Featured" you should see "Face". Click on that.
4. You should now be at [this page](https://portal.azure.com/#create/Microsoft.CognitiveServicesFace). Fill in the required information and press "Create" when done.
5. Now, click on "All resources" on the left hand side of the Portal.
6. Click on the name you gave the API.
7. Underneath "Resource Management", click on "Keys".
You should now be able to see two different subscription keys that you can use. Follow the additional instructions to see how to use the API Key in the app
### Using the API Key in the app
Head over to the [MainActivity page](https://github.com/ishaanjav/Face_Analyzer/blob/master/app/src/main/java/com/example/anany/emotionrecognition/MainActivity.java) since that is where the API Key will be used when creating the `FaceServiceClient` object. Where it says, 'faceServiceClient = new FaceServiceRestClient("<YOUR ENDPOINT HERE>", "<YOUR API SUBSCRIPTION KEY>");', replace '<YOUR API SUBSCRIPTION KEY>' with one of your 2 keys from the Azure Portal. *(If you haven't gotten your API Key yet, read the previous two sections above)*. `<YOUR ENDPOINT HERE>` should be replaced with one of the following examples from [this API Documentation link](https://westus.dev.cognitive.microsoft.com/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236). The format should be similar to: `"https://<LOCATION>/face/v1.0"` where `<LOCATION>` should be replaced with something like `uksouth.api.cognitive.microsoft.com` or `japaneast.api.cognitive.microsoft.com`. All of these can be found, listed at [this link](https://westus.dev.cognitive.microsoft.com/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236).
Now that you have the Face API Key, you can use the app as it was inteded. **Please note that if you are using the free, standard plan, you can only make 20 API transactions/calls per minute."

## Using the App:
The app is simple enough to use: the first page contains two buttons: one for taking the picture, the other for processing the picture. Hence, the app requires Camera Permission. Once the picture is taken, you can press the "Process" button and the app will use an `AsyncTask` and the Microsoft Face API to detect the faces in an image and get information about facial attributes such as age, headpose, gender, emotions, and more. *(You can customize what data the app detects and analyzes by specifying it in `FaceServiceClient.FaceAttributeType.MY_FACIAL_ATTRIBUTES` which is located in the `doInBackground` method of the `AsyncTask`.)*
Once the image has been processed, it takesyou to a second page, where for each person's face it picked up in the image, it generates a thumbnail of the individual, and displays it in a `ListView` alongside the information analyzed from the previous page. Once again, the Microsoft Face API offers a variety of features which can be found at [their site](https://azure.microsoft.com/en-us/services/cognitive-services/face/) and you can choose what `FaceAttributeType` will be analyzed by specifying it in teh `AsyncTask`.

## Future Proofness:
This repository was posted on January 9th, 2019. Therefore, updates would have been made to the Face API. Currently, the project uses the following `implementation` in `build.gradle`: `implementation 'com.microsoft.projectoxford:face:1.4.3'`. This can be changed for newer versions of the API. Feel free to make any changes to the app once you have cloned it and if you have any questions or issues, you can contact me at

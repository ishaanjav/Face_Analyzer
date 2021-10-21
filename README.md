<!-- [<img  width="128" alt="Donate to paypal.me/IJApps" src="https://img.shields.io/badge/Donate-PayPal-green.svg"/>](https://www.paypal.com/paypalme/ijapps) -->

[<img  width="70" alt="Click me for Video Demonstration" src="https://github.com/ishaanjav/Face_Analyzer/blob/master/IJ%20Apps.png" align="right"/>](https://www.youtube.com/ijapps)

# Face Analyzer
### [***Link to updated, improved app on Google Play Store***](https://play.google.com/store/apps/details?id=app.anany.faceanalyzer) 
 
**The purpose of this Android app is to use the Microsoft Face API to not only detect individual faces in an image, but also provide information about facial attributes for each face such as emotions, estimated age, gender, and more.** Possible applications for this app are at amusement parks, classrooms, and residential homes. 
1. **Amusement parks** can use the app to collect data about the type of audiences that rides gather based on age and other attributes in addition to analyzing the emotions of people before and after the ride. 
2. Furthermore, the app can be used in **classrooms** for analyzing student's faces when being taught. The teacher can then review the data about emotions to see whether students were able to understand, enjoy, or dislike the lesson. 
3. Finally, another application of the app is in **residential homes** where caretakers can regularly use the app to determine patients' emotions and store it in a database for later analyzation.

<img align="center" src="https://github.com/ishaanjav/Face_Analyzer/blob/master/Smiling.png" width="200"> <img align="center" src="https://github.com/ishaanjav/Face_Analyzer/blob/master/TakingPicture.png" width="220"> <img align="center" src="https://github.com/ishaanjav/Face_Analyzer/blob/master/Analysis.png" width="220">

_____

<img align="right" src="https://github.com/ishaanjav/Face_Analyzer/blob/master/Face%20Analyzer%20Demo.gif" width="250" />

## Usage:

### [Video Demonstration](https://youtu.be/wjYyZwTlWEQ)

The app is simple enough to use: the first page contains two button- one for taking the picture, the other for processing the picture. Hence, the app requires Camera Permission. Once the picture is taken, you can press the "Process" button and the app will use an `AsyncTask` and the Microsoft Face API to detect the faces in an image and get information about facial attributes such as age, headpose, gender, emotions, and more. *(You can customize what data the app detects and analyzes by specifying it in `FaceServiceClient.FaceAttributeType.MY_FACIAL_ATTRIBUTES` which is located in the `doInBackground` method of the `AsyncTask`. For more details, check out the [Detecting Particular Facial Attributes Section](#detecting-particular-facial-attributes))*


Once the image has been processed, it takes you to a second page, where for each person's face it detected in the image, it generates a thumbnail of the individual, and displays it in a `ListView` alongside the information analyzed from the previous page. Once again, the Microsoft Face API offers a variety of features which can be found at [their site](https://azure.microsoft.com/en-us/services/cognitive-services/face/) and you can choose what `FaceAttributeType` will be analyzed by specifying it in the `AsyncTask`.

### [Install the App](https://play.google.com/store/apps/details?id=app.anany.faceanalyzer)

_____
## Setup:

**Please note that this app requires the use of Microsoft Azure's Face API. Without an API Key, you will not be able to use the app as it was intended. The following sections contain the full set of instructions to getting your own API key for free and using it in the app by changing a single line of code.**
### Downloading to Android Studio
You can fork this project on GitHub, download it, and then open it as a project in Android Studio. Once you have done so, it can be run on your Android device. 
### Making the Azure Account
In order to run the face dectection and analysis, you must get an API Subscription Key from the Azure Portal. [This page](https://azure.microsoft.com/en-us/services/cognitive-services/face/) by Microsoft provides the features and capabilities of the Face API. **You can create a free Azure account that doesn't expire at [this link here](https://azure.microsoft.com/en-us/try/cognitive-services/?api=face-api) by clicking on the "Get API Key" button and choosing the option to create an Azure account**. 
### Getting the Face API Key from Azure Portal
Once you have created your account, head to the [Azure Portal](https://portal.azure.com/#home). Follow these steps:
1. Click on **"Create a resource"** on the left side of the portal.
2. Underneath **"Azure Marketplace"**, click on the **"AI + Machine Learning"** section. 
3. Now, under **"Featured"** you should see **"Face"**. Click on that.
4. You should now be at [this page](https://portal.azure.com/#create/Microsoft.CognitiveServicesFace). **Fill in the required information and press "Create" when done**.
5. Now, click on **"All resources"** on the left hand side of the Portal.
6. Click on the **name you gave the API**.
7. Underneath **"Resource Management"**, click on **"Manage Keys"**.

<p align="center">
  <img width="900" src="https://github.com/ishaanjav/Face_Analyzer/blob/master/Azure-FaceAPI%20Key.PNG">
  <td>Hi</td>
</p>

You should now be able to see two different subscription keys that you can use. Follow the additional instructions to see how to use the API Key in the app.
### Using the API Key in the app
Head over to the [MainActivity page](https://github.com/ishaanjav/Face_Analyzer/blob/master/app/src/main/java/com/example/anany/emotionrecognition/MainActivity.java) in Android Studio since that is where the API Key will be used when creating the `FaceServiceClient` object. Where it says in `onCreate`:

    faceServiceClient = new FaceServiceRestClient("<YOUR ENDPOINT HERE>", "<YOUR API SUBSCRIPTION KEY>"); 

replace `<YOUR API SUBSCRIPTION KEY>` with one of your 2 keys from the Azure Portal. *(If you haven't gotten your API Key yet, read the previous two sections above)*. `<YOUR ENDPOINT HERE>` should be replaced with one of the following examples from [this API Documentation link](https://westus.dev.cognitive.microsoft.com/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236). The format should be similar to: 
  
    "https://<LOCATION>/face/v1.0"
  
where `<LOCATION>` should be replaced with something like `uksouth.api.cognitive.microsoft.com` or `japaneast.api.cognitive.microsoft.com`. All of these can be found, listed at [this link](https://westus.dev.cognitive.microsoft.com/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236).

Now that you have the Face API Key, you can use the app as it was intended. **Please note that if you are using the free, standard plan, you can only make 20 API transactions/calls per minute. Therefore, if that limit is exceeded, you may run into runtime errors.**
### Detecting Particular Facial Attributes
The face analysis happens in the `detectandFrame` method of [`MainActivity.java`](https://github.com/ishaanjav/Face_Analyzer/blob/master/app/src/main/java/com/example/anany/emotionrecognition/MainActivity.java). More specifically, `detectandFrame` -> `AsyncTask` -> `doInBackground`. This is what the code looks like for detecting head position, age, gender, emotion, and facial hair:
    
    FaceServiceClient.FaceAttributeType[] faceAttr = new FaceServiceClient.FaceAttributeType[]{
         FaceServiceClient.FaceAttributeType.HeadPose,
         FaceServiceClient.FaceAttributeType.Age,
         FaceServiceClient.FaceAttributeType.Gender,
         FaceServiceClient.FaceAttributeType.Emotion,
         FaceServiceClient.FaceAttributeType.FacialHair
    };
You can change it to something like `FaceServiceClient.FaceAttributeType.hairColor`. For more of the `FaceAttributeTypes`, you can check out one of the JSON files from the [Face API page](https://azure.microsoft.com/en-us/services/cognitive-services/face/). 

Now that you have detected the face attributes, you will have to change the `CustomAdapter.java` in order to display the results from the detection process. In the `getView` method, to get the facial attributes of a face, the code uses `faces[position]` to get an element in the array of type `Face`. Then, you can use `faces[position].faceAttributes .faceAttribute` to get information about a particular attribute. The code is below:

    //Getting the Gender:
    faces[position].faceAttributes.gender
    
    //Getting facial hair information:
    //Probability of having a beard:
    faces[position].faceAttributes.facialHair.beard
    //Probability of having sideburns:
    faces[position].faceAttributes.facialHair.sideburns
    //Probability of having a moustache:
    faces[position].faceAttributes.facialHair.moustache
    
##### Please note that if you do not specify a certain face attribute to be detected, then doing `faces[position].faceAttributes.thatFacialAttribute` in the `getView` method will give you errors. Additionally, certain attributes, like Head Position and Facial Hair, have attributes within themselves such as `faces[position].faceAttributes.facialHair.moustache` which can end in `moustache, sideburns, beard` or `faces[position].faceAttributes.headPose.yaw` which can end in `yaw, roll, pitch`. 
_____
## [IJ Apps YouTube Channel](https://www.youtube.com/channel/UCLQUpH7SdkAXAeK6jeeF8zg)
If you found this repository useful, you may benefit from checking out my YouTube Channel for learning Android app development. 
It is called [**IJ Apps**](https://www.youtube.com/channel/UCLQUpH7SdkAXAeK6jeeF8zg) and has over 50+ tutorials (as of June 20th) starting at the basics and then covering more advanced topics.

There is also a [**video demonstration of Face Analyzer**](https://youtu.be/wjYyZwTlWEQ) on my channel if you would like to see how it works & how to use it.

***Once again, check out the YouTube Channel and don't forget to subscribe!***

_____
## Future Proofness:

### [***Link to updated, improved app on Google Play Store***](https://play.google.com/store/apps/details?id=app.anany.faceanalyzer)

This repository was posted on January 9th, 2019. Therefore, updates would have been made to the Face API since then. As of the time of posting, the project uses the following `implementation` in `build.gradle`:

    //This can be changed for newer versions of the API. 
    implementation 'com.microsoft.projectoxford:face:1.4.3'.

Feel free to make any changes to the app once you have cloned it and if you have any questions or issues, you can contact me at ijapps101@gmail.com or by raising an issue for this GitHub repository. I hope that you find this app useful and you enjoy using and testing it!

Furthermore, you may also want to check out some of my other repositories for Android apps:

- [**Kairos Face Recognition**](https://github.com/ishaanjav/Kairos_Face_Recognition): The purpose of this Android app is to use Kairos's SDK for Android in order to implement facial recognition. Features of this app include: registering and identifying users when given an image. [README](https://github.com/ishaanjav/Kairos_Face_Recognition/blob/master/README.md)
- [**GitHub Automated File Uploader**](https://github.com/ishaanjav/GitHub-Automated-File-Uploader): A command-line automation tool to upload files to GitHub, regardless of whether the files are in a git repository. Runs with a single command.
- **[Fingerprint Authentication](https://github.com/ishaanjav/Fingerprint_Authentication)** - A simple app that demonstrates how to use a device's fingerprint reader to authenticate a person's finger and identify it among existing fingerprints. **[README](https://github.com/ishaanjav/Fingerprint_Authentication/blob/master/README.md)**

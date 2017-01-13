# TwilioVideo
A Twilio Video example as shown on Twilio's blog post

![video-android-github](https://cloud.githubusercontent.com/assets/221627/13633155/3df7c41c-e5e5-11e5-8591-01f2a341732d.png)

## What you’ll need
* [Free Twilio account](https://www.twilio.com/try-twilio) which will come with access to the Twilio Video public beta
* A server-side quickstart and access token generator. You can get one on any [six major programming languages here](https://www.twilio.com/docs/api/video).
* [Ngrok](https://ngrok.com/) to provide a tunnel to our backend. You can read more about ngrok [here](https://www.twilio.com/blog/2013/10/test-your-webhooks-locally-with-ngrok.html).
* Twilio Conversations and Twilio Common libraries.

## How to run
Make sure you can run the quickstart locally by starting ngrok on the applciation's chosen port and browsing to it. Once you kow that works, open MainActivity.java and the ngrok url with your ngrok url:

`run("http://[your-ngrok-url].ngrok.io/token", new Callback()`

Load up the quickstart on the browser and copy the username assigned to that tab. Copy that name into `participants.add("WhateverNameYouWant")` in Android Studio and run the application.

Click the green button and you should see yourself on both screens now. The top one is your local camera, and the bottom one is from your browser. Click the red button to hangup.

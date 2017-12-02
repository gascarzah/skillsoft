/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.example.nicolas.vercoapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;


public class Verco extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("1ExiFixTStp7ui364aLaiCxqnW4oMol7Qm9RkYdK")
            .clientKey("sBNUTRxj6n9GgGsDozROjW7ZN8F6ABKUedi3TL2S")
            .server("https://parseapi.back4app.com")
            .build()
    );


//    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
//            .applicationId("ac41b2c72a3c2fbb49bf9db6b4afeb96b2d0a9a6")
//            .clientKey("\"38e313257fabb204b3d3bcd3a5a70eb4ac1702ff\",")
//            .server("http://ec2-52-14-232-19.us-east-2.compute.amazonaws.com/parse/")
//            .build()
//    );




//    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}

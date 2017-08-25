/*
 * Copyright 2017 dmfs GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dmfs.tasks.utils;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.dmfs.android.retentionmagic.RetentionMagic;
import org.dmfs.tasks.utils.permission.BasicAppPermissions;
import org.dmfs.tasks.utils.permission.Permission;
import org.dmfs.tasks.utils.permission.dialog.PermissionRequestDialogFragment;


/**
 * Base class for all Activities in the app.
 *
 * @author Tobias Reinsch <tobias@dmfs.org>
 */
public abstract class BaseActivity extends AppCompatActivity
{
    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // TODO Maybe DismissiblePermission extends Permission?
        Permission getAccountsPermission = new BasicAppPermissions(this).forName(Manifest.permission.GET_ACCOUNTS);
        if (!getAccountsPermission.isGranted()
                && savedInstanceState == null
                && !getSharedPreferences(PermissionRequestDialogFragment.PREFS_STORE_IGNORED_PERMISSIONS, MODE_PRIVATE)
                .getBoolean(Manifest.permission.GET_ACCOUNTS, false))
        {
            PermissionRequestDialogFragment.newInstance().show(getSupportFragmentManager(), "permission-dialog");
        }

        mPrefs = getSharedPreferences(getPackageName() + ".sharedPrefences", 0);

        RetentionMagic.init(this, getIntent().getExtras());

        if (savedInstanceState == null)
        {
            RetentionMagic.init(this, mPrefs);
        }
        else
        {
            RetentionMagic.restore(this, savedInstanceState);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        RetentionMagic.store(this, outState);
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        /*
         * On older SDK version we have to store permanent data in onPause(), because there is no guarantee that onStop() will be called.
		 */
        if (VERSION.SDK_INT < VERSION_CODES.HONEYCOMB)
        {
            RetentionMagic.persist(this, mPrefs);
        }
    }


    @Override
    protected void onStop()
    {
        super.onStop();
        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB)
        {
            RetentionMagic.persist(this, mPrefs);
        }
    }

}

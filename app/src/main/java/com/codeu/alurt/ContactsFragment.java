package com.codeu.alurt;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactsFragment
        extends Fragment
        implements
        LoaderManager.LoaderCallbacks<Cursor>
        , AdapterView.OnItemClickListener
{
    /*
     * Defines an array that contains column names to move from
     * the Cursor to the ListView.
     */
    private final static String[] FROM_COLUMNS = {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            , ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
    };
    /*
     * Defines an array that contains resource ids for the layout views
     * that get the Cursor column contents. The id is pre-defined in
     * the Android framework, so it is prefaced with "android.R.id"
     */
    private final static int[] TO_IDS = {
            R.id.name
            , R.id.thumbnail
    };
    // Define global mutable variables
    // Define a ListView object
    ListView mContactsList;
    // Define variables for the contact the user selects
    // The contact's _ID value
    long mContactId;
    // The contact's LOOKUP_KEY
    String mContactKey;
    // A content URI for the selected contact
    Uri mContactUri;
    // An adapter that binds the result Cursor to the ListView
    private SimpleCursorAdapter mCursorAdapter;

    // Empty public constructor, required by the system
    public ContactsFragment() {}

    private static final String[] PROJECTION = {
            ContactsContract.Contacts._ID
            , ContactsContract.Contacts.LOOKUP_KEY
            , ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            , ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
    };
    // The column indexes
    private static final int CONTACT_ID_INDEX = 0;
    private static final int LOOKUP_KEY_INDEX = 1;
    private static final int DISPLAY_NAME_PRIMARY = 2;
    // Defines the text expression
    private static final String SELECTION =
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                    + " <> '' ";
    private static final String SORT =
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = { };

    // A UI Fragment must inflate its View
    @Override
    public View onCreateView(
            LayoutInflater inflater
            , ViewGroup container
            , Bundle savedInstanceState)
    {
        // Inflate the fragment layout
        View v = inflater.inflate(R.layout.contacts_list_view,
                container, false);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Gets the ListView from the View list of the parent activity
        mContactsList =
                (ListView) getView().findViewById(android.R.id.list);
        // Gets a CursorAdapter
        mCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.contacts_list_item,
                null,
                FROM_COLUMNS, TO_IDS,
                0);
        // Sets the adapter for the ListView
        mContactsList.setAdapter(mCursorAdapter);
        // Set the item click listener to be the current fragment.
        mContactsList.setOnItemClickListener(this);
        // Initializes the loader
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onItemClick(
            AdapterView<?> parent
            , View item
            , int position
            , long rowI
    ) {
        // Get the Cursor
        Cursor cursor = ((CursorAdapter)parent.getAdapter()).getCursor();
        // Move to the selected contact
        cursor.moveToPosition(position);
        // Get the _ID value
        mContactId = cursor.getLong(CONTACT_ID_INDEX);
        // Get the selected LOOKUP KEY
        mContactKey = cursor.getString(LOOKUP_KEY_INDEX);
        // Create the contact's content Uri
        mContactUri = ContactsContract.Contacts.getLookupUri(mContactId, mContactKey);
    }

    @Override
    public Loader<Cursor> onCreateLoader(
            int loaderId
            , Bundle args
    ) {
        /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
        // Starts the query
        return new CursorLoader(
                getActivity(),
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                SELECTION,
                null,
                SORT
        );
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Put the result Cursor in the adapter for the ListView
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        mCursorAdapter.swapCursor(null);
    }
}
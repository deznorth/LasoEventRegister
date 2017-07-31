package com.example.android.lasoeventregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class AddEventActivity extends AppCompatActivity {

    private EditText tDate, tLocation, tTitle, tDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //declaring the editTexts and button
        tDate = (EditText) findViewById(R.id.dateEditText);
        tLocation = (EditText) findViewById(R.id.locationEditText);
        tTitle = (EditText) findViewById(R.id.titleEditText);
        tDescription = (EditText) findViewById(R.id.descriptionEditText);
        Button addButton = (Button) findViewById(R.id.addButton);

        //set onclicklistener for the button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d,l,t,de;
                d = String.valueOf(tDate.getText());
                l = String.valueOf(tLocation.getText());
                t = String.valueOf(tTitle.getText());
                de = String.valueOf(tDescription.getText());

                if(d.equals("") || l.equals("") || t.equals("") || de.equals("")){
                    Toast.makeText(AddEventActivity.this, "Fields cannot be left blank", Toast.LENGTH_LONG).show();
                }else{
                    addEvent(d,l,t,de);
                }
            }
        });
    }

    private int addEvent(final String date, final String location, final String title, final String description){

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.ADD_EVENT_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(AddEventActivity.this, "Succeeded to register event", Toast.LENGTH_LONG).show();
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(AddEventActivity.this, "Failed to register event", Toast.LENGTH_LONG).show();
                        Log.d("Error.Response", "failed");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("Date", date);
                params.put("Location", location);
                params.put("Title", title);
                params.put("Description", description);

                return params;
            }
        };
        queue.add(postRequest);

        return 1;
    }

}

package com.sriyanksiddhartha.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Author : Sriyank Siddhartha
 * Module 3 : Using GSON to Save and Retrieve Non-primitive Data Type
 *
 * 		"AFTER" Demo Project
 * */
public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	private TextView txvDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txvDisplay = (TextView) findViewById(R.id.txvDisplay);
	}

	// save button onClick
	public void saveObjectType(View view) {

		Employee employee = getEmployee();

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

		// Serialization
		Gson gson = new Gson();
		// convert Employee object to json string
		String jsonStr = gson.toJson(employee, Employee.class);
		Log.i(TAG + " SAVE", jsonStr);
		// {"name":"Sriyank Siddhartha","profId":287,"profession":"Android Developer","roles":["Developer","Admin"]}

		// put json string into preference
		prefsEditor.putString("employee_key", jsonStr);
		prefsEditor.apply();
	}

	public void loadObjectType(View view) {

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		// retrieve Employee json string from shared preferences
		String jsonStr = sharedPreferences.getString("employee_key", "");
		Log.i(TAG + " LOAD", jsonStr);

		// Deserialization
		Gson gson = new Gson();
		// reconstruct Employee object from json string
		Employee employeeObj = gson.fromJson(jsonStr, Employee.class);

		displayText(employeeObj);
	}

	public void saveGenericType(View view) {

		Employee employee = getEmployee();
		Foo<Employee> foo = new Foo<>();
		foo.setObject(employee);

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

		// Serialization
		Gson gson = new Gson();
		// tell Gson that inside Foo we have an object of Employee
		Type type = new TypeToken<Foo<Employee>>() {}.getType();
		String jsonStr = gson.toJson(foo, type);
		Log.i(TAG  + " SAVE", jsonStr);
		// {"object":{"name":"Sriyank Siddhartha","profId":287,"profession":"Android Developer","roles":["Developer","Admin"]}}

		prefsEditor.putString("foo_key", jsonStr);
		prefsEditor.apply();
	}

	public void loadGenericType(View view) {

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		String jsonStr = sharedPreferences.getString("foo_key", "");

		Log.i(TAG + " LOAD", jsonStr);

		// Deserialization
		Gson gson = new Gson();
		Type type = new TypeToken<Foo<Employee>>() {}.getType();

		Foo<Employee> employeeFoo = gson.fromJson(jsonStr, type);
		Employee employeeObj = employeeFoo.getObject();

		displayText(employeeObj);
	}

	private Employee getEmployee() {

		Employee employee = new Employee();
		employee.setName("Sriyank Siddhartha");
		employee.setProfession("Android Developer");
		employee.setProfId(287);
		employee.setRoles(Arrays.asList("Developer", "Admin"));

		return employee;
	}

	private void displayText(Employee employeeObj) {

		if (employeeObj == null)
			return;

		String display = employeeObj.getName()
				+ "\n" + employeeObj.getProfession()
				+ " - " + employeeObj.getProfId()
				+ "\n" + employeeObj.getRoles().toString();

		txvDisplay.setText(display);
	}
}

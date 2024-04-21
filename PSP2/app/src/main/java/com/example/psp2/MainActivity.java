package com.example.psp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    Spinner facultySpinner, departmentSpinner, semesterSpinner, specialtySpinner, levelSpinner, sectionSpinner, groupSpinner;
    RequestQueue requestQueue;
    ArrayList<String> facultyNameArrayList = new ArrayList<>(), departmentNameArrayList = new ArrayList<>(), specialtyNameArrayList = new ArrayList<>(), levelNameArrayList = new ArrayList<>(), sectionNameArrayList = new ArrayList<>(), groupNameArrayList = new ArrayList<>();
    ArrayList<String> facultyIdArrayList = new ArrayList<>(), departmentIdArrayList = new ArrayList<>(), specialtyIdArrayList = new ArrayList<>(), levelIdArrayList = new ArrayList<>(), sectionIdArrayList = new ArrayList<>(), groupIdArrayList = new ArrayList<>();
    final ArrayList<String> semesterArrayList = new ArrayList<>();
    int SECTION_ID, SEMESTER, GROUP_ID;
    String LANGUAGE = "fr";
    ArrayList<String> className = new ArrayList<>(), location = new ArrayList<>(), profName = new ArrayList<>(), dayOfWeek = new ArrayList<>(), timeSlot = new ArrayList<>(), type = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facultySpinner = findViewById(R.id.facultySpinner);
        departmentSpinner = findViewById(R.id.departmentSpinner);
        semesterSpinner = findViewById(R.id.semesterSpinner);
        specialtySpinner = findViewById(R.id.specialtySpinner);
        levelSpinner = findViewById(R.id.levelSpinner);
        sectionSpinner = findViewById(R.id.sectionSpinner);
        groupSpinner = findViewById(R.id.groupSpinner);
        submitButton = findViewById(R.id.submitButton);

        requestQueue = Volley.newRequestQueue(this);

        semesterArrayList.add("1");
        semesterArrayList.add("2");
        fillSpinner(semesterSpinner, semesterArrayList);
        semesterSpinner.setSelection(1);

        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SEMESTER = semesterSpinner.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        facultyNameArrayList.add("");
        jsonParse("https://num.univ-biskra.dz/psp/pspapi/faculty?key=appmob", "name_fac", "id_fac", facultyNameArrayList, facultyIdArrayList);
        fillSpinner(facultySpinner, facultyNameArrayList);

        facultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (facultySpinner.getSelectedItemPosition() == 0) {
                    return;
                }
                String idFaculty = facultyIdArrayList.get(facultySpinner.getSelectedItemPosition() - 1);
                departmentIdArrayList.clear();
                jsonParse("https://num.univ-biskra.dz/psp/pspapi/department?faculty=" + idFaculty + "&key=appmob", "name_fr", "id", departmentNameArrayList, departmentIdArrayList);
                departmentNameArrayList.clear();
                departmentNameArrayList.add("");
                fillSpinner(departmentSpinner, departmentNameArrayList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (departmentSpinner.getSelectedItemPosition() == 0) {
                    return;
                }
                String idDepartment = departmentIdArrayList.get(departmentSpinner.getSelectedItemPosition() - 1);
                specialtyIdArrayList.clear();
                jsonParse("https://num.univ-biskra.dz/psp/pspapi/specialty?department=" + idDepartment + "&semester=" + SEMESTER + "&key=appmob", "Nom_spec", "id_specialty", specialtyNameArrayList, specialtyIdArrayList);
                specialtyNameArrayList.clear();
                specialtyNameArrayList.add("");
                fillSpinner(specialtySpinner, specialtyNameArrayList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        specialtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (specialtySpinner.getSelectedItemPosition() == 0) {
                    return;
                }
                String idSpecialty = specialtyIdArrayList.get(specialtySpinner.getSelectedItemPosition() - 1);
                levelIdArrayList.clear();
                jsonParse("https://num.univ-biskra.dz/psp/pspapi/level?specialty=" + idSpecialty + "&semester=" + SEMESTER + "&key=appmob", "id_niveau", "id_niv_spec", levelNameArrayList, levelIdArrayList);
                levelNameArrayList.clear();
                levelNameArrayList.add("");
                fillSpinner(levelSpinner, levelNameArrayList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (levelSpinner.getSelectedItemPosition() == 0) {
                    return;
                }
                String idLevel = levelIdArrayList.get(levelSpinner.getSelectedItemPosition() - 1);
                sectionIdArrayList.clear();
                jsonParse("https://num.univ-biskra.dz/psp/pspapi/section?level_specialty=" + idLevel + "&semester=" + SEMESTER + "&key=appmob", "sectionn_name", "sectionn_id", sectionNameArrayList, sectionIdArrayList);
                sectionNameArrayList.clear();
                sectionNameArrayList.add("");
                fillSpinner(sectionSpinner, sectionNameArrayList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sectionSpinner.getSelectedItemPosition() == 0) {
                    return;
                }
                String idSection = sectionIdArrayList.get(sectionSpinner.getSelectedItemPosition() - 1);
                SECTION_ID = Integer.parseInt(idSection);
                groupIdArrayList.clear();
                jsonParse("https://num.univ-biskra.dz/psp/pspapi/group?section=" + idSection + "&semester=" + SEMESTER + "&key=appmob", "groupe_name", "groupe_id", groupNameArrayList, groupIdArrayList);
                groupNameArrayList.clear();
                groupNameArrayList.add("");
                fillSpinner(groupSpinner, groupNameArrayList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (groupSpinner.getSelectedItemPosition() == 0) {
                    return;
                }
                GROUP_ID = Integer.parseInt(groupIdArrayList.get(groupSpinner.getSelectedItemPosition() - 1));
                String url = "https://num.univ-biskra.dz/psp/pspapi/timetable?section=" + SECTION_ID + "&semester=" + SEMESTER + "&lang=" + LANGUAGE + "&group=" + GROUP_ID + "&key=appmob";
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
                    for (int j = 0; j < response.length(); j++) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(j);
                            className.add(jsonArray.getString(0));
                            location.add(jsonArray.getString(1));
                            profName.add(jsonArray.getString(6).charAt(0) + "." + jsonArray.getString(5));
                            dayOfWeek.add(jsonArray.getString(12));
                            timeSlot.add(jsonArray.getString(13));
                            type.add(jsonArray.getString(2));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, Throwable::printStackTrace);
                requestQueue.add(request);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitButton.setOnClickListener(view -> {
            openActivity2();
            className.clear();
            location.clear();
            profName.clear();
            dayOfWeek.clear();
            timeSlot.clear();
            type.clear();
        });

    }

    void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("class_name", className);
        intent.putExtra("location", location);
        intent.putExtra("prof_name", profName);
        intent.putExtra("day_of_week", dayOfWeek);
        intent.putExtra("time_slot", timeSlot);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    void jsonParse(String url, String apiName, String apiId, ArrayList<String> nameArrayList, ArrayList<String> idArrayList) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    String name = jsonObject.getString(apiName);
                    String id = jsonObject.getString(apiId);
                    if (apiName.equals("id_niveau")) {
                        switch (name) {
                            case "1":
                            case "2":
                            case "3":
                                nameArrayList.add("L" + name);
                                break;
                            case "4":
                                nameArrayList.add("M1");
                                break;
                            case "5":
                                nameArrayList.add("M2");
                                break;
                        }
                    } else {
                        nameArrayList.add(name);
                    }
                    idArrayList.add(id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, Throwable::printStackTrace);
        requestQueue.add(request);
    }

    void fillSpinner(Spinner nameSpinner, ArrayList<String> arrayList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(adapter);
    }
}


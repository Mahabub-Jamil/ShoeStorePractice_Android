package com.teamwork.shoestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    ArrayList<SlideModel> imageList = new ArrayList<>();
    RequestQueue queue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        if (container != null) {
            container.removeAllViews();
        }



        imageSlider =view.findViewById(R.id.image_slider);

        queue= Volley.newRequestQueue(requireContext());


        queue = Volley.newRequestQueue(requireContext());
        String url = "https://mj01861.000webhostapp.com/Shoe%20Store/slider.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i=0; i<response.length(); i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                String imageurl=jsonObject.getString("image");
                                imageList.add(new SlideModel(imageurl, ScaleTypes.FIT));
                                Log.d("ImageURL", imageurl);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        imageSlider.setImageList(imageList,ScaleTypes.FIT);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
        return view;




    }
}